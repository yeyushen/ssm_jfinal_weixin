package com.skywayct.controller.weixin.agent;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.entity.system.User;
import com.skywayct.service.weixin.agent.AgentManager;
import com.skywayct.util.Const;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;

/**
 * 说明：代理查看会员 创建人： 叶育生 创建时间：2016-06-1
 */
@Controller
@RequestMapping(value = "/agent")
public class AgentController extends BaseController {

	String menuUrl = "agent/list.do"; // 菜单地址(权限用)
	@Resource(name = "agentService")
	private AgentManager agentService;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表memberrelation");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		Session session = Jurisdiction.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String openid = (String) pd.get("openid");
		String user_openid = user.getOPENID();
		if (openid == null || openid.equals("")) {
			openid = user_openid;
		}
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		pd.put("openid", openid);
		page.setPd(pd);
		if (openid != null && !openid.equals("")) {
			List<PageData> varList = agentService.list(page); // 列出memberrelation列表
			mv.setViewName("weixin/agent/agent_list");
			mv.addObject("varList", varList);
			PageData pdById = agentService.findById(pd);// 用来页面返回用
			mv.addObject("openid", openid);
			// mv.addObject("currentPage", currentPage);
			mv.addObject("pd", pd);
			mv.addObject("pdById", pdById);
			mv.addObject("user_openid", user_openid);
		}
		mv.setViewName("weixin/agent/agent_list");
		mv.addObject("sort", pd.getString("sort"));
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

}
