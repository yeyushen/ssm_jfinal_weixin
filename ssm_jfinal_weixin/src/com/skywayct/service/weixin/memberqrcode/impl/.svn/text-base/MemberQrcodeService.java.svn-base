package com.skywayct.service.weixin.memberqrcode.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;
import com.skywayct.service.weixin.memberqrcode.MemberQrcodeManager;

/** 
 * 说明： 用户永久二维码
 * 创建人： 
 * 创建时间：2016-05-30
 * @version
 */
@Service("memberqrcodeService")
public class MemberQrcodeService implements MemberQrcodeManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MemberQrcodeMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.update("MemberQrcodeMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MemberQrcodeMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberQrcodeMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberQrcodeMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(String openId)throws Exception{
		return (PageData)dao.findForObject("MemberQrcodeMapper.findById", openId);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.update("MemberQrcodeMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 通过opendId查找对应的场景ID
	 * @param opendid
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData findSceneIdByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		
		return (PageData)dao.findForObject("MemberQrcodeMapper.findSceneIdByOpenId", openId);
	}

	/**
	 * 没有场景ID的先保存
	 */
	@Override
	public void saveScene(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.save("MemberQrcodeMapper.save_scene", pd);
	}

	@Override
	public PageData findByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		return  (PageData) dao.findForObject("MemberQrcodeMapper.findByOpenId", openId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> findAllMember(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		 return (List<PageData>) dao.findForList("MemberQrcodeMapper.getAllMember", pd);
	}

	@Override
	public int findLastSceneId() throws Exception {
		// TODO Auto-generated method stub
		return  (Integer) dao.findForObject("MemberQrcodeMapper.findLastSceneId", "");
	}

	/**
	 * 打印二维码
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> findQrcodePic(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("MemberQrcodeMapper.findQrcode", ids);
	}

	/**
	 * 更改状态为已打印
	 * @param ids
	 * @throws Exception
	 */	
	@Override
	public void updatePrint(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		dao.update("MemberQrcodeMapper.updateIsPrint", ids);
	}
	
}

