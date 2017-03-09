package com.skywayct.controller.weixin.orderpayagain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.skywayct.service.weixin.orderpayagain.impl.OrderPayAgainService;

/***
 * 二次充值模块
 * @author hmf
 *
 */

@Controller
@RequestMapping(value = "/ordPayAgain")
public class OrderPayAgainController extends BaseController {

	String menuUrl = "orderPayAgain/list.do"; // 菜单地址(权限用)
	@Resource(name = "orderPayAgainService")
	private OrderPayAgainService orderPayAgainService;

	/**
	 * 所有二次充值订单列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView listFail(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表二次充值");
		 if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		 //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = orderPayAgainService.list(page);  
		List<PageData> countList = orderPayAgainService.countPayAgain(page);  
		
		PageData pg = countList.get(0);
		Long total = (Long) pg.get("total");
		Double totalProductPrice = (Double) pg.get("totalProductPrice");
		Double totalSettlementPrice = (Double) pg.get("totalSettlementPrice");
		Double totalCostPrice = (Double) pg.get("totalCostPrice");
		Double totalProfit = (Double) pg.get("totalProfit");
		
		mv.setViewName("weixin/orderpayagain/order_payagain");
		mv.addObject("varList", varList);
		
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
				"totalProfit",
				new BigDecimal(totalProfit).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
		mv.addObject("pd", pd);
		
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}
	
	/**
	 * 去显示二次充值明细界面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goRecord")
	public ModelAndView showRecordDetail(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String order_id = pd.getString("detail_id");
		
		if( order_id != null && !order_id.equals("")){
			pd.put("order_id", order_id);
			page.setPd(pd);
			
			List<PageData> detaiList = orderPayAgainService.listDetail(page);
			
			mv.setViewName("weixin/orderpayagain/show_payagain_detail");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.addObject("varList", detaiList);
		}else{
			
		    mv.addObject("msg", "error");
		    mv.setViewName("error_result");
		}

		return mv;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
