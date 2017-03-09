package com.skywayct.service.weixin.mypoints;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface MyPointsManager {

	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	
	/**
	 * 根据openId获取会员总积分
	 * @return
	 * @throws Exception
	 */
	public PageData findByOpenId(String openId) throws Exception;
}
