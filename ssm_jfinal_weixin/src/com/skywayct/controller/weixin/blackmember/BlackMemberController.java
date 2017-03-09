package com.skywayct.controller.weixin.blackmember;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.blackmember.impl.BlackMemberService;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;
import com.skywayct.util.weixin.WXTagUtil;

/**
 * 说明：黑名单管理控制器   创建人： 叶育生 创建时间：2016-06-21
 */
@Controller
@RequestMapping(value = "/blackmember")
public class BlackMemberController extends BaseController {
	String menuUrl = "blackmember/list.do"; // 菜单地址(权限用)
	@Resource(name = "blackmemberService")
	private BlackMemberService blackmemberService;

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
		page.setPd(pd);
		List<PageData> varList = blackmemberService.list(page); // 列出BlackMember列表

		mv.setViewName("weixin/blackmember/blackmember_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWXBlack")
	public ModelAndView getWXBlack(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String tag = "{\"tagid\":1}";
		String message = WXTagUtil.getAllMemberByTag(tag);
		JSONObject jsonObject1 = JSONObject.fromObject(message); // 可以将json字符串转换成json对象
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get("data");
		JSONArray jsonArray = (JSONArray) jsonObject2.get("openid");
		pd.put("jsonArray", jsonArray);
		page.setPd(pd);
/*		JSONArray js = new JSONArray();
		js.add("obNUxwRdlglpIgPpgnC3dUXdTir4");
		js.add("obNUxwbCEaWOR_pWs2AewBN9pO2Q");
		js.add("obNUxwWotG2Dkcxt4yJ4RgIx0Llc");*/
		blackmemberService.updateRemoveMemberBlack(jsonArray);
		blackmemberService.updateMemberToBlack(jsonArray);
		List<PageData> varList = blackmemberService.list(page);
		mv.setViewName("weixin/blackmember/blackmember_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}
}
