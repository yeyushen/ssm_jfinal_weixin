package com.skywayct.service.weixin.warningrecord.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.warningrecord.WarningRecordManager;
import com.skywayct.util.PageData;


@Service("warningrecordService")
public class WarningRecordService implements WarningRecordManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;


	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList(
				"WarningRecordMapper.datalistPage", page);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> query(Page page) throws Exception {
		return (List<PageData>) dao.findForList(
				"WarningRecordMapper.datalistPage", page);
	}
	
	public void update(PageData page) throws Exception{
		dao.findForList("WarningRecordMapper.update", page);
	}

}
