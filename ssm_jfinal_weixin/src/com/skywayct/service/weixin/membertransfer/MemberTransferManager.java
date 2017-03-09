package com.skywayct.service.weixin.membertransfer;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/**
 * 说明： 普通会员接口 创建人： 创建时间：2016-04-19
 * 
 * @version
 */
public interface MemberTransferManager {

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 列出选择的会员
	 * 
	 * @param
	 * @throws Exception
	 */
	public List<PageData> listManay(String[] arrayDATA_IDS) throws Exception;

	/**
	 * 列表ByName
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> findByName(Page page) throws Exception;

	/**
	 * 根据id批量更新(批量更新父节点)
	 * 
	 * @param
	 * @throws Exception
	 */
	public void updateAllParent(PageData pd) throws Exception;
	/**
	 * 插入转移操作者
	 * 
	 * @param
	 * @throws Exception
	 */
	public void setOperator(PageData pd) throws Exception;

}
