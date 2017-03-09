package com.skywayct.service.weixin.discount.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.discount.DiscountManager;

/** 
 * 说明： 分销返佣比例
 * 创建人： 叶育生
 * 创建时间：2016-05-12
 * @version
 */
@Service("discountService")
public class  DiscountService implements DiscountManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData list(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DiscountMapper.list", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("DiscountMapper.edit", pd);
	}
	

}

