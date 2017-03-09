package com.skywayct.service.weixin.membertransfer.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.membertransfer.MemberTransferManager;

	/**
	 * 说明： 会员转移实现类
	 * 创建人： 叶育生
	 * 创建时间：2016-05-05
	 * @version
	 */
@Service("membertransferService")
public class MemberTransferService implements MemberTransferManager {

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
				"MemberTransferMapper.datalistPage", page);
	}

	/**
	 * 列出选择的会员
	 * @param
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listManay(String[] arrayDATA_IDS) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList(
				"MemberTransferMapper.listManay", arrayDATA_IDS);
	}

	/**
	 * 列表ByName
	 * 
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> findByName(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList(
				"MemberTransferMapper.findByName", page);
	}

	/**
	 * 根据id批量更新(批量更新父节点)
	 * 
	 * @param
	 * @throws Exception
	 */
	@Override
	public void updateAllParent(PageData pd) throws Exception {
		dao.update("MemberTransferMapper.updateAllParent", pd);
	}
	
	/**
	 * 插入转移操作者
	 * 
	 * @param
	 * @throws Exception
	 */
	@Override
	public void setOperator(PageData pd) throws Exception {
		dao.update("MemberTransferMapper.setOperator", pd);
	}

}
