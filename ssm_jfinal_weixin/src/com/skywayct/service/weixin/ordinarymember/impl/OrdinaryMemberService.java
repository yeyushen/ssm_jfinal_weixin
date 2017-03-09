package com.skywayct.service.weixin.ordinarymember.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.ordinarymember.OrdinaryMemberManager;

/** 
 * 说明： 普通会员
 * 创建人： 
 * 创建时间：2016-04-19
 * @version
 */
@Service("ordinarymemberService")
public class OrdinaryMemberService implements OrdinaryMemberManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("OrdinaryMemberMapper.save", pd);
	}
	
	/**根据id把数据真正从数据库中删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("OrdinaryMemberMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("OrdinaryMemberMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("OrdinaryMemberMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("OrdinaryMemberMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("OrdinaryMemberMapper.findById", pd);
	}
	
	/**把数据真正从数据库中批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("OrdinaryMemberMapper.deleteAll", ArrayDATA_IDS);
	}

	/**根据id修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateDrById(PageData pd) throws Exception {	
		dao.update("OrdinaryMemberMapper.updateDrById", pd);
	}
	
	/**根据id批量修改dr的值由0变为1
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception {		
		dao.update("OrdinaryMemberMapper.updateAllDr", arrayDATA_IDS);
	}
	
	/**根据openid批量将会员拉进黑名单(修改is_black的值0)
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void setMemberBlack(String[] arrayDATA_IDS) throws Exception {		
		dao.update("OrdinaryMemberMapper.setMemberBlack", arrayDATA_IDS);
	}
	
	/**根据openid批量将会员移除黑名单(修改is_black的值为null)
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void removeMemberBlack(String[] arrayDATA_IDS) throws Exception {		
		dao.update("OrdinaryMemberMapper.removeMemberBlack", arrayDATA_IDS);
	}
	
	/**用户列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAllMember(Page page)throws Exception{
		return (List<PageData>) dao.findForList("OrdinaryMemberMapper.listAllMember", page);
	}

	/**
	 * 定时修改通过openId更新
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void updateByOpenId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("OrdinaryMemberMapper.updateByOpenId", pd);
		
	}

	/**
	 * 是否存在记录
	 * @param openId
	 * @return
	 */
	@Override
	public boolean isExist(String openId) throws Exception {
		// TODO Auto-generated method stub
		int existCount = (Integer) dao.findForObject("OrdinaryMemberMapper.isExistByOpenId", openId);
		if( existCount > 0 ){
			return true;
		}else{
		    return false;
		}
	}

	@Override
	public PageData findWalletByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		return  (PageData) dao.findForObject("OrdinaryMemberMapper.findWalletByOpenId", openId);
	}
	
	

}

