package com.skywayct.service.weixin.warningmember;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/**
 * 说明：给会员添加订单预警选项接口 创建人： 叶育生 创建时间：2016-06-17
 */
public interface WarningMemberManager {

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 根据id批量更新(批量更新)
	 * 
	 * @param
	 * @throws Exception
	 */
	public void updateAllWarning(PageData pd) throws Exception;

}
