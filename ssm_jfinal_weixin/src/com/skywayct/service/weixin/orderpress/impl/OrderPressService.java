package com.skywayct.service.weixin.orderpress.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.order.OrderManager;
import com.skywayct.service.weixin.orderfail.OrderFailManager;
import com.skywayct.service.weixin.orderpress.OrderPressManager;
import com.skywayct.util.PageData;

/***
 * 压单列表服务类
 * @author hmf
 *
 */
@Service("orderPressService")
public class OrderPressService implements OrderPressManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**充值失败订单列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderPressMapper.datalistPage", page);
	}
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> countOrder(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderPressMapper.countOrder", page);
	}
	
}
