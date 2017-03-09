package com.skywayct.service.weixin.blackmember.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.blackmember.BlackMemberManager;
import com.skywayct.util.PageData;

@Service("blackmemberService")
public class BlackMemberService implements BlackMemberManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 列表
	 * 
	 * @param jsonArray
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList(
				"BlackMemberMapper.datalistPage", page);
	}

	/**
	 * 根据id批量将会员更新为黑名单
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateMemberToBlack(JSONArray jsonArray) throws Exception {
		dao.update("BlackMemberMapper.updateMemberToBlack", jsonArray);
	}

	/**
	 * 同步微信黑名单，如果会员不在微信端黑名单列表，则将后台是黑名单的会员移除黑名单
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateRemoveMemberBlack(JSONArray jsonArray) throws Exception {
		dao.update("BlackMemberMapper.updateRemoveMemberBlack", jsonArray);
	}

}
