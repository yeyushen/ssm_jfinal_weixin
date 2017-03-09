package com.skywayct.controller.weixin.phonecharge;

import java.math.BigDecimal;
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

import com.jfinal.weixin.model.Order;
import com.jfinal.weixin.skywayct.api.inteface.OrderHandler;
import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.util.AppUtil;
import com.skywayct.util.ObjectExcelView;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.service.weixin.phonecharge.PhoneChargeOrderManager;

/**
 * 说明：话费订单 创建人： 叶育生 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value = "/phonecharge")
public class PhoneChargeController extends BaseController {

	String menuUrl = "phonecharge/list.do"; // 菜单地址(权限用)
	@Resource(name = "phonechargeService")
	private PhoneChargeOrderManager phonechargeService;

	/**
	 * 所有话费订单列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表order");
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

		String keywords = pd.getString("keywords"); // 关键词检索条件
		String time1 = pd.getString("lastStart");
		String time2 = pd.getString("lastEnd");
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
			pd.put("is_today", "1");
		}
		
		//有搜索条件搜索全部订单
		if( (null != time1 && !"".equals(time1)) || (null != time2 && !"".equals(time2))){
			pd.put("is_today", "1");
		}
		
		
		
		page.setPd(pd);

		
		List<PageData> varList = phonechargeService.list(page); // 列出OrdinaryMember列表
		List<PageData> countList = phonechargeService.countOrder(page); // 列出OrdinaryMember列表
		
		
		PageData pg = countList.get(0);
		Long total = (Long) pg.get("total");
		Double totalProductPrice = (Double) pg.get("totalProductPrice");
		Double totalSettlementPrice = (Double) pg.get("totalSettlementPrice");
		Double totalCostPrice = (Double) pg.get("totalCostPrice");
		Double totalProfit = (Double) pg.get("totalProfit");
		mv.setViewName("weixin/phonecharge/phonecharge_list");
		mv.addObject("varList", varList);
		String status = pd.getString("status");
		if (status != null && status.length() > 0) {
			if (status.equals("0")) {
				pd.put("status", 100);//0变为100 js好判断
			}
		}

		String paystatus = pd.getString("paystatus");
		if (paystatus != null && paystatus.length() > 0) {
			if (paystatus.equals("0")) {
				pd.put("paystatus", 101);// 0变为101 js好判断
			}
		}
		mv.addObject("pd", pd);
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
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 批量更新订单状态为失败
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatusFail")
	@ResponseBody
	public Object updateStatusFail() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量更新订单状态为成功order");
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
			Order order = null;
			for(int i = 0 ; i<ArrayDATA_IDS.length;i++){
				 order = Order.dao.findById(ArrayDATA_IDS[i]);
				 OrderHandler.reNewOrder(order, false);
			}
			//orderService.updateStatusFail(ArrayDATA_IDS);
		
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 批量更新订单状态为成功
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStatusSuccess")
	@ResponseBody
	public Object updateStatusSuccess() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量更新订单状态为成功order");
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
		//	orderService.updateStatusSuccess(ArrayDATA_IDS);
			pd.put("msg", "ok");
			Order order = null;
			for(int i = 0 ; i<ArrayDATA_IDS.length;i++){
				 order = Order.dao.findById(ArrayDATA_IDS[i]);
				 //如果未支付
				 
				 OrderHandler.reNewOrder(order, true);
			}
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
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		logBefore(logger, "导出订单列表到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//默认显示当天订单
		String isToday = pd.getString("is_today");
		if( isToday == null ){
			pd.put("is_today", "0");
		}

		String keywords = pd.getString("keywords"); // 关键词检索条件
		String time1 = pd.getString("lastStart");
		String time2 = pd.getString("lastEnd");
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
			pd.put("is_today", "1");
		}
		
		//有搜索条件搜索全部订单
		if( (null != time1 && !"".equals(time1)) || (null != time2 && !"".equals(time2))){
			pd.put("is_today", "1");
		}		
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("会员昵称"); // 1
			titles.add("电话号码"); // 2
			titles.add("号码归属地"); // 3
			titles.add("产品名称"); // 4
			titles.add("产品价格"); // 5
			titles.add("结算价格");
			titles.add("日志");
			titles.add("支付方式");
			titles.add("支付状态");
			titles.add("充值状态");
			titles.add("创建日期");
			dataMap.put("titles", titles);
			Page page = new Page();
			page.setPd(pd);
			List<PageData> varOList = phonechargeService.list(page);
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < varOList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("nick_name")); // 1
				vpd.put("var2", varOList.get(i).getString("phone")); // 2
				vpd.put("var3", varOList.get(i).getString("attribution")); // 3
				String productName = varOList.get(i).get("product_name") == null ? ""
						: varOList.get(i).get("product_name").toString();
				vpd.put("var4", productName);
				vpd.put("var5", varOList.get(i).get("product_price").toString());
				vpd.put("var6", varOList.get(i).get("settlement_price")
						.toString());
				String log = varOList.get(i).get("log") == null ? "" : varOList
						.get(i).get("log").toString();
				vpd.put("var7", log);
				String settlement_method = varOList.get(i)
						.get("settlement_method").toString();
				if (settlement_method.equals("1")) {
					vpd.put("var8", "微信支付");
				} else if (settlement_method.equals("2")) {
					vpd.put("var8", "钱包支付");
				} else {
					vpd.put("var8", "");
				}
				String paystatus = varOList.get(i).get("paystatus") == null ? ""
						: varOList.get(i).get("paystatus").toString();
				if (paystatus.equals("0")) {
					vpd.put("var9", "支付失败");
				} else if (paystatus.equals("1")) {
					vpd.put("var9", "支付成功");
				} else if (paystatus.equals("2")) {
					vpd.put("var9", "已退款");
				} else {
					vpd.put("var9", "");
				}
				String status = varOList.get(i).get("status") == null ? ""
						: varOList.get(i).get("status").toString();
				if (status.equals("0")) {
					vpd.put("var10", "充值失败");
				} else if (status.equals("1")) {
					vpd.put("var10", "充值成功");
				} else if (status.equals("2")) {
					vpd.put("var10", "处理中");
				} else {
					vpd.put("var10", "");
				}

				vpd.put("var11", varOList.get(i).get("recharge_dttm").toString().substring(0, 16));

				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
			logger.error(e.toString(), e);
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
