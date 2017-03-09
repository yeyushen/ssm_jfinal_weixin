package com.skywayct.controller.weixin.historywarning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.historywarning.HistoryWarningManager;
import com.skywayct.util.AppUtil;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;

/**
 * 说明：查看订单历史预警消息 创建人： 叶育生 创建时间：2016-06-27
 */
@Controller
@RequestMapping(value = "/historywarning")
public class HistoryWarningController extends BaseController {
	String menuUrl = "historywarning/list.do"; // 菜单地址(权限用)
	@Resource(name = "historywarningService")
	private HistoryWarningManager historywarningService;

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
		List<PageData> varList = historywarningService.list(page); // 列出OrdinaryMember列表

		mv.setViewName("weixin/historywarning/historywarning_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
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
		logBefore(logger, Jurisdiction.getUsername() + "修改orderwarning");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		historywarningService.updateDrById(pd);
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
		logBefore(logger, Jurisdiction.getUsername() + "批量删除orderwarning");
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

			historywarningService.updateAllDr(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

}
