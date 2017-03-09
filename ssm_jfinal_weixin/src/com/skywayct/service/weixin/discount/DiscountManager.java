package com.skywayct.service.weixin.discount;

import com.skywayct.util.PageData;

/** 
 * 说明： 分销返佣比例接口
 * 创建人： 
 * 创建时间：2016-05-12
 * @version
 */
public interface  DiscountManager{

	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public PageData list(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	

}

