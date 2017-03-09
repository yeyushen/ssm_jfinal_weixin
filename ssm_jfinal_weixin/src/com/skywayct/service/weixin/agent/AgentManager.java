package com.skywayct.service.weixin.agent;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/**
 * 说明： 代理查看会员 创建人：叶育生 创建时间：2016-06-2
 * 
 * @version
 */
public interface AgentManager {

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception;
}
