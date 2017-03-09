package com.skywayct.service.weixin.phonecharge;


import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface PhoneChargeOrderManager {
	
	/**所有话费订单列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;

	/**统计
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> countOrder(Page page)throws Exception;
	
	/**
	 * 导出订单信息到EXCEL
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllToExcel(PageData pd) throws Exception; 
	
	/**批量更新订单状态为成功
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateStatusSuccess(String[] arrayDATA_IDS) throws Exception;
	
	/**批量更新订单状态为失败
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateStatusFail(String[] arrayDATA_IDS) throws Exception;

}
