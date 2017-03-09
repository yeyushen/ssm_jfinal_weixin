package com.skywayct.service.weixin.historywarning;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface HistoryWarningManager {
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**
	 * 根据id修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateDrById(PageData pd) throws Exception;

	/**
	 * 根据id批量修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception;

}
