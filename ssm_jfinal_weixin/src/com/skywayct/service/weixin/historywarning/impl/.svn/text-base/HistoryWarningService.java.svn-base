package com.skywayct.service.weixin.historywarning.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.historywarning.HistoryWarningManager;
import com.skywayct.util.PageData;

@Service("historywarningService")
public class HistoryWarningService implements HistoryWarningManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("HistoryWarningMapper.datalistPage", page);
	}
	
	/**
	 * 根据id修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateDrById(PageData pd) throws Exception {
		dao.update("HistoryWarningMapper.updateDrById", pd);
	}

	/**
	 * 根据id批量修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception {
		dao.update("HistoryWarningMapper.updateAllDr", arrayDATA_IDS);
	}

}
