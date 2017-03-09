package com.skywayct.service.weixin.memberqrcode;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/** 
 * 说明： 用户永久二维码接口
 * 创建人： 
 * 创建时间：2016-05-30
 * @version
 */
public interface MemberQrcodeManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
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
	public PageData findById(String openId)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	
	/**
	 * 通过opendId查找对应的场景ID
	 * @param opendid
	 * @return
	 * @throws Exception
	 */
	public PageData findSceneIdByOpenId(String openId) throws Exception;
	
	/**
	 * 没有场景ID的先保存
	 * @param scene
	 * @return
	 * @throws Exception
	 */
	public void saveScene(PageData pd) throws Exception;
	
	/**
	 * 查找刚插入的最后ID
	 * @return
	 * @throws Exception
	 */
	public int findLastSceneId() throws Exception;
	
	
	/**
	 * 根据OpenId获取记录
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public PageData findByOpenId(String openId) throws Exception;
	
	/**
	 * 获取会员列表
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findAllMember(PageData pd) throws Exception;
	
	/**
	 * 打印二维码
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findQrcodePic(String[] ids) throws Exception;
	
	/**
	 * 更改状态为已打印
	 * @param ids
	 * @throws Exception
	 */
	public void updatePrint(String[] ids) throws Exception;
	
	
 	
}

