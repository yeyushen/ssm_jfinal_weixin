package com.skywayct.service.weixin.orderwarning.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.orderwarning.OrderWarningManager;

/**
 * 说明：订单预警设置接口 创建人： 创建时间：2016-06-16
 * 
 * @version
 */
@Service("orderwarningService")
public class OrderWarningService implements OrderWarningManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception {
		dao.save("OrderWarningMapper.save", pd);
	}

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd) throws Exception {
		dao.update("OrderWarningMapper.edit", pd);
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList(
				"OrderWarningMapper.datalistPage", page);
	}
	
	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("OrderWarningMapper.findById", pd);
	}


	/**
	 * 根据id修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateDrById(PageData pd) throws Exception {
		dao.update("OrderWarningMapper.updateDrById", pd);
	}

	/**
	 * 根据id批量修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception {
		dao.update("OrderWarningMapper.updateAllDr", arrayDATA_IDS);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrderWarningMapper.listAll", pd);
	}

}
