package com.skywayct.service.weixin.queryinfo;


import java.util.List;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/** 
 * 说明： 查询订单会员下级数据服务类
 * 创建人： 
 * 创建时间：
 * @version
 */
public interface OrderMemberInfoManager{

	
	/**
	 * 利用存储过程创建实体统计表
	 * @param pd
	 * @throws Exception
	 */
	public void createTb(PageData pd) throws Exception;
	/**代理会员统计信息列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> agentInfoList(Page page)throws Exception;
	
	/**
	 * 统计总的代理会员统计信息
	 * @return
	 * @throws Exception
	 */
	public PageData countAgentInfo(PageData pd) throws Exception;
	
	/**二维码推广信息统计列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> qrcodeInfoList(Page page)throws Exception;
	
	/**
	 * 统计总的二维码推广信息
	 * @return
	 * @throws Exception
	 */
	public PageData countQrcodeInfo(PageData pd) throws Exception;
	
	/**
	 * 列出代理会员openid
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listId(PageData pd) throws Exception;
	
	/**
	 * 列出openid下所有子openId字符串
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public String listChildByPID(PageData pd) throws Exception;
	
	
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**
	 * 列出代理会员下所有订单统计信息
	 * @param page
	 * @param type  0,1,2,3 分别查全部，1级，2级，3级 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listChildInfo(Page page, int type) throws Exception;
	/**
	 * 统计理会员下所有订单信息
	 * @param page
	 * @param type  0,1,2,3 分别查全部，1级，2级，3级 
	 * @return
	 * @throws Exception
	 */
	public PageData countChildInfo(PageData pd, int type) throws Exception;
	
	/***
	 * 查找会员名称
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public String findNameByOpenId(String openid) throws Exception;
	
	/**
	 * 列出代理会员某一下级的所有订单详细
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listChildDetails(Page page) throws Exception;
	
	/**
	 * 查询会员个人信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findChildInfo(PageData pd) throws Exception;
	
	
	
	
	
}

