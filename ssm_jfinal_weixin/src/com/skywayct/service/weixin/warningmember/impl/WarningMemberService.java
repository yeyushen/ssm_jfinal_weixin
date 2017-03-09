package com.skywayct.service.weixin.warningmember.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.warningmember.WarningMemberManager;
import com.skywayct.util.PageData;

/**
 * 说明：给会员添加订单预警选项Service 创建人： 叶育生 创建时间：2016-06-17
 */
@Service("warningmemberService")
public class WarningMemberService implements WarningMemberManager {
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
				"WarningMemberMapper.datalistPage", page);
	}

	/**
	 * 根据id批量更新(批量更新父节点)
	 * 
	 * @param
	 * @throws Exception
	 */
	@Override
	public void updateAllWarning(PageData pd) throws Exception {
		dao.update("WarningMemberMapper.updateAllWarning", pd);

	}
}
