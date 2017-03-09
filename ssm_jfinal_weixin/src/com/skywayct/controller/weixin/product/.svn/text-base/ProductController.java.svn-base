package com.skywayct.controller.weixin.product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skywayct.controller.base.BaseController;
import com.skywayct.entity.Page;
import com.skywayct.service.weixin.operator.OperatorManager;
import com.skywayct.service.weixin.product.ProductManager;
import com.skywayct.util.AppUtil;
import com.skywayct.util.PageData;
import com.skywayct.util.Jurisdiction;

/**
 * 说明：产品信息 创建人：叶育生 创建时间：2015-05-10
 */
@Controller
@RequestMapping(value = "/pt")
public class ProductController extends BaseController {

	String menuUrl = "product/list.do"; // 菜单地址(权限用)
	@Resource(name = "productService")
	private ProductManager productService;
	@Resource(name = "operatorService")
	private OperatorManager operatorService;

	/**
	 * 保存
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "新增Product");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String profit = pd.getString("profit");
		String profit_price = pd.getString("profit_price");
		if (profit.equals("")) {
			pd.put("profit", 0);
		}
		if (profit_price.equals("")) {
			pd.put("profit_price", 0);
		}
		pd.put("product_id", this.get32UUID()); // 主键
		productService.save(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
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
		// String pid = null == pd.get("pid") ? "" : pd.get("pid").toString();
		// pd.put("pid", pid); // 上级ID
		// List<PageData> operatorList = operatorService.listAll(null);
		pd = productService.findById(pd); // 根据ID读取
		// List<PageData> operatorList = operatorService.listAll(null);//
		// 查询所有级别，方便在编辑页面下拉框选择和显示
		mv.setViewName("weixin/product/product_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		// mv.addObject("operatorList", operatorList);
		return mv;
	}

	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String profit = pd.getString("profit"); // 给mybatis里的xml判断是否为空
		String profit_price = pd.getString("profit_price"); // 给mybatis里的xml判断是否为空
		pd.put("profit", profit);
		pd.put("profit_price", profit_price);
		productService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除，把dr由0变为1
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDrById")
	public ModelAndView updateDrById() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		productService.updateDrById(pd);
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
		logBefore(logger, Jurisdiction.getUsername() + "列表product");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords"); // 检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		String pid = null == pd.get("pid") ? "" : pd.get("pid").toString();
		if (null != pd.get("pid") && !"".equals(pd.get("pid").toString())) {
			pid = pd.get("pid").toString();
		}
		pd.put("pid", pid); // 上级ID
		page.setPd(pd);
		List<PageData> varList = productService.list(page); // 列出Dictionaries列表
		mv.addObject("pd", productService.findById(pd)); // 传入上级所有信息
		mv.addObject("pid", pid); // 上级ID
		mv.setViewName("weixin/product/product_list");
		mv.addObject("varList", varList);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCostPrice")
	public ModelAndView updateCostPrice(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表operator");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String pct = pd.getString("pct"); // 检索条件
		String type = pd.getString("chooseType"); //修改选择 1：结算 2：成本 3：佣金比例
		Double doublePct = null;
		if (pct != null && !pct.equals("")) {
			try {
				doublePct = Double.parseDouble(pct);
				pd.put("doublePct", doublePct);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			String pid = null == pd.get("pid") ? "" : pd.get("pid").toString();
			if (null != pd.get("pid") && !"".equals(pd.get("pid").toString())) {
				pid = pd.get("pid").toString();
			}
			pd.put("pid", pid); // 上级ID
			if( type != null && !type.equals("")){
				pd.put("chooseType", type);
			}
			
			page.setPd(pd);
			if (pid != null && !pid.equals("")) {
				productService.updateManyCostPrice(pd); // 根据父节点批量更新价格
			} else {
				productService.updateAllCostPrice(pd);// 如果pid为空则更新全部产品价格
			}
			mv.addObject("msg", "success");
		} else {
			mv.addObject("msg", "修改失败");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 显示列表ztree
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllProduct")
	public ModelAndView listAllProduct(Model model, String id) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			JSONArray arr = JSONArray.fromObject(productService
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
			mv.setViewName("weixin/product/product_ztree");
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
		String pid = null == pd.get("pid") ? "" : pd.get("pid").toString();
		pd.put("pid", pid); // 上级ID
		pd = productService.findByPid(pd);
		// List<PageData> operatorList = operatorService.listAll(null);
		mv.addObject("pd", pd); // 传入上级所有信息
		mv.setViewName("weixin/product/product_edit");
		mv.addObject("msg", "save");
		// mv.addObject("pd", pd);
		// mv.addObject("operatorList", operatorList);
		return mv;
	}

	/**
	 * 批量产品上架
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProductStatus1")
	@ResponseBody
	public Object updateProductStatus1() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量更新订单状态为成功order");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");

			productService.updateProductStatus1(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 批量产品下架
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProductStatus2")
	@ResponseBody
	public Object updateProductStatus2() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量更新订单状态为成功order");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");

			productService.updateProductStatus2(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 批量产品压单
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProductStatus3")
	@ResponseBody
	public Object updateProductStatus3() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量更新产品为压单");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");

			productService.updateProductStatus3(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 批量产品取消压单
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProductStatus4")
	@ResponseBody
	public Object updateProductStatus4() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "批量更新产品为取消压单");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if (null != DATA_IDS && !"".equals(DATA_IDS)) {
			String ArrayDATA_IDS[] = DATA_IDS.split(",");

			productService.updateProductStatus4(ArrayDATA_IDS);
			pd.put("msg", "ok");
		} else {
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
