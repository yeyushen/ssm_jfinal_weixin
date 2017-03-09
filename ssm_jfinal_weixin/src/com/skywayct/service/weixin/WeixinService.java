package com.skywayct.service.weixin;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.skywayct.dao.DaoSupport;
import com.skywayct.entity.Page;
import com.skywayct.util.PageData;

/** 
 * 说明： weixin
 * 创建人： 
 * 创建时间：2016-04-28
 * @version
 */
@Service("weixinService")
public class WeixinService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("WeixinMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("WeixinMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("WeixinMapper.edit", pd);
	}
	
	/**列表
	 * @param at
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(PageData at)throws Exception{
		return (List<PageData>)dao.findForList("WeixinMapper.datalistPage", at);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WeixinMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WeixinMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("WeixinMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

