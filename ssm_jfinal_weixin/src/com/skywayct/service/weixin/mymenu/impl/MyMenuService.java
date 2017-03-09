package com.skywayct.service.weixin.mymenu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.mymenu.MyMenuManger;
import com.skywayct.util.PageData;

@Service("mymenuService")
public class MyMenuService implements MyMenuManger {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>)dao.findForList("MyMenuMapper.datalistPage", page);
	}
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void save(PageData pd)throws Exception{
		dao.save("MyMenuMapper.save", pd);
	}

	@Override
	public void updateByMenuId(PageData pd) throws Exception {
		dao.findForList("MyMenuMapper.updateByMenuId", pd);
	}

	@Override
	public boolean isExist(String menuId) throws Exception {
		if(dao.findForObject("MyMenuMapper.isExistMenu", menuId) !=null){
			return true;
		}else{
		    return false;
		}
	}


}
