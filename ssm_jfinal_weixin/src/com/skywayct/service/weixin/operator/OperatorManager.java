package com.skywayct.service.weixin.operator;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.entity.weixin.Operator;
import com.skywayct.util.PageData;

/**
 * 说明： 运营商信息接口类 创建人：叶育生 创建时间：2015-05-10
 * 
 * @version
 */
public interface OperatorManager {

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
	 * 列表(全部)
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd) throws Exception;
	
	/**
	 * 是否有关联的产品
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> listProduct(PageData pd) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception;
	
	/**
	 * 列出地区
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAllDictionaries(Page page) throws Exception;

	/**
	 * 通过ID获取其子级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Operator> listSubOperatorByParentId(String parentId)
			throws Exception;
	
	public List<Operator> listParentbyid(String parentId) throws Exception;
	
	public int intParentbyid(String parentId,int lv) throws Exception; 

	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * 
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Operator> listAllOperator(String parentId) throws Exception;

}
