package com.jeecg.cms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.cms.dao.ErpCategoryDao;
import com.jeecg.cms.entity.ErpCategory;
import com.jeecg.cms.entity.ErpCode;

@Controller
@RequestMapping("/api/erpCategory")
public class ErpCategoryAPI extends BaseController {
	@Autowired
	private ErpCategoryDao erpCategoryDao;

	private Map<String, Object> result = new HashMap();

	@RequestMapping(params = "treeApi",headers="Accept=application/json")
	@ResponseBody
	public List<ErpCategory> treeApi() {
		MiniDaoPage<ErpCategory> list = erpCategoryDao.getTree();
		List<ErpCategory> erpCategoryList = list.getResults();
		return erpCategoryList;
	}
	
	
	
	/**
	 * 列表
	 * 
	 * @return
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tree", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> tree() {
		MiniDaoPage<ErpCategory> list = erpCategoryDao.getTree();
		List<ErpCategory> erpCategoryList = list.getResults();
		result.put("status", true);
		result.put("summary", "");
		result.put("data", erpCategoryList);
		return result;
	}


}
