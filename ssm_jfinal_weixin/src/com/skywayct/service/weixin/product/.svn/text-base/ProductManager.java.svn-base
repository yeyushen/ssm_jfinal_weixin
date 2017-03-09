package com.skywayct.service.weixin.product;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.entity.weixin.Operator;
import com.skywayct.util.PageData;

/**
 * 说明： 产品信息接口类 创建人： 叶育生 创建时间：2015-05-10
 * 
 * @version
 */
public interface ProductManager {

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception;

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception;

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd) throws Exception;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception;
	
	/**
	 * 通过pid获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByPid(PageData pd) throws Exception;

	/**
	 * 根据id修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateDrById(PageData pd) throws Exception;
	
	/**
	 * 根据传入数字批量修改价格
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateManyCostPrice(PageData pd) throws Exception;
	/**
	 * 修改全部价格
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateAllCostPrice(PageData pd) throws Exception;

	/**
	 * 通过ID获取其子级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Operator> listSubOperatorByParentId(String parentId)
			throws Exception;

	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * 
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Operator> listAllOperator(String parentId) throws Exception;
	
	/**批量产品上架
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateProductStatus1(String[] arrayDATA_IDS) throws Exception;
	
	/**批量产品下架
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateProductStatus2(String[] arrayDATA_IDS) throws Exception;
	
	/**批量产品压单
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateProductStatus3(String[] arrayDATA_IDS) throws Exception;
	
	/**批量产品取消压单
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateProductStatus4(String[] arrayDATA_IDS) throws Exception;

}
