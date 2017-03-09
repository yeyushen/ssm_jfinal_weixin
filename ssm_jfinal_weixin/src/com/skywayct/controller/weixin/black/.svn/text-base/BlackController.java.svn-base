package com.skywayct.controller.weixin.black;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.black.impl.BlackService;
import com.skywayct.service.weixin.membertransfer.MemberTransferManager;
import com.skywayct.service.weixin.ordinarymember.OrdinaryMemberManager;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;
import com.skywayct.util.weixin.WXTagUtil;

/**
 * 说明：黑名单管理控制器 创建人： 叶育生 创建时间：2016-06-21
 */
@Controller
@RequestMapping(value = "/black")
public class BlackController extends BaseController {
	String menuUrl = "black/list.do"; // 菜单地址(权限用)
	@Resource(name = "blackService")
	private BlackService blackService;
	@Resource(name = "membertransferService")
	private MemberTransferManager membertransferService;
	@Resource(name = "ordinarymemberService")
	private OrdinaryMemberManager ordinarymemberService;

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
		String is_black = pd.getString("is_black"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = blackService.list(page); // 列出BlackMember列表

		mv.setViewName("weixin/black/black_list");
		if (is_black != null && is_black.equals("0")) {
			pd.put("is_black", 1);
		} else if (is_black != null && is_black.equals("3")) {
			pd.put("is_black", 3);
		} else {
			pd.put("is_black", 2);
		}
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}

	/**
	 * 去黑名单页面
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/goBlack")
	public ModelAndView goBlack(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表MemberTransfer");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// 校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String ArrayDATA_IDS[] = DATA_IDS.split(",");
		List<PageData> varList = membertransferService.listManay(ArrayDATA_IDS);// 列出所选择的会员(根据放在数组里的openid批量查询)
		mv.setViewName("weixin/black/black_edit");
		mv.addObject("varList", varList);
		mv.addObject("DATA_IDS", DATA_IDS);
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 拉进黑名单
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "拉进黑名单");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String news = null;
		Integer errcode = null;
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			StringBuilder sb = new StringBuilder();
			String openid = null;
			for (int i = 0; i < ArrayDATA_IDS.length; i++) {
				if (i == ArrayDATA_IDS.length - 1) {
					openid = "\"" + ArrayDATA_IDS[i] + "\"";
				} else {
					openid = "\"" + ArrayDATA_IDS[i] + "\",";
				}
				sb.append(openid);
			}
			String tag = "{\"openid_list\":[" + sb + "],\"tagid\":2}";// 因为星标组tagid固定为2，避免微信端新建标签时的tagid不固定，所以用星标组用来作为黑名单组
			String message = WXTagUtil.addTagToU(tag);
			JSONObject jsonMessage = JSONObject.fromObject(message);
			errcode = (Integer) jsonMessage.get("errcode");
			if(errcode==0){
				ordinarymemberService.setMemberBlack(ArrayDATA_IDS);
				news = "成功拉进黑名单";
			}else{
				news = "拉进黑名单失败(会员标签已超过3个，请移除再操作)";
			}
		}else{
			news = "失败，请选择会员";
		}
		// ordinarymemberService.save(pd);
		mv.addObject("news", news);
		mv.addObject("msg", "success");
		mv.setViewName("weixin/black/result");
		return mv;
	}

	/**
	 * 移除黑名单
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/remove")
	public ModelAndView remove() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "移除黑名单");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String news = null;
		Integer errcode = null;
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			StringBuilder sb = new StringBuilder();
			String openid = null;
			for (int i = 0; i < ArrayDATA_IDS.length; i++) {
				if (i == ArrayDATA_IDS.length - 1) {
					openid = "\"" + ArrayDATA_IDS[i] + "\"";
				} else {
					openid = "\"" + ArrayDATA_IDS[i] + "\",";
				}
				sb.append(openid);
			}
			String tag = "{\"openid_list\":[" + sb + "],\"tagid\":2}";// 因为星标组tagid固定为2，避免微信端新建标签时的tagid不固定，所以用星标组用来作为黑名单组
			String message = WXTagUtil.delTagToU(tag);
			JSONObject jsonMessage = JSONObject.fromObject(message);
			errcode = (Integer) jsonMessage.get("errcode");
			if(errcode==0){
				ordinarymemberService.removeMemberBlack(ArrayDATA_IDS);
				news = "成功移除黑名单";
			}else{
				news = "移除黑名单失败";
			}
		}else{
			news = "失败，请选择会员";
		}
		// ordinarymemberService.save(pd);
		mv.addObject("news", news);
		mv.addObject("msg", "success");
		mv.setViewName("weixin/black/result");
		return mv;
	}

	public static void main(String[] args) {
		/*
		 * String DATA_IDS = "aa,bb,cc"; String ArrayDATA_IDS[] =
		 * DATA_IDS.split(","); StringBuilder sb = new StringBuilder(); String
		 * openid = null; for (int i = 0; i < ArrayDATA_IDS.length; i++) { if (i
		 * == ArrayDATA_IDS.length - 1) { openid = "\"" + ArrayDATA_IDS[i] +
		 * "\""; } else { openid = "\"" + ArrayDATA_IDS[i] + "\","; }
		 * sb.append(openid); } System.out.println(sb); String tag1 =
		 * "{\"openid_list\":[" + sb + "],\"tagid\":2}";
		 * System.out.println(tag1);
		 */
		String tag = "{\"tagid\":2}";
		String message = "{\"errcode\":0,\"errmsg\":\"ok\"}";
		System.out.println(message);
		JSONObject jsonMessage = JSONObject.fromObject(message);
		Integer errcode = (Integer) jsonMessage.get("errcode");
		System.out.println(errcode);
	}
}
