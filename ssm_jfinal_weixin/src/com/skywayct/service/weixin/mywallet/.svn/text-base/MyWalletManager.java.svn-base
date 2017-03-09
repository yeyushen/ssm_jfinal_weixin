package com.skywayct.service.weixin.mywallet;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface MyWalletManager {

	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	
	/**
	 * 通过openid更新钱包
	 * @param pd
	 * @throws Exception
	 */
	public void updateByOpenId(PageData pd) throws Exception;
	
	/**
	 * 是否存在记录
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(String openId) throws Exception;
	
	/**
	 * 保存
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception;
	
	/**
	 * 更改状态
	 * @param open_id
	 * @throws Exception
	 */
	public void updateDr(String open_id) throws Exception;
	
	
	public PageData findByOpenId(PageData pd) throws Exception;
	
	
	
}
