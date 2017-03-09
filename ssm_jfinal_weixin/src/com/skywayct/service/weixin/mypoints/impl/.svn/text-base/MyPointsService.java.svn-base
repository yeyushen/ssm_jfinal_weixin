package com.skywayct.service.weixin.mypoints.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.mypoints.MyPointsManager;
import com.skywayct.service.weixin.mywallet.MyWalletManager;
import com.skywayct.util.PageData;

@Service("mypointsService")
public class MyPointsService implements MyPointsManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**列表
	 * @param page 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MyPointsMapper.datalistPage", page);
	}

	@Override
	public PageData findByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		return  (PageData) dao.findForObject("MyPointsMapper.findByOpenId", openId);
	}

}
