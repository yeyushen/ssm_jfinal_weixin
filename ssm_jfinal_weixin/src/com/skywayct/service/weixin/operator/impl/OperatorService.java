package com.skywayct.service.weixin.operator.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.entity.weixin.Operator;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.operator.OperatorManager;

/**
 * 说明： 运营商信息接口类 创建人：叶育生 创建时间：2015-05-10
 * 
 * @version
 */
@Service("operatorService")
public class OperatorService implements OperatorManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 新增
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception {
		dao.save("OperatorMapper.save", pd);
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
		dao.update("OperatorMapper.edit", pd);
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		List<PageData> pagedataList = (List<PageData>) dao.findForList("OperatorMapper.datalistPage",page);
		
		return pagedataList;
	}

	/**
	 * 是否有关联的产品
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listProduct(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("OperatorMapper.listProduct",
				pd);
	}
	
	/**
	 * 列表(全部)
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("OperatorMapper.listAll", pd);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("OperatorMapper.findById", pd);
	}
	
	
	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAllDictionaries(Page page) throws Exception {
		return (List<PageData>) dao.findForList("OperatorMapper.listAllDictionaries",
				page);
	}
	

	/**
	 * 通过编码获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBianma(PageData pd) throws Exception {
		return (PageData) dao.findForObject("OperatorMapper.findByBianma", pd);
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
				"OperatorMapper.listSubOperatorByParentId", parentId);
	}
	
	
	/**
	 * 获取上级
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int intParentbyid(String parentId,int lv)
			throws Exception {
		List<Operator> operatorList = this.listParentbyid(parentId);
		if(operatorList.size() < 1) return lv;
		for (Operator operator : operatorList) {
			lv = this.intParentbyid(operator.getParent_id(),lv+1);
		}
		return lv;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operator> listParentbyid(String parentId) throws Exception {
		return (List<Operator>)dao.findForList("OperatorMapper.listParentbyid", parentId);
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
			operator.setTreeurl("operator/list.do?id=" + operator.getId());
			operator.setSubOperator(this.listAllOperator(operator.getId()));
			operator.setTarget("treeFrame");
		}
		return operatorList;
	}
	

}
