/*package com.skywayct.controller.weixin.department1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ucap.common.util.json.OutputUtil;
import com.ucap.common.util.string.StringUitl;
import com.ucap.upams.dao.persist.Condition;
import com.ucap.upams.dao.persist.Sort;
import com.ucap.upams.dao.persist.Condition.Paramerter;
import com.ucap.upams.model.sys.CodeDict;
import com.ucap.upams.model.sys.SysDept;
import com.ucap.upams.model.sys.SysRoleGroup;
import com.ucap.upams.model.sys.SysUser;
import com.ucap.upams.service.sys.ICodeDictService;

import com.ucap.upams.util.Constants;
import com.ucap.upams.util.TreeDto;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/pages/sys/backstage")
public class DictTreeController {

	protected static Logger logger = LoggerFactory
			.getLogger(DictTreeController.class);

	private int pageSize = 15;
	
	@Autowired
	private ICodeDictService codeDictService;
	
	@RequestMapping(value = "/dateDict", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView userManage(ModelAndView mav, HttpServletRequest request) {
		
		String viewName = request.getRequestURI().substring(
				request.getContextPath().length());
		
		mav.setViewName(viewName);

		return mav;
	}
	
	
	@RequestMapping(value = "/dateDictList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView dateDictList(ModelAndView mav,
			HttpServletRequest request,String dictName,String dictCode,Long fatherDictId,
			Integer currentPage) {
		
		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}
		
		String viewName = request.getRequestURI().substring(
				request.getContextPath().length());
		
		mav.setViewName(viewName);
		
		int offset = (Integer.valueOf(currentPage) - 1) * pageSize;
		
		Condition condition = new Condition() ;
		
		String dictName_decode = StringUitl.parseStringUTF8(dictName);
		
		if (fatherDictId != null && fatherDictId!=0) {
			
			condition.add(new Paramerter("codeDict.dictId", Condition.Operator.EQ , fatherDictId));
			
		} else if(fatherDictId != null && fatherDictId==0){
			condition.add(new Paramerter("codeDict.dictId", Condition.Operator.ISNULL,
					""));
		}
		
		
			
		if (StringUitl.checkStrNotNull(dictName)) {
		
		condition.add(new Paramerter("dictName", Condition.Operator.LIKE , dictName_decode));
		
		}
		
		if (StringUitl.checkStrNotNull(dictCode)) {
		
		condition.add(new Paramerter("dictCode", Condition.Operator.LIKE , dictCode));
		
		}
		
		condition.add(new Paramerter("state", Condition.Operator.EQ,
				Constants.DICT_STATE_COMMON));
		
		List<CodeDict> dateDicts = codeDictService.findByCondition(condition,null,offset,pageSize);
		
		System.out.println(fatherDictId);
		
		System.out.println(dateDicts.size());
		
		int count = codeDictService.count(condition);
		
		int countPage = StringUitl.getPageCount(
				codeDictService.count(condition), pageSize);
	
		mav.addObject("dateDicts", dateDicts);
		
		mav.addObject("dictName",dictName_decode);
		
		mav.addObject("dictCode",dictCode);
		
		mav.addObject("fatherDictId", fatherDictId);

		mav.addObject("currentPage", currentPage);
		
		mav.addObject("countPage", countPage);
		
		mav.addObject("count", count);
		
		return mav;
	}
	
	@RequestMapping(value = "/delDict", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void delDict(HttpServletRequest request,
			HttpServletResponse response, Long dictId) {
		 
		CodeDict dateDict = codeDictService.findById(dictId);
		
		dateDict.setState(Constants.DICT_STATE_NOUSE);

		codeDictService.update(dateDict);

		OutputUtil.jsonOutPut(response, true, "删除用户成功");
	}
	
	
	
	@RequestMapping(value = "/dictTree", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void dictTree(HttpServletRequest request,
			HttpServletResponse response) {
		
		Condition con = new Condition();
		
		con.add(new Paramerter("dictLevel", Condition.Operator.EQ,
				Constants.DICT_LEVEL_1));

		con.add(new Paramerter("state", Condition.Operator.EQ,
				Constants.DICT_STATE_COMMON));
		
		List<CodeDict> dateDicts = codeDictService.findByCondition(con);
		
		List<TreeDto> lstTree = new ArrayList<TreeDto>();
		
		TreeDto td = new TreeDto();
		td.setId("0");
		td.setName("所有部门");
		td.setOpen(true);// 节点是否打开
		td.setNocheck(true);
		lstTree.add(td);
		
		for (int i = 0; i < dateDicts.size(); i++) {
			dgDict(dateDicts.get(i), lstTree);
		}
		
		String result = JSONArray.fromObject(lstTree).toString();
		OutputUtil.jsonOutPut(response, true, result);
	}
	
	*//**
	 * 递归获取树状部门数据
	 * 
	 * @return
	 *//*
	public List<TreeDto> dgDict(CodeDict cd, List<TreeDto> lstTree) {
		TreeDto td = new TreeDto();
		td.setId(String.valueOf(cd.getDictId()));
		if (cd.getCodeDict() != null) {
			td.setpId(cd.getCodeDict().getDictId());
		} else {
			td.setpId(0);
		}
		td.setName(cd.getDictName());
		td.setOpen(true);// 节点是否打开
		lstTree.add(td);
		Iterator it = (Iterator) cd.getCodeDicts().iterator();
		while (it.hasNext()) {
			dgDict((CodeDict) it.next(), lstTree);
		}
		return lstTree;
	}
	
	
	
	@RequestMapping(value = "/addDict", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView addDict(ModelAndView mav,
			HttpServletRequest request, Long fatherDictId) {
		String viewName = request.getRequestURI().substring(
				request.getContextPath().length());
		mav.setViewName(viewName);
		
		if(fatherDictId != null && fatherDictId!=0){
			CodeDict codeDict = codeDictService.findById(fatherDictId);
	
			mav.addObject("codeDict", codeDict);
		}
		
		System.out.println(fatherDictId);
		
		return mav;
	}
	
	@RequestMapping(value = "/addDictUpdate", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void addDeptUpdate(HttpServletRequest request,
			HttpServletResponse response,Long deptId,String dictName,String dictCode,Long dictIdUp) {
		CodeDict codeDict = new CodeDict();
		codeDict.setDictName(dictName);
		codeDict.setDictCode(dictCode);
		CodeDict codeDictUp = codeDictService.findById(dictIdUp);
		if(codeDictUp != null){
			codeDict.setCodeDict(codeDictUp);
			codeDict.setDictLevel(codeDictUp.getDictLevel()+1);
		}else {
			codeDict.setCodeDict(codeDictUp);
			codeDict.setDictLevel(0);
		}
		codeDict.setCreateTime(new Date());
		codeDict.setState(Constants.DICT_STATE_COMMON);
		
		codeDictService.save(codeDict);
		
		OutputUtil.jsonOutPut(response, true, "保存字典成功");
	}
		
}
*/