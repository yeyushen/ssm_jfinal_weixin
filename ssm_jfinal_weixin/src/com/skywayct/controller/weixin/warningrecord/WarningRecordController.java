package com.skywayct.controller.weixin.warningrecord;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.entity.system.Role;
import com.skywayct.entity.system.User;
import com.skywayct.entity.weixin.OrderWarning;
import com.skywayct.service.system.menu.MenuManager;
import com.skywayct.service.system.role.RoleManager;
import com.skywayct.service.weixin.warningrecord.WarningRecordManager;
import com.skywayct.util.Const;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;
import com.skywayct.util.RightsHelper;

/**
 * 说明：给会员添加订单预警选项 创建人： 叶育生 创建时间：2016-06-17
 */
@Controller
@RequestMapping(value = "/warningrecord")
public class WarningRecordController extends BaseController {
	String menuUrl = "warningrecord/list.do"; // 菜单地址(权限用)
	@Resource(name = "warningrecordService")
	private WarningRecordManager warningrecordService;
	@Resource(name="roleService")
	private RoleManager roleService;
	@Resource(name="menuService")
	private MenuManager menuService;

	
	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表warningrecord");
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
		List<PageData> varList = warningrecordService.list(page); // 列出OrdinaryMember列表

		mv.setViewName("weixin/warningrecord/warningrecord_list");
		if (warning_tagid != null && warning_tagid.equals("0")) {
			pd.put("warning_tagid", 1);
		} else if (warning_tagid != null && warning_tagid.equals("3")) {
			pd.put("warning_tagid", 3);
		} else {
			pd.put("warning_tagid", 2);
		}
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}


	@RequestMapping(value="/query", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String query(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表warningrecord");
		Session session = Jurisdiction.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		Role role = roleService.getRoleById(user.getROLE_ID());
		String roleNewsRights = role.getNEWS_RIGHTS();	
		List<OrderWarning> warningList = menuService.listAllNewsQx("0");	//获取所有订单预警消息选项
		if(StrKit.isBlank(roleNewsRights)) return "[]";
		BigInteger num = new BigInteger(roleNewsRights);
		StringBuilder qxstr = new StringBuilder();
		for(OrderWarning war : warningList){
			if(num.testBit(Integer.parseInt(war.getWarning_id()))) 
				qxstr.append(war.getWarning_tagid()+",");
		}
		PageData pd = new PageData();
		pd.put("warning_tagid",qxstr.toString());
		pd.put("showuser", user.getUSER_ID());
		page.setPd(pd);
		List<PageData> warninglList = warningrecordService.query(page);
		JSONArray json  = JSONArray.fromObject(warninglList);
		return json.toString().replace("warning_", "").replace("warnrec_", "");
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public String update() throws Exception{
		PageData pd = new PageData();
		pd =this.getPageData();
		Session session = Jurisdiction.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		pd.put("showuser", user.getUSER_ID());
		warningrecordService.update(pd);
		return "success";
	}

}
