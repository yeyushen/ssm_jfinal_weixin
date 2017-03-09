package com.skywayct.service.weixin.memberlevel.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.memberlevel.MemberLevelManager;

/** 
 * 说明： 会员等级
 * 创建人： 
 * 创建时间：2016-04-26
 * @version
 */
@Service("memberlevelService")
public class MemberLevelService implements MemberLevelManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MemberLevelMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MemberLevelMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MemberLevelMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberLevelMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberLevelMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberLevelMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MemberLevelMapper.deleteAll", ArrayDATA_IDS);
	}

	/**删除，把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	@Override
	public void updateDrById(PageData pd) throws Exception {
		dao.update("MemberLevelMapper.updateDrById", pd);
		
	}

	/**批量删除，批量把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	@Override
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception {
		dao.update("MemberLevelMapper.updateAllDr", arrayDATA_IDS);
	}
	
}

