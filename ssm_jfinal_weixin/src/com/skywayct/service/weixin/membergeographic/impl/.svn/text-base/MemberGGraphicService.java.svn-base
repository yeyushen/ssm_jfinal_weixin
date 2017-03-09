package com.skywayct.service.weixin.membergeographic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.service.weixin.membergeographic.MemberGGraphicManager;
import com.skywayct.util.PageData;

@Service("memberGGraphicService")
public class MemberGGraphicService implements MemberGGraphicManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/**列出所有坐标值
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listAll(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return  (List<PageData>) dao.findForList("MemberGeographic.listAll", pd);
	}

	/**
	 * 根据会员名字查找
	 * @param nick_name
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData findByName(String nick_name) throws Exception {
		// TODO Auto-generated method stub
		return  (PageData) dao.findForObject("MemberGeographic.findByName", nick_name);
	}

}
