package com.skywayct.controller.weixin.memberrelation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.service.weixin.memberrelation.MemberRelationManager;

/**
 * 说明：普通会员 创建人： 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value = "/memberrelation")
public class MemberRelationController extends BaseController {

	String menuUrl = "memberrelation/list.do"; // 菜单地址(权限用)
	@Resource(name = "memberrelationService")
	private MemberRelationManager memberrelationService;

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
		/*
		 * String currentPage1 = pd.getString("currentPage"); Integer
		 * currentPage = null; if(!(currentPage1==null)){ currentPage =
		 * Integer.parseInt(currentPage1); page.setCurrentPage(currentPage); }
		 */
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		String openid = null == pd.get("openid") ? "" : pd.get("openid")
				.toString();
		if (null != pd.get("id") && !"".equals(pd.get("id").toString())) {
			openid = pd.get("id").toString();
		}
		pd.put("openid", openid);
		page.setPd(pd);
		List<PageData> varList = memberrelationService.list(page); // 列出memberrelation列表
		mv.setViewName("weixin/memberrelation/memberrelation_list");
		mv.addObject("varList", varList);
		PageData pdById = memberrelationService.findById(pd);// 用来页面返回用
		mv.addObject("openid", openid);
		// mv.addObject("currentPage", currentPage);
		mv.addObject("pdById", pdById);
		mv.addObject("pd", pd);
		mv.addObject("sort", pd.getString("sort"));
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 显示列表ztree
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllMember")
	public ModelAndView listAllMember(Model model, String openid)
			throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			JSONArray arr = JSONArray.fromObject(memberrelationService
					.listAllMember("0"));
			String json = arr.toString();
			json = json.replaceAll("openid", "id")
					.replaceAll("parent_id", "pId")
					.replaceAll("nick_name", "name")
					.replaceAll("subMember", "nodes")
					.replaceAll("hasMember", "checked")
					.replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("openid", openid);
			mv.addObject("pd", pd);
			mv.setViewName("weixin/memberrelation/memberrelation_ztree");
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
