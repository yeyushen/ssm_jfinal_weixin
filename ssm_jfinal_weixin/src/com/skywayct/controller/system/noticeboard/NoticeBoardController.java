package com.skywayct.controller.system.noticeboard;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jfinal.render.Render;
import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.system.noticeboard.NoticeBoardManager;
import com.skywayct.util.AppUtil;
import com.skywayct.util.ObjectExcelView;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.Tools;

/** 
 * 说明：公共栏
 * 创建人： 
 * 创建时间：2016-05-30
 */
@Controller
@RequestMapping(value="/noticeboard")
public class NoticeBoardController extends BaseController {
	
	String menuUrl = "noticeboard/list.do"; //菜单地址(权限用)
	@Resource(name="noticeboardService")
	private NoticeBoardManager noticeboardService;
	 
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改NoticeBoard");
		JSONObject obj = new JSONObject();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "edit") || Jurisdiction.buttonJurisdiction(menuUrl, "add"))
		{
			
			PageData pd = new PageData();
			pd = this.getPageData();
			String content = pd.getString("DATA_IDS");
			pd.put("content", content);
			pd.put("update_name", Jurisdiction.getUsername());
			List<PageData> pdList = noticeboardService.listAll(pd);
			if( pdList.size() > 0 ){
				noticeboardService.edit(pd);
			}else{
				noticeboardService.save(pd);
			}
			
			obj.put("msg", "success");
						
		}else{
			obj.put("msg", "error");
		} 
		return obj.toString();

	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表NoticeBoard");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData>	varList = noticeboardService.listAll(pd);	//列出NoticeBoard列表
		pd = varList.get(0);
		mv.setViewName("system/noticeboard/noticeboard_list");
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
