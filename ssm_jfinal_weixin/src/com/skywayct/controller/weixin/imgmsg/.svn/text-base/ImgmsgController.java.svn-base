package com.skywayct.controller.weixin.imgmsg;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.util.AppUtil;
import com.skywayct.util.ObjectExcelView;
import com.skywayct.util.PageData;
import com.skywayct.util.Tools;
import com.skywayct.util.Jurisdiction;
import com.skywayct.service.weixin.imgmsg.ImgmsgService;

/** 
 * 类名称：ImgmsgController
 * 创建人： 
 * 创建时间：2015-05-10
 */
@Controller
@RequestMapping(value="/imgmsg")
public class ImgmsgController extends BaseController {
	
	String menuUrl = "imgmsg/list.do"; //菜单地址(权限用)
	@Resource(name="imgmsgService")
	private ImgmsgService imgmsgService;
	
	/**新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Imgmsg");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("IMGMSG_ID", this.get32UUID());	//主键
		pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		imgmsgService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Imgmsg");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			imgmsgService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**修改
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Imgmsg");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		imgmsgService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Imgmsg");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String KEYWORD = pd.getString("KEYWORD");
			if(null != KEYWORD && !"".equals(KEYWORD)){
				pd.put("KEYWORD", KEYWORD.trim());
			}
			page.setPd(pd);
			List<PageData>	varList = imgmsgService.list(page);	//列出Imgmsg列表
			mv.setViewName("weixin/imgmsg/imgmsg_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**去新增页面
	 * @return
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Imgmsg页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("weixin/imgmsg/imgmsg_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**去修改页面
	 * @return
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Imgmsg页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = imgmsgService.findById(pd);	//根据ID读取
			mv.setViewName("weixin/imgmsg/imgmsg_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**批量删除
	 * @return
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Imgmsg");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				imgmsgService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Imgmsg到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("关键词");	//1
			titles.add("创建时间");	//2
			titles.add("状态");	//3
			titles.add("备注");	//4
			titles.add("标题1");	//5
			titles.add("描述1");	//6
			titles.add("图片地址1");	//7
			titles.add("超链接1");	//8
			titles.add("标题2");	//9
			titles.add("描述2");	//10
			titles.add("图片地址2");	//11
			titles.add("超链接2");	//12
			titles.add("标题3");	//13
			titles.add("描述3");	//14
			titles.add("图片地址3");	//15
			titles.add("超链接3");	//16
			titles.add("标题4");	//17
			titles.add("描述4");	//18
			titles.add("图片地址4");	//19
			titles.add("超链接4");	//20
			titles.add("标题5");	//21
			titles.add("描述5");	//22
			titles.add("图片地址5");	//23
			titles.add("超链接5");	//24
			titles.add("标题6");	//25
			titles.add("描述6");	//26
			titles.add("图片地址6");	//27
			titles.add("超链接6");	//28
			titles.add("标题7");	//29
			titles.add("描述7");	//30
			titles.add("图片地址7");	//31
			titles.add("超链接7");	//32
			titles.add("标题8");	//33
			titles.add("描述8");	//34
			titles.add("图片地址8");	//35
			titles.add("超链接8");	//36
			dataMap.put("titles", titles);
			List<PageData> varOList = imgmsgService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("KEYWORD"));	//1
				vpd.put("var2", varOList.get(i).getString("CREATETIME"));	//2
				vpd.put("var3", varOList.get(i).get("STATUS").toString());	//3
				vpd.put("var4", varOList.get(i).getString("BZ"));	//4
				vpd.put("var5", varOList.get(i).getString("TITLE1"));	//5
				vpd.put("var6", varOList.get(i).getString("DESCRIPTION1"));	//6
				vpd.put("var7", varOList.get(i).getString("IMGURL1"));	//7
				vpd.put("var8", varOList.get(i).getString("TOURL1"));	//8
				vpd.put("var9", varOList.get(i).getString("TITLE2"));	//9
				vpd.put("var10", varOList.get(i).getString("DESCRIPTION2"));	//10
				vpd.put("var11", varOList.get(i).getString("IMGURL2"));	//11
				vpd.put("var12", varOList.get(i).getString("TOURL2"));	//12
				vpd.put("var13", varOList.get(i).getString("TITLE3"));	//13
				vpd.put("var14", varOList.get(i).getString("DESCRIPTION3"));	//14
				vpd.put("var15", varOList.get(i).getString("IMGURL3"));	//15
				vpd.put("var16", varOList.get(i).getString("TOURL3"));	//16
				vpd.put("var17", varOList.get(i).getString("TITLE4"));	//17
				vpd.put("var18", varOList.get(i).getString("DESCRIPTION4"));	//18
				vpd.put("var19", varOList.get(i).getString("IMGURL4"));	//19
				vpd.put("var20", varOList.get(i).getString("TOURL4"));	//20
				vpd.put("var21", varOList.get(i).getString("TITLE5"));	//21
				vpd.put("var22", varOList.get(i).getString("DESCRIPTION5"));	//22
				vpd.put("var23", varOList.get(i).getString("IMGURL5"));	//23
				vpd.put("var24", varOList.get(i).getString("TOURL5"));	//24
				vpd.put("var25", varOList.get(i).getString("TITLE6"));	//25
				vpd.put("var26", varOList.get(i).getString("DESCRIPTION6"));	//26
				vpd.put("var27", varOList.get(i).getString("IMGURL6"));	//27
				vpd.put("var28", varOList.get(i).getString("TOURL6"));	//28
				vpd.put("var29", varOList.get(i).getString("TITLE7"));	//29
				vpd.put("var30", varOList.get(i).getString("DESCRIPTION7"));	//30
				vpd.put("var31", varOList.get(i).getString("IMGURL7"));	//31
				vpd.put("var32", varOList.get(i).getString("TOURL7"));	//32
				vpd.put("var33", varOList.get(i).getString("TITLE8"));	//33
				vpd.put("var34", varOList.get(i).getString("DESCRIPTION8"));	//34
				vpd.put("var35", varOList.get(i).getString("IMGURL8"));	//35
				vpd.put("var36", varOList.get(i).getString("TOURL8"));	//36
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
