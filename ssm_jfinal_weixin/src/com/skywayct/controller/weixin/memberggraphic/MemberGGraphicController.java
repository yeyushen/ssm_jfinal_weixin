package com.skywayct.controller.weixin.memberggraphic;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.service.weixin.membergeographic.MemberGGraphicManager;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;
import com.skywayct.util.Tools;

/***
 * 会员地理信息模块
 * @author hmf
 *
 */
@Controller
@RequestMapping(value = "/membergeographic")
public class MemberGGraphicController extends BaseController {

	String menuUrl = "membergeographic/list.do"; // 菜单地址(权限用)
	
	@Resource(name = "memberGGraphicService")
	private MemberGGraphicManager memberGGraphicService;

	
	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "会员地理位置信息MemberGGraphicController");
		 if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		 //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		List<PageData> ggraphicList = memberGGraphicService.listAll(pd);
		
		JSONArray ggraphicJson = JSONArray.fromObject(ggraphicList);
		
		mv.setViewName("weixin/membergeographic/member_geographic");

		mv.addObject("pd", pd);
		mv.addObject("mLocation", ggraphicJson);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}
	
	/**
	 * 搜索会员地理信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/search")
	@ResponseBody
	public Object searchByName(){
		logBefore(logger, Jurisdiction.getUsername() + "搜索会员地理位置信息MemberGGraphicController");
		PageData pd = this.getPageData();
		String member_name = pd.getString("member_name");
		JSONObject json = null;
		Map<String, String> map = new HashMap<String, String>();
		try{
		if( Tools.isEmpty(member_name) ){
			map.put("msg", "noname");
		}else{
			
			PageData mpd = memberGGraphicService.findByName(member_name);
			if( mpd != null ){
				map.put("msg", "success");
				map.put("lng", mpd.get("bdlng").toString());
				map.put("lat", mpd.get("bdlat").toString());
			}else{
				map.put("msg", "nodata");
			}	
		}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info(Jurisdiction.getUsername() + "MemberGGraphicController.search() error");
			map.put("msg", "error");
		}
		json = JSONObject.fromObject(map);
		
		
		return json.toString();
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
