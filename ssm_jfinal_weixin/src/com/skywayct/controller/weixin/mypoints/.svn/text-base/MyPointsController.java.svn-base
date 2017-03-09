package com.skywayct.controller.weixin.mypoints;

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
import com.skywayct.service.weixin.mypoints.MyPointsManager;
import com.skywayct.service.weixin.mywallet.MyWalletManager; 

/**
 * 说明：我的钱包 创建人： 叶育生 创建时间：2016-05-06
 */
@Controller
@RequestMapping(value = "/mypoints")
public class MyPointsController extends BaseController {

	String menuUrl = "mypoints/list.do"; // 菜单地址(权限用)
	@Resource(name = "mypointsService")
	private MyPointsManager mypointsService;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表mywallet");
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
		List<PageData> varList = mypointsService.list(page); 

		mv.setViewName("weixin/mypoints/mypoints_list");
		mv.addObject("varList", varList);

		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
