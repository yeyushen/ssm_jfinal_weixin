package com.skywayct.controller.weixin.memberqrcode;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.memberqrcode.MemberQrcodeManager;
import com.skywayct.service.weixin.memberrelation.impl.MemberRelationService;
import com.skywayct.util.AppUtil;
import com.skywayct.util.Const;
import com.skywayct.util.DateUtil;
import com.skywayct.util.FileUpload;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.ObjectExcelView;
import com.skywayct.util.PageData;
import com.skywayct.util.PathUtil;
import com.skywayct.util.Tools;

/** 
 * 说明：用户永久二维码
 * 创建人： hmf
 * 创建时间：2016-05-30
 */
@Controller
@RequestMapping(value="/memberqrcode")
public class MemberQrcodeController extends BaseController {
	
	String menuUrl = "memberqrcode/list.do"; //菜单地址(权限用)
	@Resource(name="memberqrcodeService")
	private MemberQrcodeManager memberqrcodeService;
	@Resource(name="memberrelationService")
	private MemberRelationService menberRelationService;
	


	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增MemberQrcode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		
		createQrcode(pd);
		
		//经测试多此一举  微信生成的二维码openid再次请求还是一样的
		//PageData oldQrcode = memberqrcodeService.findById(menberOpenId);
		//判断是否存在已删除的二维码  
/*		if( oldQrcode != null ){
			oldQrcode.put("dr", 0);
			memberqrcodeService.delete(oldQrcode);
			
		}else{*/

		mv.setViewName("save_result");

		return mv;
	}
	
	
	
	@RequestMapping(value="/saveBatch")
	public ModelAndView saveBatch() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量新增MemberQrcode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		createQrcode(pd);
		mv.setViewName("save_result");


		return mv;
	}
	
	
	
	/**
	 * 公用生成永久二维码方法
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	private void createQrcode(PageData pd) throws Exception{
		
		String	menberOpenId = pd.getString("TITLE");
		String	menberName = pd.getString("menberName");
		
		PageData scenePd = null;
		int count = 1;
		if( !Tools.isEmpty(menberOpenId) ){
		scenePd = memberqrcodeService.findSceneIdByOpenId(menberOpenId);
		pd.put("status", 0);
		}else{
			count = Integer.parseInt( pd.get("qrcodeCount").toString() );
			pd.put("status", 1);
		}
		int sceneId = 0;
		PageData emptyPd = null;
		for(int i = 0; i < count; i++ ){
			
		if( scenePd == null ){
			emptyPd = new PageData();
			emptyPd.put("open_id", menberOpenId);
			emptyPd.put("create_time", new Date().getTime());
			memberqrcodeService.saveScene(emptyPd);
			//scenePd = memberqrcodeService.findSceneIdByOpenId(menberOpenId);
			//System.out.println(emptyPd.get("scene_id"));
			//sceneId = memberqrcodeService.findLastSceneId();
			sceneId = Integer.parseInt(emptyPd.get("scene_id").toString());
		}else{
			//若有则用此场景值ID
			sceneId = Integer.parseInt(scenePd.get("scene_id").toString());
		}
		//int sceneId = (Integer) scenePd.get("scene_id");
		ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
		ApiResult qrcodeResult = QrcodeApi.createPermanent(sceneId);
		if( qrcodeResult != null ){
			if( qrcodeResult.getStr("errcode") != null ){
				logger.info("生成二维码不成功...");
			}
			pd.put("name", menberName);
			pd.put("url", qrcodeResult.getStr("url"));
			pd.put("ticket", qrcodeResult.getStr("ticket"));
			pd.put("open_id", menberOpenId);
			pd.put("create_time", new Timestamp(System.currentTimeMillis()));
			pd.put("scene_id", sceneId);
			String bz = pd.getString("bz") == null ? "" : pd.getString("bz");
			pd.put("bz", bz);
			pd.put("create_name", Jurisdiction.getUsername());
			pd.put("menberqrcode_id", this.get32UUID());	//主键
			
			//保持图片到本地
			String ffile = DateUtil.getDays();
			String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + ffile;
			String fileName = qrcodeResult.getStr("ticket").substring(3, 7) + new Date().getTime() + ".jpg";
			String fileUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrcodeResult.getStr("ticket");
			fileName = FileUpload.getHtmlPicture(fileUrl, filePath, fileName);
			if( fileName != null ){
			pd.put("path", ffile + "/" + fileName);
			}else{
				pd.put("path", "");
			}
			
			memberqrcodeService.save(pd);
		
		}	
		}
		
	}
	
	
	/***
	 * 打印二维码
	 * @return
	 */
	@RequestMapping(value = "/print")
	public ModelAndView print() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"打印MemberQrcode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		ModelAndView mv = new ModelAndView("iReportView");
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			List<PageData> varList = memberqrcodeService.findQrcodePic( ArrayDATA_IDS );
			JRDataSource jrDataSource = new JRBeanCollectionDataSource( varList );

			mv.addObject("url", "/WEB-INF/jasper/qrcode/qrcode.jasper");
			
			mv.addObject("format", "pdf");
			mv.addObject("jrMainDataSource", jrDataSource);
			//标记已打印
			memberqrcodeService.updatePrint(ArrayDATA_IDS);
			return mv;
		}else{
			
			return null;
		}
		
	}
	
	
	


	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除MemberQrcode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		memberqrcodeService.delete(pd);
		out.write("success");
		out.close();
	}
	
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表MemberQrcode");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		//page.setShowCount(5);
		List<PageData>	varList = memberqrcodeService.list(page);	//列出MemberQrcode列表
		mv.setViewName("weixin/memberqrcode/memberqrcode_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> tempPd = memberqrcodeService.findAllMember(pd);
		
		pd.put("menberList", tempPd);

		
		mv.setViewName("weixin/memberqrcode/memberqrcode_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	/**去批量新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddBatch")
	public ModelAndView goAddBatch()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		mv.setViewName("weixin/memberqrcode/memberqrcode_batch_add");
		mv.addObject("msg", "saveBatch");
		mv.addObject("pd", pd);
		return mv;
	}	
	/**
	 * 判断是否存在用户二维码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/qrcodeexist")
	@ResponseBody
	public Object isExist() throws Exception{

		String menberOpenId = getRequest().getParameter("DATA_ID").toString();
		PageData isExistPd = memberqrcodeService.findByOpenId(menberOpenId);
		JSONObject tranJson = new JSONObject();
		if( isExistPd != null ){
			tranJson.put("msg", "no");
		}else{
			tranJson.put("msg", "yes");
		}
		
		
		return tranJson.toString();
		
	}

	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除MemberQrcode");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			memberqrcodeService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	
	
	
	/**
	 * 获取配置信息
	 * @return
	 */
	private ApiConfig getApiConfig() {
		// TODO Auto-generated method stub
		ApiConfig ac = new ApiConfig();

		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));

		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey",
				"setting it in config file"));
		return ac;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
