package com.skywayct.service.weixin.commission;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;


/** 
 * 说明： 佣金快报接口
 * 创建人： 叶育生
 * 创建时间：2016-05-06
 * @version
 */
public interface CommissionManager {
	

	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**根据id修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateDrById(PageData pd) throws Exception;
	

	/**根据id批量修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception;
	
	/**
	 * 根据某字段名称和值返回list记录
	 * @param page  
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listByFiled(Page page) throws Exception;

	/**
	 * 保存记录
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception;
	
	

}
