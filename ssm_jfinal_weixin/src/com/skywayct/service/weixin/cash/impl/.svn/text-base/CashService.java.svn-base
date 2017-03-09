package com.skywayct.service.weixin.cash.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.cash.CashManager;
import com.skywayct.service.weixin.ordinarymember.OrdinaryMemberManager;

/** 
 * 说明： 普通会员
 * 创建人： 
 * 创建时间：2016-04-19
 * @version
 */
@Service("cashService")
public class CashService implements CashManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CashMapper.datalistPage", page);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CashMapper.findById", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("CashMapper.edit", pd);
	}

	/**根据id批量修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception {		
		dao.update("CashMapper.updateAllDr", arrayDATA_IDS);
	}
	

}

