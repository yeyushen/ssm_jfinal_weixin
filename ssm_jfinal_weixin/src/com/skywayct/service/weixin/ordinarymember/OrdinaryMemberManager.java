package com.skywayct.service.weixin.ordinarymember;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/** 
 * 说明： 普通会员接口
 * 创建人： 
 * 创建时间：2016-04-19
 * @version
 */
public interface OrdinaryMemberManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**根据id把数据真正从数据库中删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**把数据真正从数据库中批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**列出所有会员
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllMember(Page page)throws Exception;

	/**根据id修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateDrById(PageData pd) throws Exception;

	/**根据id批量修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception;
	
	/**根据openid批量将会员拉进黑名单(修改is_black的值0)
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void setMemberBlack(String[] arrayDATA_IDS) throws Exception;
	
	/**根据openid批量将会员移除黑名单(修改is_black的值为null)
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void removeMemberBlack(String[] arrayDATA_IDS) throws Exception;
	
	/**
	 * 定时修改通过openId更新
	 * @param pd
	 * @throws Exception
	 */
	public void updateByOpenId(PageData pd) throws Exception;
	
	/**
	 * 是否存在记录
	 * @param openId
	 * @return
	 */
	public boolean isExist(String openId) throws Exception;
	
	/**
	 * 根据openid获取钱包信息
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public PageData findWalletByOpenId(String openId) throws Exception;

}

