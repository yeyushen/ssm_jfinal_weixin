package com.skywayct.controller.weixin.profit;

import java.math.BigDecimal;
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

import org.beetl.ext.fn.ParseInt;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.service.weixin.order.OrderManager;
import com.skywayct.service.weixin.profit.ProfitManager;

/**
 * 说明：普通会员 创建人： 叶育生 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value = "/profit")
public class ProfitController extends BaseController {

	String menuUrl = "profit/list.do"; // 菜单地址(权限用)
	@Resource(name = "profitService")
	private ProfitManager profitService;

	/**
	 * 所有订单列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表profit");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//默认显示当天订单
		String isToday = pd.getString("is_today");
		if( isToday == null ){
			pd.put("is_today", "0");
		}
		String time1 = pd.getString("lastStart");
		String time2 = pd.getString("lastEnd");
		//有搜索条件搜索全部订单
		if( (null != time1 && !"".equals(time1)) || (null != time2 && !"".equals(time2))){
			pd.put("is_today", "1");
		}
		
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
			pd.put("is_today", "1");
		}
		
		page.setPd(pd);
		
		List<PageData> varList = profitService.list(page); // 列出OrdinaryMember列表
		List<PageData> countList = profitService.countProfit(page); // 统计
		
		PageData pg = countList.get(0);
		Long total = (Long) pg.get("total");
		Double totalProductPrice = (Double) pg.get("totalProductPrice");
		Double totalSettlementPrice = (Double) pg.get("totalSettlementPrice");
		Double totalCostPrice = (Double) pg.get("totalCostPrice");
		Double totalcommission = (Double) pg.get("totalcommission");
		Double totalProfitPrice = (Double) pg.get("totalProfitPrice");
		Double totalProfitPrice1 = (Double) pg.get("totalProfitPrice1");
		Double totalProfitPrice2 = (Double) pg.get("totalProfitPrice2");
		Double totalProfitPrice3 = (Double) pg.get("totalProfitPrice3");
		mv.setViewName("weixin/profit/profit_list");
		mv.addObject("varList", varList);
		String status = pd.getString("status");
		if (status != null && status.length() > 0) {
			if (status.equals("0")) {
				pd.put("status", 100);
			}
		}

		String paystatus = pd.getString("paystatus");
		if (paystatus != null && paystatus.length() > 0) {
			if (paystatus.equals("0")) {
				pd.put("paystatus", 101);// 0变为101 js好判断
			}
		}
		mv.addObject("total", total); // 统计
		mv.addObject("totalProductPrice", new BigDecimal(totalProductPrice)
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计保留两位小数
		mv.addObject("totalSettlementPrice", new BigDecimal(
				totalSettlementPrice).setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue()); // 统计
		mv.addObject(
				"totalCostPrice",
				new BigDecimal(totalCostPrice).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
		mv.addObject(
				"totalcommission",
				new BigDecimal(totalcommission).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
		mv.addObject(
				"totalProfitPrice",
				new BigDecimal(totalProfitPrice).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统
		mv.addObject(
				"totalProfitPrice1",
				new BigDecimal(totalProfitPrice1).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
		mv.addObject(
				"totalProfitPrice2",
				new BigDecimal(totalProfitPrice2).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
		mv.addObject(
				"totalProfitPrice3",
				new BigDecimal(totalProfitPrice3).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}
	
	/***
	 * 打印
	 * @return
	 */
	@RequestMapping(value = "/print")
	public ModelAndView print() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//默认显示当天订单
		String isToday = pd.getString("is_today");
		if( isToday == null ){
			pd.put("is_today", "0");
		}
		String time1 = pd.getString("lastStart");
		String time2 = pd.getString("lastEnd");
		//有搜索条件搜索全部订单
		if( (null != time1 && !"".equals(time1)) || (null != time2 && !"".equals(time2))){
			pd.put("is_today", "1");
		}
		
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
			pd.put("is_today", "1");
		}
		String printType = pd.getString("print_type");
		printType = printType == null ? "1" : printType ;
		
		ModelAndView mv = new ModelAndView("iReportView");
		List<PageData> varList = profitService.printProfit(pd);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(varList);
		if( printType.equals("1") ){
		mv.addObject("url", "/WEB-INF/jasper/profit/profit_date.jasper");
		}else if( printType.equals("2")){
		mv.addObject("url", "/WEB-INF/jasper/profit/profit_chnnl.jasper");	
		}else{
		mv.addObject("url", "/WEB-INF/jasper/profit/profit_chart.jasper");
		}
		mv.addObject("format", "pdf");
		mv.addObject("jrMainDataSource", jrDataSource);
		return mv;
		
	}
	
	
	
	
	
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
