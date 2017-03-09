package com.skywayct.controller.weixin.agentprofit;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.entity.system.User;
import com.skywayct.util.Const;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.Tools;
import com.skywayct.service.weixin.agent.AgentManager;
import com.skywayct.service.weixin.agentprofit.AgentProfitManager;
import com.skywayct.service.weixin.queryinfo.OrderMemberInfoManager;

/**
 * 说明：代理会员查看下级产生的利润创建人： 叶育生 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value = "/agentprofit")
public class AgentProfitController extends BaseController {

	String menuUrl = "agentprofit/list.do"; // 菜单地址(权限用)
	@Resource(name = "agentprofitService")
	private AgentProfitManager agentprofitService;
	@Resource(name = "agentService")
	private AgentManager agentService;
	@Resource(name = "orderMemberInfoService")
	private OrderMemberInfoManager orderMemberInfoService;

	/**
	 * 所有订单列表
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
		Session session = Jurisdiction.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String user_openid = user.getOPENID();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		pd.put("openid", user_openid);
		String childIds = null;
		if (user_openid != null && !user_openid.equals("")) {
			childIds = orderMemberInfoService.listChildByPID(pd);
		}
		if (!Tools.isEmpty(childIds)) {
			String ArrayDATA_IDS[] = childIds.split(",");
			String tempIds = "";
			for (String str : ArrayDATA_IDS) {
				tempIds += "'" + str + "',";
			}
			pd.put("childIds", tempIds.substring(0, tempIds.length() - 1));
			if (null != keywords && !"".equals(keywords)) {
				pd.put("keywords", keywords.trim());
			}
			page.setPd(pd);
			if (user_openid != null && !user_openid.equals("")) {
				List<PageData> varList = agentprofitService.list(page); // 列出列表
				List<PageData> countList = agentprofitService.countProfit(page); // 统计
				PageData pg = countList.get(0);
				Long total = (Long) pg.get("total");
				Double totalProductPrice = (Double) pg.get("totalProductPrice");
				Double totalSettlementPrice = (Double) pg
						.get("totalSettlementPrice");
				Double totalCostPrice = (Double) pg.get("totalCostPrice");
				Double totalcommission = (Double) pg.get("totalcommission");
				Double totalProfitPrice = (Double) pg.get("totalProfitPrice");
				Double totalProfitPrice1 = (Double) pg.get("totalProfitPrice1");
				Double totalProfitPrice2 = (Double) pg.get("totalProfitPrice2");
				Double totalProfitPrice3 = (Double) pg.get("totalProfitPrice3");
				String status = pd.getString("status");
				if (status != null && status.length() > 0) {
					if (status.equals("0")) {
						pd.put("status", 100);
					}
				}
				mv.addObject("total", total); // 统计
				mv.addObject("totalProductPrice", new BigDecimal(
						totalProductPrice)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计保留两位小数
				mv.addObject(
						"totalSettlementPrice",
						new BigDecimal(totalSettlementPrice).setScale(2,
								BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
				mv.addObject("totalCostPrice", new BigDecimal(totalCostPrice)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
				mv.addObject("totalcommission", new BigDecimal(totalcommission)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
				mv.addObject("totalProfitPrice", new BigDecimal(
						totalProfitPrice).setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue()); // 统
				mv.addObject("totalProfitPrice1", new BigDecimal(
						totalProfitPrice1)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
				mv.addObject("totalProfitPrice2", new BigDecimal(
						totalProfitPrice2)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
				mv.addObject("totalProfitPrice3", new BigDecimal(
						totalProfitPrice3)
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()); // 统计
				mv.addObject("pd", pd);
				mv.addObject("varList", varList);
			}
		}
		mv.setViewName("weixin/agentprofit/agentprofit_list");
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
