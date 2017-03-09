package com.skywayct.service.weixin.commission.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.commission.CommissionManager;
import com.skywayct.util.PageData;


/** 
 * 说明： 普通会员
 * 创建人： 
 * 创建时间：2016-04-19
 * @version
 */
@Service("commissionService")
public class CommissionService implements CommissionManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CommissionMapper.datalistPage", page);
	}
	
	/**根据id修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateDrById(PageData pd) throws Exception {	
		dao.update("CommissionMapper.updateDrById", pd);
	}
	
	/**根据id批量修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception {		
		dao.update("CommissionMapper.updateAllDr", arrayDATA_IDS);
	}

	/**
	 * 根据某字段名称和值返回list记录
	 * @param pd  必须有 filed key_value
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listByFiled(Page page) throws Exception {
		// TODO Auto-generated method stub
		return  (List<PageData>) dao.findForList("CommissionRecord.datalistPage", page);
	}

	@Override
	public void save(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.save("CommissionMapper.save", pd);
	}


}
