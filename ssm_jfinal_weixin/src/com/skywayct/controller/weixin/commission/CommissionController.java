package com.skywayct.controller.weixin.commission;


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
import com.skywayct.service.weixin.memberlevel.MemberLevelManager;
import com.skywayct.service.weixin.commission.CommissionManager;

/** 
 * 说明：普通会员
 * 创建人： 叶育生
 * 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value="/commission")
public class CommissionController extends BaseController {
	
	String menuUrl = "commission/list.do"; //菜单地址(权限用)
	@Resource(name="commissionService")
	private CommissionManager commissionService;
	
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表commission");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = commissionService.list(page);	//列出commission列表
		
		mv.setViewName("weixin/commission/commission_list");
		mv.addObject("varList", varList);

		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**删除，把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/updateDrById")
	public ModelAndView updateDrById() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改commission");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		commissionService.updateDrById(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	 /**批量删除，批量把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAllDr")
	@ResponseBody
	public Object updateAllDr() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除commission");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> list = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			
			commissionService.updateAllDr(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		list.add(pd);
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
