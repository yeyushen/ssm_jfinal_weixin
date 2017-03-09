package com.skywayct.controller.weixin.operator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.beetl.ext.fn.ParseInt;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.operator.OperatorManager;
import com.skywayct.util.AppUtil;
import com.skywayct.util.Jurisdiction;
import com.skywayct.util.PageData;

/**
 * 说明：运营商信息Controller 创建人：叶育生 创建时间：2015-05-10
 */
@Controller
@RequestMapping(value = "/operator")
public class OperatorController extends BaseController {

	String menuUrl = "operator/list.do"; // 菜单地址(权限用)
	@Resource(name = "operatorService")
	private OperatorManager operatorService;

	/*
	 * @Resource(name = "productService") private ProductManager productService;
	 */

	/**
	 * 保存
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "新增operator");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id", this.get32UUID()); // 主键
		operatorService.save(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam String id) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		logBefore(logger, Jurisdiction.getUsername() + "删除operator");
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd.put("id", id);
		String errInfo = "success";
		System.out.println(operatorService.listProduct(pd).size());
		if (operatorService.listSubOperatorByParentId(id).size() > 0) {// 判断是否有子级和判断改级是否有产品，是：不允许删除
			errInfo = "false1";
		} else if (operatorService.listProduct(pd).size() > 0) {
			errInfo = "false2";
		} else {
			operatorService.delete(pd); // 执行删除
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改operator");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		operatorService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表operator");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		String id = null == pd.get("id") ? "" : pd.get("id").toString();
		if (null != pd.get("id") && !"".equals(pd.get("id").toString())) {
			id = pd.get("id").toString();
		}
		pd.put("id", id); // 上级ID
		page.setPd(pd);
		List<PageData> varList = operatorService.list(page); // 列出Dictionaries列表
		
		mv.addObject("pd", operatorService.findById(pd)); // 传入上级所有信息
		mv.addObject("id", id); // 上级ID
		mv.setViewName("weixin/operator/operator_list");
		mv.addObject("varList", varList);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 显示列表ztree
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllOperator")
	public ModelAndView listAlloperator(Model model, String id)
			throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			JSONArray arr = JSONArray.fromObject(operatorService
					.listAllOperator("0"));
			String json = arr.toString();
			json = json.replaceAll("id", "id").replaceAll("parent_id", "pId")
					.replaceAll("type_name", "name")
					.replaceAll("subOperator", "nodes")
					.replaceAll("hasOperator", "checked")
					.replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("id", id);
			mv.addObject("pd", pd);
			mv.setViewName("weixin/operator/operator_ztree");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 去新增页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String id = null == pd.get("id") ? "" : pd.get("id").toString();
		int showwx = null == pd.get("showwx") ? 0 : Integer.parseInt(pd.getString("showwx"));
		pd.put("id", id); // 上级ID
		pd.put("is_show_wx", showwx);
		PageData pagedata = operatorService.findById(pd);
		int lv = operatorService.intParentbyid(pagedata.getString("id"),0);
		mv.addObject("level", lv);
		mv.addObject("pds", pagedata); // 传入上级所有信息
		List<PageData> dictionariesList = operatorService
				.listAllDictionaries(null);// 查询所有地区，方便在编辑页面下拉框选择和显示
		mv.addObject("id", id); // 传入ID，作为子级ID用
		mv.addObject("dictionariesList", dictionariesList);
		mv.setViewName("weixin/operator/operator_edit");
		mv.addObject("msg", "save");
		return mv;
	}

	/**
	 * 去修改页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String id = pd.getString("id");
		pd = operatorService.findById(pd); // 根据ID读取
		List<PageData> dictionariesList = operatorService
				.listAllDictionaries(null);// 查询所有地区，方便在编辑页面下拉框选择和显示
		mv.addObject("pd", pd); // 放入视图容器
		pd.put("id", pd.get("parent_id").toString()); // 用作上级信息
		
		// 计算当前类型所在级别，只有3级别的类型才可以设置是否在微信端显示
		int lv = operatorService.intParentbyid(pd.get("parent_id").toString(),0);
		mv.addObject("level", lv);
		
		mv.addObject("pds", operatorService.findById(pd)); // 传入上级所有信息
		mv.addObject("id", pd.get("parent_id").toString()); // 传入上级ID，作为子ID用
		mv.addObject("dictionariesList", dictionariesList);
		pd.put("id", id); // 父原本ID
		mv.setViewName("weixin/operator/operator_edit");
		mv.addObject("msg", "edit");
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
