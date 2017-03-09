package com.skywayct.service.weixin.agent.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.agent.AgentManager;
import com.skywayct.util.PageData;

/**
 * 说明： 代理查看会员 创建人：叶育生 创建时间：2016-06-2
 * 
 * @version
 */
@Service("agentService")
public class AgentService implements AgentManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("AgentMapper.datalistPage",
				page);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("AgentMapper.findById", pd);
	}
}
