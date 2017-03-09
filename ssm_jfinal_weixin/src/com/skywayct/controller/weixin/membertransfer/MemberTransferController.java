package com.skywayct.controller.weixin.membertransfer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfinal.weixin.model.Member;
import com.jfinal.weixin.model.Statistics;
import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.entity.system.User;
import com.skywayct.util.Const;
import com.skywayct.util.DateUtil;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.service.weixin.membertransfer.MemberTransferManager;

/**
 * 说明：会员转移 创建人：叶育生 创建时间：2016-05-05
 */
@Controller
@RequestMapping(value = "/membertransfer")
public class MemberTransferController extends BaseController {

	String menuUrl = "membertransfer/list.do"; // 菜单地址(权限用)
	@Resource(name = "membertransferService")
	private MemberTransferManager membertransferService;

	/**
	 * 列出所有会员及上级
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表MemberTransfer");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// 校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = membertransferService.list(page); // 列出所有会员及上级

		mv.setViewName("weixin/membertransfer/membertransfer_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 列出所选择的会员
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/listChoice")
	public ModelAndView listChoice(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表MemberTransfer");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// 校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String ArrayDATA_IDS[] = DATA_IDS.split(",");
		List<PageData> varList = membertransferService.listManay(ArrayDATA_IDS);// 列出所选择的会员(根据放在数组里的openid批量查询)
		mv.setViewName("weixin/membertransfer/membertransfer_transferList");
		mv.addObject("varList", varList);
		mv.addObject("DATA_IDS", DATA_IDS);
		mv.addObject("msg", "listChoiceAndParent");// form表单里的action路径控制
		return mv;
	}

	/**
	 * 列出选择的会员和列出将要转移到的上级会员
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/listChoiceAndParent")
	public ModelAndView listChoiceAndParent(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表MemberTransfer");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// 校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String ArrayDATA_IDS[] = DATA_IDS.split(",");
		List<PageData> varList = membertransferService.listManay(ArrayDATA_IDS); // 列出选择的会员(同listChoice)
		String keywords = pd.getString("keywords"); // 关键词检索条件
		mv.setViewName("weixin/membertransfer/membertransfer_transferList");
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
			page.setPd(pd);
			List<PageData> varList1 = membertransferService.findByName(page); // 根据昵称查出会员列表(将要转移到的上级会员)
			mv.addObject("varList1", varList1);
		}
		mv.addObject("varList", varList);
		mv.addObject("DATA_IDS", DATA_IDS);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 根据id批量更新(批量更新父节点)
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAllParent")
	@ResponseBody
	public ModelAndView updateAllParent() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量删除OrdinaryMember");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String DATA_IDS = pd.getString("DATA_IDS");
		String openid = pd.getString("openid");
		List<String> ls = new ArrayList<String>();
		mv.setViewName("weixin/membertransfer/membertransfer_msg");
		String msg = null;
		if (null != openid && !"".equals(openid)) {
			pd.put("parent_id", openid);
            //要转移的会员openID数组
			if (null != DATA_IDS && !"".equals(DATA_IDS)) {
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				for (int i = 0; i < ArrayDATA_IDS.length; i++) {
					ls.add(ArrayDATA_IDS[i]);
				}

				pd.put("idList", ls);
				
				//转移前对级数表进行减操作
				subtractStatisticsLv(ls, openid);
				
				membertransferService.updateAllParent(pd);
				//转移前对级数表进行加操作
				addStatisticsLv(ls, openid);
				
				
				User user = (User) Jurisdiction.getSession().getAttribute(
						Const.SESSION_USER);
				pd.put("username", user.getUSERNAME());
				pd.put("to_transfer_openid", pd.getString("parent_id"));
				for (int i = 0; i < ArrayDATA_IDS.length; i++) {
					pd.put("transfer_record_id", this.get32UUID());
					pd.put("openid", ArrayDATA_IDS[i]);
					membertransferService.setOperator(pd);//插入操作者信息
				}
				
				msg = "转移成功";
			} else {
				msg = "转移失败";
			}
		} else {
			msg = "转移失败";
		}
		mv.addObject("msg", msg);
		return mv;
	}
	
	/**
	 * 转以后更新级数表 减操作
	 * @param tranOpenId
	 * @param parentId
	 */
	private void subtractStatisticsLv(List<String> tranOpenId, String parentId) throws Exception{
		
		Statistics parentS = null;
		Statistics childS  = null;
		String tempParentId = null;
		for (String openId : tranOpenId) {
			//若有父类父类父类 则对其对应的级别人数进行移除操作
				childS = Statistics.dao.findFirst("SELECT * FROM weixin_member_statistics WHERE openid='"+ openId +"' ");
				if( childS != null ){
				tempParentId = childS.getStr("parent_id");
				for(int i = 0; i < 3; i++){
					parentS =  Statistics.dao.findFirst("SELECT * FROM weixin_member_statistics WHERE openid='"+ tempParentId +"' ");
					if( parentS == null ) break;
					//第一级父类
					if( i == 0 ){
					parentS.set("lv1", parentS.getInt("lv1") - 1 );
					parentS.set("lv2", parentS.getInt("lv2") - childS.getInt("lv1") );
					parentS.set("lv3", parentS.getInt("lv3") - childS.getInt("lv2") );
					//第二级父类
					}else if( i == 1 ){
					parentS.set("lv2", parentS.getInt("lv2") - 1 );	
					parentS.set("lv3", parentS.getInt("lv3") - childS.getInt("lv1") );	
					//第三级父类
					}else if( i == 2 ){
					parentS.set("lv3", parentS.getInt("lv3") - 1 );	
					}
					parentS.update();
					tempParentId = parentS.getStr("parent_id");
				}
				
			//把新的父类openId进行指向
					childS.set("parent_id", parentId);
					childS.update();
				}else{
					childS = new Statistics();
					childS.set("openid", openId); 
					childS.set("parent_id", parentId);
					childS.set("create_date",DateUtil.fomatDateTiem(System.currentTimeMillis()));
					childS.set("lv1", 0);
			 		childS.set("lv2", 0);
			 		childS.set("lv3", 0);
			 		childS.save();					
				}
			}
		
	}
	/**
	 * 转以后更新级数表 加操作
	 * @param tranOpenId
	 * @param parentId
	 */	
	private void addStatisticsLv(List<String> tranOpenId, String parentId) throws Exception{
		Statistics childS = null;
		Statistics parentS = null;
		String tempParentId = null;
		//给父类 则对其对应的级别人数进行加操作
		for (String openId : tranOpenId) {
			childS = Statistics.dao.findFirst("SELECT * FROM weixin_member_statistics WHERE openid='"+ openId +"' ");
			tempParentId = parentId;
			for(int i = 0; i < 3; i++){
				parentS =  Statistics.dao.findFirst("SELECT * FROM weixin_member_statistics WHERE openid='"+ tempParentId +"' ");
				if( parentS == null ) break;
				//第一级父类
				if( i == 0 ){
				parentS.set("lv1", parentS.getInt("lv1") + 1 );
				parentS.set("lv2", parentS.getInt("lv2") + childS.getInt("lv1") );
				parentS.set("lv3", parentS.getInt("lv3") + childS.getInt("lv2") );
				//第二级父类
				}else if( i == 1 ){
				parentS.set("lv2", parentS.getInt("lv2") + 1 );	
				parentS.set("lv3", parentS.getInt("lv3") + childS.getInt("lv1") );	
				//第三级父类
				}else if( i == 2 ){
				parentS.set("lv3", parentS.getInt("lv3") + 1 );	
				}
				parentS.update();
				tempParentId = parentS.getStr("parent_id");
			}
			}			

		
	}
	
	

}
