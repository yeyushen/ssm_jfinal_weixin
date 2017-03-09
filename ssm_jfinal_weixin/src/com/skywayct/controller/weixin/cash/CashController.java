package com.skywayct.controller.weixin.cash;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.util.AppUtil;
import com.skywayct.util.DateUtil;
import com.skywayct.util.ObjectExcelView;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.Tools;
import com.skywayct.util.UuidUtil;
import com.skywayct.service.weixin.cash.CashManager;
import com.skywayct.service.weixin.commission.CommissionManager;
import com.skywayct.service.weixin.memberlevel.MemberLevelManager;
import com.skywayct.service.weixin.mypoints.MyPointsManager;
import com.skywayct.service.weixin.mywallet.MyWalletManager;
import com.skywayct.service.weixin.ordinarymember.OrdinaryMemberManager;

/** 
 * 说明：普通会员
 * 创建人： 叶育生
 * 创建时间：2016-04-19
 */
@Controller
@RequestMapping(value="/ch")
public class CashController extends BaseController {
	
	String menuUrl = "cash/list.do"; //菜单地址(权限用)
	@Resource(name="cashService")
	private CashManager cashService;
	@Resource(name = "mywalletService")
	private MyWalletManager mywalletService;
	@Resource(name = "commissionService")
	private CommissionManager commissionService;
	@Resource(name = "mypointsService")
	private MyPointsManager mypointsService;	
	@Resource(name = "ordinarymemberService")
	private OrdinaryMemberManager ordinarymemberService;

	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表OrdinaryMember");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		String tempAsHead = pd.getString("money_scope_head");       //提现金额范围
		String tempAsEnd =  pd.getString("money_scope_end");
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		if( tempAsHead != null && !tempAsHead.equals("") ){
			pd.put("as_head", tempAsHead );
		}
		if( tempAsEnd != null && !tempAsEnd.equals("")){
			pd.put("as_end", tempAsEnd);
		}
		
		
		page.setPd(pd);
		List<PageData>	varList = cashService.list(page);	//列出OrdinaryMember列表
		
		mv.setViewName("weixin/cash/cash_list");
		mv.addObject("varList", varList);

		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		System.out.println(Jurisdiction.getHC());
		return mv;
	}
	
	/**
	 * 去修改页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = cashService.findById(pd); // 根据ID读取
		//List<PageData> levelList = cashService.listAll(null);// 查询所有级别，方便在编辑页面下拉框选择和显示
		mv.setViewName("weixin/cash/cash_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 去显示佣金明细界面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goCommission")
	public ModelAndView goCommission(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String open_id = pd.getString("open_id");
		
		if( open_id != null && !open_id.equals("")){
			pd.put("filed", "open_id");
			pd.put("key_value", open_id);
			
			String points = pd.getString("points");
			if( points == null || points.equals("")){
			PageData pointPd = mypointsService.findByOpenId(open_id);
			if( pointPd == null ){
				pd.put("points", 0);
			}else{
				pd.put("points", pointPd.get("points"));
			}
			}
			
			String wallet = pd.getString("wallet");
			if( wallet == null || wallet.equals("")){
			PageData walletPd = ordinarymemberService.findWalletByOpenId(open_id);
			if( walletPd == null ){
				pd.put("wallet", 0);
			}else{
				pd.put("wallet", walletPd.get("amount"));
			}
			}
			page.setPd(pd);
			
			List<PageData> csList = commissionService.listByFiled(page);
			
			mv.setViewName("weixin/commission/show_commission_record");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			mv.addObject("varList", csList);
			
			
		}else{
			
		    mv.addObject("msg", "error");
		    mv.setViewName("error_result");
		}

		return mv;
	}	
	
	
	
	
	
	
	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改Cash");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//增加判断不通过状态退款回钱包
		Integer nowStatus =  Integer.parseInt( pd.getString("status") );
		PageData cashPd = cashService.findById(pd);
		if( nowStatus == 3){
		   if( cashPd != null ){
			   Integer oldStatus = (Integer) cashPd.get("status");
			   if( nowStatus == 3 && nowStatus != oldStatus){
				  mywalletService.updateByOpenId(cashPd);
			   }
			
		    }
		}
		
		//增加通过后往佣金表写数据  2016-06-06 16:52:43
		if( nowStatus == 2){
			PageData walletPd = mywalletService.findByOpenId(cashPd);
			//钱包当前余额
			BigDecimal walletMoney = null ;
			if( walletPd != null  ){
			walletMoney = new BigDecimal((Double) walletPd.get("amount") );
			}else{
				walletMoney = new BigDecimal(0);
			}
			BigDecimal cashMoney = new BigDecimal((Double) cashPd.get("amount") );
			//提现后钱包余额
			PageData commissionPd = new PageData();
			commissionPd.put("commission_id", UuidUtil.get32UUID());
			commissionPd.put("open_id", cashPd.get("open_id"));
			commissionPd.put("commission", cashMoney.negate().setScale(8));
			commissionPd.put("balance", walletMoney.setScale(8) );
			commissionPd.put("create_date", new Date().getTime());
			commissionPd.put("order_id", cashPd.get("cash_id"));
			commissionPd.put("lv", 5);
			commissionPd.put("dr", 0);
			commissionService.save(commissionPd);
		}
		//通过或不通过加入审批时间
		if( nowStatus != 1 ){
			pd.put("approve", Jurisdiction.getUsername());
			pd.put("approve_time", DateUtil.fomatDateTiem(System.currentTimeMillis()));
		}
		
		cashService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}
	
	 /**批量删除，批量把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAllDr")
	@ResponseBody
	public Object updateAllDr() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除commission");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> list = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			
			cashService.updateAllDr(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		list.add(pd);
		map.put("list", list);
		return AppUtil.returnObject(pd, map);
	}	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
