package com.skywayct.service.weixin.profit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.order.OrderManager;
import com.skywayct.service.weixin.profit.ProfitManager;
import com.skywayct.util.PageData;

/** 
 * 说明： 普通会员
 * 创建人： 
 * 创建时间：2016-04-19
 * @version
 */
@Service("profitService")
public class ProfitService implements ProfitManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**所有订单列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProfitMapper.datalistPage", page);
	}
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> countProfit(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProfitMapper.countProfit", page);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> printProfit(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("ProfitMapper.printList", pd);
	}
	
}
