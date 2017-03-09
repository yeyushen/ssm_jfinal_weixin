package com.skywayct.service.weixin.memberrelation.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.entity.system.Department;
import com.skywayct.entity.weixin.Member;
import com.skywayct.service.weixin.memberrelation.MemberRelationManager;
import com.skywayct.util.PageData;

@Service("memberrelationService")
public class MemberRelationService implements MemberRelationManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList(
				"MemberRelationMapper.datalistPage", page);
	}

	/**
	 * 列表(全部)
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("MemberRelationMapper.listAll",
				pd);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao
				.findForObject("MemberRelationMapper.findById", pd);
	}

	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Member> listSubMemberByParentId(String parentId) throws Exception {
		return (List<Member>) dao.findForList("MemberRelationMapper.listSubMemberByParentId", parentId);
	}
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Member> listAllMember(String parentId) throws Exception {
		List<Member> memberList = this.listSubMemberByParentId(parentId);
		for(Member member : memberList){
			member.setTreeurl("memberrelation/list.do?openid="+member.getOpenid());
			member.setSubMember(this.listAllMember(member.getOpenid()));
			member.setTarget("treeFrame");
		}
		return memberList;
	}
}
