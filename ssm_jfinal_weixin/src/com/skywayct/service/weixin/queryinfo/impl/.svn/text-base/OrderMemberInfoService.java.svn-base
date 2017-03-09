package com.skywayct.service.weixin.queryinfo.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.queryinfo.OrderMemberInfoManager;
import com.skywayct.util.PageData;

/** 
 * 说明： 查询统计
 * 创建人： 
 * 创建时间：2016-06-17
 * @version
 */
@Service("orderMemberInfoService")
public class OrderMemberInfoService implements OrderMemberInfoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 利用存储过程创建临时统计表
	 * @param pd
	 * @throws Exception
	 */	
	@Override
	public void createTb(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.findForObject("OrderMemberInfoMapper.createTempTb", pd);
	}
	
	/**代理会员统计列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> agentInfoList(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderMemberInfoMapper.agentInfolistPage", page);
	}
	
	
	/**二维码推广统计列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> qrcodeInfoList(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrderMemberInfoMapper.qrcodeInfolistPage", page);
	}	
	
	@Override
	public PageData countAgentInfo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) dao.findForObject("OrderMemberInfoMapper.countAgentInfo", pd);
	}

	@Override
	public PageData countQrcodeInfo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) dao.findForObject("OrderMemberInfoMapper.countQrcodeInfo", pd);
	}	
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrderMemberInfoMapper.listAll", pd);
	}
	
	/**
	 * 列出代理会员openid
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return  (List<PageData>)dao.findForList("OrderMemberInfoMapper.listAgentOpenId", pd);
	}

	/**
	 * 列出openid下所有子openId字符串
	 * @param pd
	 * @return
	 * @throws Exception
	 */	
	@Override
	public String listChildByPID(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (String) dao.findForObject("OrderMemberInfoMapper.getAllChildId", pd);
	}

	/**
	 * 列出代理会员下所有订单统计信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listChildInfo(Page page, int type) throws Exception {
		// TODO Auto-generated method stub
		List<PageData> tempList = null;
		switch(type){
		//全部
		case 0:
			tempList = (List<PageData>)dao.findForList("OrderMemberInfoMapper.childlistPage", page);
			break;
	//1级		
		case 1:
			tempList = (List<PageData>)dao.findForList("OrderMemberInfoMapper.ChildLv1listPage", page);
			break;
	//2级		
		case 2:
			tempList = (List<PageData>)dao.findForList("OrderMemberInfoMapper.ChildLv2listPage", page);
			break;
	//3级		
		case 3:
			tempList = (List<PageData>)dao.findForList("OrderMemberInfoMapper.ChildLv3listPage", page);
			break;		
		}
		return tempList;
	}
	@Override
	public PageData countChildInfo(PageData pd, int type)
			throws Exception {
		PageData tempPd = null;
		switch(type){
		//全部
		case 0:
			tempPd = (PageData)dao.findForObject("OrderMemberInfoMapper.countChildInfo", pd);
			break;
	//1级		
		case 1:
			tempPd = (PageData)dao.findForObject("OrderMemberInfoMapper.countChildLv1Info", pd);
			break;
	//2级		
		case 2:
			tempPd = (PageData)dao.findForObject("OrderMemberInfoMapper.countChildLv2Info", pd);
			break;
	//3级		
		case 3:
			tempPd = (PageData)dao.findForObject("OrderMemberInfoMapper.countChildLv3Info", pd);
			break;		
		}
		return tempPd;
	}	
	

	@Override
	public String findNameByOpenId(String openid) throws Exception {
		// TODO Auto-generated method stub
		return (String) dao.findForObject("OrderMemberInfoMapper.findNameByOpenId", openid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listChildDetails(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("OrderMemberInfoMapper.childDetaillistPage", page);
	}

	@Override
	public PageData findChildInfo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData)dao.findForObject("OrderMemberInfoMapper.childDetailInfo", pd);
	}

	
}

