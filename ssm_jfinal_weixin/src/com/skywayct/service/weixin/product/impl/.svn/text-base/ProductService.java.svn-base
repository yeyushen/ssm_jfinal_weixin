package com.skywayct.service.weixin.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.entity.weixin.Operator;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.product.ProductManager;

/**
 * 说明： 产品信息 创建人： 叶育生 创建时间：2015-05-10
 * 
 * @version
 */
@Service("productService")
public class ProductService implements ProductManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception {
		dao.save("ProductMapper.save", pd);
	}

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd) throws Exception {
		dao.delete("OperatorMapper.updateDr", pd);
	}

	/**
	 * 修改
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd) throws Exception {
		dao.update("ProductMapper.edit", pd);
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("ProductMapper.datalistPage",
				page);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ProductMapper.findById", pd);
	}
	
	/**
	 * 通过pid获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByPid(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ProductMapper.findByPid", pd);
	}

	/**
	 * 根据id修改dr的值由0变为1
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateDrById(PageData pd) throws Exception {
		dao.update("ProductMapper.updateDrById", pd);
	}

	/**
	 * 根据pid批量修改价格
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateManyCostPrice(PageData pd) throws Exception {
		dao.update("ProductMapper.updateManyCostPrice", pd);
	}
	
	/**
	 * 修改所有价格
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateAllCostPrice(PageData pd) throws Exception {
		dao.update("ProductMapper.updateAllCostPrice", pd);
	}

	/**
	 * 通过ID获取其子级列表
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Operator> listSubOperatorByParentId(String parentId)
			throws Exception {
		return (List<Operator>) dao.findForList(
				"ProductMapper.listSubOperatorByParentId", parentId);
	}

	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * 
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Operator> listAllOperator(String parentId) throws Exception {
		List<Operator> operatorList = this.listSubOperatorByParentId(parentId);
		for (Operator operator : operatorList) {
			operator.setTreeurl("pt/list.do?pid=" + operator.getId());
			operator.setSubOperator(this.listAllOperator(operator.getId()));
			operator.setTarget("treeFrame");
		}
		return operatorList;
	}
	
	/**批量产品上架
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateProductStatus1(String[] arrayDATA_IDS) throws Exception {		
		dao.update("ProductMapper.updateProductStatus1", arrayDATA_IDS);
	}
	
	/**批量产品上架
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateProductStatus2(String[] arrayDATA_IDS) throws Exception {		
		dao.update("ProductMapper.updateProductStatus2", arrayDATA_IDS);
	}

	@Override
	public void updateProductStatus3(String[] arrayDATA_IDS) throws Exception {
		dao.update("ProductMapper.updateProductStatus3", arrayDATA_IDS);
	}

	@Override
	public void updateProductStatus4(String[] arrayDATA_IDS) throws Exception {
		dao.update("ProductMapper.updateProductStatus4", arrayDATA_IDS);
	}

}
