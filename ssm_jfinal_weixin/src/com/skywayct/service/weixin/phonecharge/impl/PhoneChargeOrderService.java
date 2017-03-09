package com.skywayct.service.weixin.phonecharge.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.phonecharge.PhoneChargeOrderManager;
import com.skywayct.util.PageData;

/** 
 * 说明：话费订单
 * 创建人： 叶育生
 * 创建时间：2016-06-07
 * @version
 */
@Service("phonechargeService")
public class PhoneChargeOrderService implements PhoneChargeOrderManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**所有订单列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("PhoneChargeOrderMapper.datalistPage", page);
	}
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> countOrder(Page page)throws Exception{
		return (List<PageData>)dao.findForList("PhoneChargeOrderMapper.countOrder", page);
	}

	/**
	 * 导出订单信息到EXCEL
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listAllToExcel(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		
		return  (List<PageData>) dao.findForList("PhoneChargeOrderMapper.listAllToExcel", pd);
	}
	
	/**批量更新订单状态为成功
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateStatusSuccess(String[] arrayDATA_IDS) throws Exception {		
		dao.update("PhoneChargeOrderMapper.updateStatusSuccess", arrayDATA_IDS);
	}
	
	/**批量更新订单状态为失败
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateStatusFail(String[] arrayDATA_IDS) throws Exception {		
		dao.update("PhoneChargeOrderMapper.updateStatusFail", arrayDATA_IDS);
	}
	
}
