package com.skywayct.service.weixin.memberlevel;

import java.util.List;

import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/** 
 * 说明： 会员等级接口
 * 创建人： 
 * 创建时间：2016-04-26
 * @version
 */
public interface MemberLevelManager{

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
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;

	/**删除，把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	public void updateDrById(PageData pd) throws Exception;

	/**批量删除，批量把dr由0变为1
	 * @param
	 * @throws Exception
	 */
	public void updateAllDr(String[] arrayDATA_IDS) throws Exception;
	
}

