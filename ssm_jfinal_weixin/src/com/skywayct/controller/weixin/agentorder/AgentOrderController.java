package com.skywayct.controller.weixin.agentorder;

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
import com.skywayct.service.weixin.agent.AgentManager;
import com.skywayct.service.weixin.agentorder.AgentOrderManager;
import com.skywayct.service.weixin.queryinfo.OrderMemberInfoManager;
import com.skywayct.util.Const;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;
import com.skywayct.util.Tools;

/**
 * 说明：代理会员查看下级会员订单 创建人： 叶育生 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value = "/agentorder")
public class AgentOrderController extends BaseController {

	String menuUrl = "agentorder/list.do"; // 菜单地址(权限用)
	@Resource(name = "agentorderService")
	private AgentOrderManager agentorderService;
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
		logBefore(logger, Jurisdiction.getUsername() + "列表agentorder");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Session session = Jurisdiction.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String user_openid = user.getOPENID();
		pd = this.getPageData();
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
			String keywords = pd.getString("keywords"); // 关键词检索条件
			if (null != keywords && !"".equals(keywords)) {
				pd.put("keywords", keywords.trim());
			}
			page.setPd(pd);
			if (user_openid != null && !user_openid.equals("")) {
				List<PageData> varList = agentorderService.list(page); // 列出所有下级订单列表
				mv.addObject("varList", varList);
				mv.addObject("pd", pd);
				mv.addObject("user_openid", user_openid);
				mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
			}
		}
		mv.setViewName("weixin/agentorder/agentorder_list");
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

}
