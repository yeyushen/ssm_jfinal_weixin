package com.skywayct.controller.weixin.queryinfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.queryinfo.OrderMemberInfoManager;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;
import com.skywayct.util.Tools;


/** 
 * 说明：二维码推广统计
 * 创建人： hmf
 * 
 */
@Controller
@RequestMapping(value="/qrcodeinfo")
public class QrcodeInfoController extends BaseController {
	
	String menuUrl = "qrcodeinfo/list.do"; //菜单地址(权限用)
	@Resource(name="orderMemberInfoService")
	private OrderMemberInfoManager orderMemberInfoService;
	
	
	/**会员代理下级所有订单统计列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView agentlist(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表QrcodeInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		//存储过程条件参数 1:名称|2:开始时间|3:结束时间|4:最小金额|5:最大金额
		String strWhere = "";
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		    strWhere = " AND `name` LIKE '%"+ keywords.replace("|", "") +"%' | ";	
		}else{
			strWhere = " |";
		}
		String startTime = pd.getString("lastStart");
		String endTime = pd.getString("lastEnd");
		String asHead = pd.getString("as_head");
		String asEnd = pd.getString("as_end");
		if( Tools.isEmpty( startTime )){
			strWhere += " |";
		}else{
			strWhere += " AND o.recharge_dttm >='" + startTime + "' | ";
		}
		if( Tools.isEmpty( endTime )){
			strWhere += " |";
		}else{
			strWhere += " AND o.recharge_dttm <='" + endTime + "' | ";
		}
		if( Tools.isEmpty( asHead )){
			strWhere += " |";
			pd.put("as_head", "");
		}else{
			strWhere += " AND child_ordermoney >=" + asHead + " | ";
		}		
		if( Tools.isEmpty( asEnd )){
			strWhere += " |";
			pd.put("as_end", "");
		}else{
			strWhere += " AND child_ordermoney <=" + asEnd + " | ";
		}
		pd.put("strWhere", strWhere);
		page.setPd(pd);
		
		pd.put("pro_name", "proc_order_qrcode_info");
		orderMemberInfoService.createTb(pd);
		List<PageData>	varList = orderMemberInfoService.qrcodeInfoList(page);	//列出QrcodeInfo列表
		PageData countPd = orderMemberInfoService.countQrcodeInfo(pd);
		
		
		pd.put("actionstr", "qrcodeinfo/list.do");
		pd.put("nextaction", "qrcodeinfo/agentalllist.do");
		mv.setViewName("weixin/queryinfo/order_member_info_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("countPd", countPd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	

	/**
	 * 查询某代理下级所有人
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/agentalllist")
	public ModelAndView agentAllLevelList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表QrcodeInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData countPd = null;
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String pOpenId = pd.getString("parentId");
		if( Tools.isEmpty(pOpenId) ){
			return null;
		}else{
			pd.put("openid", pOpenId);
			String pName = orderMemberInfoService.findNameByOpenId(pOpenId);
			pd.put("pName", pName);
		}
		int chooseType = 0;
		
		if( !Tools.isEmpty( pd.getString("chooseType") ) ){
			chooseType = Integer.parseInt(pd.getString("chooseType"));
		}
		
		List<PageData>	varList = null;
		//获取父级下级所有OpenId
		String childIds = orderMemberInfoService.listChildByPID(pd);
		if( !Tools.isEmpty( childIds )){
			String ArrayDATA_IDS[] = childIds.split(",");
			String tempIds = "";
			for (String str : ArrayDATA_IDS) {
				tempIds += "'" + str + "',";
						
			}
			
			pd.put("childIds", tempIds.substring(0, tempIds.length() - 1 ) );
			page.setPd(pd);

			
			varList = orderMemberInfoService.listChildInfo(page, chooseType);
			countPd = orderMemberInfoService.countChildInfo(pd, chooseType);
			
			
		}
		pd.put("actionstr", "qrcodeinfo/agentalllist");
		pd.put("returnstr", "qrcodeinfo/list.do");
		mv.setViewName("weixin/queryinfo/ordermember_childinfo_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("countPd", countPd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**
	 * 列出会员子会员详细
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/childdetaillist")
	public ModelAndView agentChildDetailList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表AgentDetailsList");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		
		page.setPd(pd);
		List<PageData>	varList = orderMemberInfoService.listChildDetails(page);
		mv.setViewName("weixin/queryinfo/member_order_show");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		
		return mv;
		
	}
	
	/**
	 * 没订单的显示会员详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/childdetailinfo")
	public ModelAndView childMemberInfo() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"会员个人信息");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();	
		
		PageData memberPd = orderMemberInfoService.findChildInfo(pd);
		mv.setViewName("weixin/queryinfo/member_info_show");
		mv.addObject("pd", memberPd);		
		return mv;
	}
	
	
	
	
	
}
