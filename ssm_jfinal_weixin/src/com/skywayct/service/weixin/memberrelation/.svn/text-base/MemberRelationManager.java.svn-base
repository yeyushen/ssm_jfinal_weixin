package com.skywayct.service.weixin.memberrelation;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.entity.system.Department;
import com.skywayct.entity.weixin.Member;
import com.skywayct.util.PageData;

/**
 * 说明： 会员关系 创建人： 创建时间：2015-12-16
 * 
 * @version
 */
public interface MemberRelationManager {

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 列表(全部)
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd) throws Exception;

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd) throws Exception;

	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Member> listSubMemberByParentId(String parentId) throws Exception;
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Member> listAllMember(String parentId) throws Exception;
	
}
