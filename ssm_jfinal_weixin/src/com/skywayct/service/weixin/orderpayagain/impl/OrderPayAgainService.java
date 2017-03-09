package com.skywayct.service.weixin.orderpayagain.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.orderpayagain.OrderPayAgainManager;
import com.skywayct.util.PageData;

/***
 * 二次充值服务类
 * @author hmf
 *
 */
@Service("orderPayAgainService")
public class OrderPayAgainService implements OrderPayAgainManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**充值失败订单列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderPayAgainMapper.datalistPage", page);
	}
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> countPayAgain(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderPayAgainMapper.countPayAgainOrder", page);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listDetail(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("OrderPayAgainMapper.listDetails", page);
	}
	
}
