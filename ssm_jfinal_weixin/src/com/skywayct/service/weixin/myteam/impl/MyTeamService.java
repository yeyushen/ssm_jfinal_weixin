package com.skywayct.service.weixin.myteam.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.weixin.Member;
import com.skywayct.service.weixin.myteam.MyTeamManager;


/** 
 * 说明： 我的团队接口类
 * 创建人： 
 * 创建时间：2015-12-16
 * @version
 */
@Service("myteamService")
public class MyTeamService implements MyTeamManager {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Member> listSubMyTeamByParentId(String parentId) throws Exception {
		return (List<Member>) dao.findForList("DepartmentMapper.listSubDepartmentByParentId", parentId);
	}
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Member> listAllMyTeam(String parentId) throws Exception {
		List<Member> departmentList = this.listSubMyTeamByParentId(parentId);
		for(Member depar : departmentList){
			depar.setTreeurl("department/list.do?openid="+depar.getOpenid());
			depar.setSubMember(this.listAllMyTeam(depar.getOpenid()));
			depar.setTarget("treeFrame");
		}
		return departmentList;
	}




}
