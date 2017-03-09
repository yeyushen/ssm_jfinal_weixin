package com.skywayct.controller.weixin.ordinarymember;

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
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.Tools;
import com.skywayct.service.weixin.commission.CommissionManager;
import com.skywayct.service.weixin.memberlevel.MemberLevelManager;
import com.skywayct.service.weixin.mypoints.MyPointsManager;
import com.skywayct.service.weixin.ordinarymember.OrdinaryMemberManager;

/**
 * 说明：普通会员 创建人： 叶育生 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value = "/ordinarymember")
public class OrdinaryMemberController extends BaseController {

	String menuUrl = "ordinarymember/list.do"; // 菜单地址(权限用)
	@Resource(name = "ordinarymemberService")
	private OrdinaryMemberManager ordinarymemberService;
	@Resource(name = "memberlevelService")
	private MemberLevelManager memberlevelService;
	@Resource(name = "commissionService")
	private CommissionManager commissionService;
	@Resource(name = "mypointsService")
	private MyPointsManager mypointsService;
	
	
	/**
	 * 保存
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "新增OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String create_date = pd.getString("create_date");
		if(create_date.equals("")){
			pd.put("create_date", new Date());
		}
		pd.put("ordinarymember_id", this.get32UUID()); // 主键
		ordinarymemberService.save(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 根据id把数据真正从数据库中删除
	 * 
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "删除OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return;
		} // 校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		ordinarymemberService.delete(pd);
		out.write("success");
		out.close();
	}

	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		ordinarymemberService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	

	/**
	 * 删除，把dr由0变为1
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDrById")
	public ModelAndView updateDrById() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		ordinarymemberService.updateDrById(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 批量删除，批量把dr由0变为1
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAllDr")
	@ResponseBody
	public Object updateAllDr() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量删除OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");

			ordinarymemberService.updateAllDr(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表OrdinaryMember");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		String min = pd.getString("min"); // 最小余额检索条件
		if (null != min && !"".equals(min)) {
			pd.put("min", min.trim());
		}
		String max = pd.getString("max"); // 最大余额检索条件
		if (null != max && !"".equals(max)) {
			pd.put("max", max.trim());
		}
		
		page.setPd(pd);
		List<PageData> varList = ordinarymemberService.list(page); // 列出OrdinaryMember列表

		mv.setViewName("weixin/ordinarymember/ordinarymember_list");
		mv.addObject("varList", varList);

		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}

	/**
	 * 去显示佣金明细界面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goCommission")
	public ModelAndView goCommission(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String open_id = pd.getString("open_id");
		
		if( open_id != null && !open_id.equals("")){
			pd.put("filed", "open_id");
			pd.put("key_value", open_id);
			
			String points = pd.getString("points");
			if( points == null || points.equals("")){
			PageData pointPd = mypointsService.findByOpenId(open_id);
			if( pointPd == null ){
				pd.put("points", 0);
			}else{
				pd.put("points", pointPd.get("points"));
			}
			}
			
			String wallet = pd.getString("wallet");
			if( wallet == null || wallet.equals("")){
			PageData walletPd = ordinarymemberService.findWalletByOpenId(open_id);
			if( walletPd == null ){
				pd.put("wallet", 0);
			}else{
				pd.put("wallet", walletPd.get("amount"));
			}
			}			
			
			page.setPd(pd);
			List<PageData> csList = commissionService.listByFiled(page);
			//pd.put("csList", csList);
			
			mv.setViewName("weixin/commission/show_commission_record");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			mv.addObject("varList", csList);
			
		}else{
			
		    mv.addObject("msg", "error");
		    mv.setViewName("error_result");
		}

		return mv;
	}	
	
	/**
	 * 去新增页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> levelList = memberlevelService.listAll(null);
		mv.setViewName("weixin/ordinarymember/ordinarymember_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		mv.addObject("levelList", levelList);
		return mv;
	}

	/**
	 * 去修改页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = ordinarymemberService.findById(pd); // 根据ID读取
		List<PageData> levelList = memberlevelService.listAll(null);// 查询所有级别，方便在编辑页面下拉框选择和显示
		mv.setViewName("weixin/ordinarymember/ordinarymember_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("levelList", levelList);
		return mv;
	}

	/**
	 * 把数据真正从数据库中批量删除
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量删除OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			ordinarymemberService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 导出到excel
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "导出OrdinaryMember到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("微信Openid"); // 1
		titles.add("会员昵称"); // 2
		titles.add("手机号码"); // 3
		titles.add("加入时间"); // 4
		titles.add("会员头像"); // 5
		titles.add("激活状态"); // 6
		dataMap.put("titles", titles);
		List<PageData> varOList = ordinarymemberService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for (int i = 0; i < varOList.size(); i++) {
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("OPENID")); // 1
			vpd.put("var2", varOList.get(i).getString("NICKNAME")); // 2
			vpd.put("var3", varOList.get(i).getString("PHONE")); // 3
			vpd.put("var4", varOList.get(i).getString("JOINTIME")); // 4
			vpd.put("var5", varOList.get(i).getString("PIC")); // 5
			vpd.put("var6", varOList.get(i).getString("STATUS")); // 6
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
