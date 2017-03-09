package com.skywayct.service.weixin.department1.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.entity.system.Department1;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.department1.DepartmentManager1;

/** 
 * 说明： 组织机构
 * 创建人： 
 * 创建时间：2015-12-16
 * @version
 */
@Service("departmentService1")
public class DepartmentService1 implements DepartmentManager1{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("DepartmentMapper1.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("DepartmentMapper1.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("DepartmentMapper1.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DepartmentMapper1.datalistPage", page);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DepartmentMapper1.findById", pd);
	}
	
	/**通过编码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByBianma(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DepartmentMapper1.findByBianma", pd);
	}
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Department1> listSubDepartmentByParentId(String parentId) throws Exception {
		return (List<Department1>) dao.findForList("DepartmentMapper1.listSubDepartmentByParentId", parentId);
	}
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Department1> listAllDepartment(String parentId) throws Exception {
		List<Department1> departmentList = this.listSubDepartmentByParentId(parentId);
		for(Department1 depar : departmentList){
			depar.setTreeurl("department1/list.do?DEPARTMENT_ID="+depar.getDEPARTMENT_ID());//为每个节点设置url
			depar.setSubDepartment(this.listAllDepartment(depar.getDEPARTMENT_ID()));//递归调用上面方法depar.getDEPARTMENT_ID()本部门节点，作为下级部门父节点
			depar.setTarget("treeFrame");
		}
		return departmentList;
	}
	
}

