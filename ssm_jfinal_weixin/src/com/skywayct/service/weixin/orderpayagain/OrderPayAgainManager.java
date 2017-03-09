package com.skywayct.service.weixin.orderpayagain;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface OrderPayAgainManager {
	
	/**所有二次充值订单列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**统计
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> countPayAgain(Page page)throws Exception;
	
	/**
	 * 列出二次充值明细记录
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listDetail(Page page) throws Exception; 
	
	
}
