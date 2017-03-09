package com.skywayct.service.weixin.agentprofit.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.agentprofit.AgentProfitManager;
import com.skywayct.util.PageData;
/** 
 * 说明： 
 * 创建人： 
 * 创建时间：
 * @version
 */
@Service("agentprofitService")
public class AgentProfitService implements AgentProfitManager {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**所有订单列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AgentProfit.datalistPage", page);
	}
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> countProfit(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AgentProfit.countProfit", page);
	}
	

}
