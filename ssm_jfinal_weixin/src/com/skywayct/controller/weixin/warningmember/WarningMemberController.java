package com.skywayct.controller.weixin.warningmember;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.membertransfer.MemberTransferManager;
import com.skywayct.service.weixin.orderwarning.OrderWarningManager;
import com.skywayct.service.weixin.warningmember.WarningMemberManager;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;

/**
 * 说明：给会员添加订单预警选项 创建人： 叶育生 创建时间：2016-06-17
 */
@Controller
@RequestMapping(value = "/warningmember")
public class WarningMemberController extends BaseController {
	String menuUrl = "warningmember/list.do"; // 菜单地址(权限用)
	@Resource(name = "warningmemberService")
	private WarningMemberManager warningmemberService;
	@Resource(name = "orderwarningService")
	private OrderWarningManager orderwarningService;
	@Resource(name = "membertransferService")
	private MemberTransferManager membertransferService;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表warningmember");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		String warning_tagid = pd.getString("warning_tagid"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = warningmemberService.list(page); // 列出OrdinaryMember列表

		mv.setViewName("weixin/warningmember/warningmember_list");
		if (warning_tagid != null && warning_tagid.equals("0")) {
			pd.put("warning_tagid", 1);
		} else if (warning_tagid != null && warning_tagid.equals("3")) {
			pd.put("warning_tagid", 3);
		} else {
			pd.put("warning_tagid", 2);
		}
//		List<PageData> varList = orderwarningService.findDefaultWarning(page);
		mv.addObject("varList", varList);
//		mv.addObject("pg", pg);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}

	/**
	 * 去设置页面
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/goWarning")
	public ModelAndView goWarning(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表MemberTransfer");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// 校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		List<PageData> warninglList = orderwarningService.listAll(null);// 查询所有级别，方便在编辑页面下拉框选择和显示
		String ArrayDATA_IDS[] = DATA_IDS.split(",");
		List<PageData> varList = membertransferService.listManay(ArrayDATA_IDS); // 列出选择的会员(同listChoice)
		mv.setViewName("weixin/warningmember/warningmember_edit");
		mv.addObject("DATA_IDS", DATA_IDS);
		mv.addObject("warninglList", warninglList);
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 保存
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "新增tag");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String warning_tagid = pd.getString("warning_tagid");
		List<String> ls = new ArrayList<String>();
		if (null != warning_tagid && !"".equals(warning_tagid)) {
			pd.put("warning_tagid", warning_tagid);
			// 要操作的会员openID数组
			if (null != DATA_IDS && !"".equals(DATA_IDS)) {
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				for (int i = 0; i < ArrayDATA_IDS.length; i++) {
					ls.add(ArrayDATA_IDS[i]);
				}

				pd.put("idList", ls);
				warningmemberService.updateAllWarning(pd);
				mv.addObject("msg", "success");
				mv.setViewName("save_result");
			}
		}
		return mv;
	}

}
