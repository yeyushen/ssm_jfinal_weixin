package com.skywayct.controller.weixin.myteam;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.service.weixin.myteam.MyTeamManager;
import com.skywayct.util.PageData;

/**
 * 说明：组织机构 创建人： 创建时间：2015-12-16
 */
@Controller
@RequestMapping(value = "/myteam")
public class MyTeamController extends BaseController {

	String menuUrl = "myteam/list.do"; // 菜单地址(权限用)
	@Resource(name = "myteamService")
	private MyTeamManager myteamService;

	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAllMyTeam")
	public ModelAndView listAllMyTeam(Model model,String DEPARTMENT_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			JSONArray arr = JSONArray.fromObject(myteamService.listAllMyTeam("0"));
			String json = arr.toString();
			json = json.replaceAll("DEPARTMENT_ID", "id").replaceAll("PARENT_ID", "pId").replaceAll("NAME", "name").replaceAll("subDepartment", "nodes").replaceAll("hasDepartment", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("DEPARTMENT_ID",DEPARTMENT_ID);
			mv.addObject("pd", pd);	
			mv.setViewName("fhoa/department/department_ztree");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
}