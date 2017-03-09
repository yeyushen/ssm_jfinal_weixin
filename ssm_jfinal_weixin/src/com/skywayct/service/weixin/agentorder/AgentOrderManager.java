package com.skywayct.service.weixin.agentorder;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface AgentOrderManager {
	/**
	 * 代理会员查看下级订单列表订单列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 统计
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> countOrder(Page page) throws Exception;

}
