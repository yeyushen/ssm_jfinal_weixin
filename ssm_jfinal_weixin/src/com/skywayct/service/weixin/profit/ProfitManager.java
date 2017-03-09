package com.skywayct.service.weixin.profit;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface ProfitManager {

	/**
	 * 所有订单列表
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
	public List<PageData> countProfit(Page page) throws Exception;
	
	/**
	 * 打印按时间分组
	 * @return
	 * @throws Exception
	 */
	public List<PageData> printProfit(PageData pd) throws Exception;
}