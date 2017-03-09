package com.skywayct.service.weixin.mymenu;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface MyMenuManger {
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	
	/**
	 * 通过menuId更新自定义菜单
	 * @param pd
	 * @throws Exception
	 */
	public void updateByMenuId(PageData pd) throws Exception;
	
	
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**
	 * 是否存在记录
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(String menuId) throws Exception;
	
}
