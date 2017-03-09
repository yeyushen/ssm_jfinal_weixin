package com.skywayct.service.weixin.myteam;

import java.util.List;

import com.skywayct.entity.weixin.Member;
import com.skywayct.util.PageData;

/** 
 * 说明： 我的团队接口类
 * 创建人： 
 * 创建时间：2015-12-16
 * @version
 */
public interface MyTeamManager {
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Member> listSubMyTeamByParentId(String parentId) throws Exception;
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Member> listAllMyTeam(String parentId) throws Exception;

}
