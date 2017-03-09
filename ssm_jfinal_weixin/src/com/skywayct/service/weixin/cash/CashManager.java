package com.skywayct.service.weixin.cash;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/** 
 * 说明： 提现申请
 * 创建人： 
 * 创建时间：2016-05-18
 * @version
 */
public interface CashManager {
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**根据id批量修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception;

}
