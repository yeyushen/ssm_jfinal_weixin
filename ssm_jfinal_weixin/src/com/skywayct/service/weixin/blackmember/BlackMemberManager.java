package com.skywayct.service.weixin.blackmember;

import java.util.List;

import net.sf.json.JSONArray;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

public interface BlackMemberManager {
	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 根据id批量将会员更新为黑名单
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateMemberToBlack(JSONArray jsonArray) throws Exception;

	/**
	 * 同步微信黑名单，如果会员不在微信端黑名单列表，则将后台是黑名单的会员移除黑名单
	 * 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateRemoveMemberBlack(JSONArray jsonArray) throws Exception;
}
