package com.skywayct.service.weixin.mywallet.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.mywallet.MyWalletManager;
import com.skywayct.util.PageData;

@Service("mywalletService")
public class MyWalletService implements MyWalletManager {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MyWalletMapper.datalistPage", page);
	}

	@Override
	public void updateByOpenId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.findForList("MyWalletMapper.updateByOpenId", pd);
	}

	/**
	 * 是否存在记录
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isExist(String openId) throws Exception {
		// TODO Auto-generated method stub
		Integer existCount = (Integer) dao.findForObject("MyWalletMapper.isExistByOpenId", openId);
		if( existCount > 0 ){
			return true;
		}else{
		    return false;
		}
	}

	@Override
	public void save(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.save("MyWalletMapper.save", pd);
	}

	@Override
	public void updateDr(String open_id) throws Exception {
		// TODO Auto-generated method stub
		dao.update("MyWalletMapper.updateStatus", open_id);
	}

	@Override
	public PageData findByOpenId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData) dao.findForObject("MyWalletMapper.findByOpenId", pd);
	}

}
