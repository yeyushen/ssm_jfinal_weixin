package com.skywayct.service.weixin.orderfail.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.order.OrderManager;
import com.skywayct.service.weixin.orderfail.OrderFailManager;
import com.skywayct.util.PageData;

/** 
 * 说明： 普通会员
 * 创建人： 
 * 创建时间：2016-04-19
 * @version
 */
@Service("orderFailService")
public class OrderFailService implements OrderFailManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**充值失败订单列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listFail(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderFailMapper.datalistPage", page);
	}
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> countFailOrder(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderFailMapper.countFailOrder", page);
	}
	
}
