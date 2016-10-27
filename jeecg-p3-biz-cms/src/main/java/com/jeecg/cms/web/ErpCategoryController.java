package com.jeecg.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.cms.dao.ErpCategoryDao;
import com.jeecg.cms.entity.ErpCategory;
import com.jeecg.cms.util.SimpleTreeIdBuild;

/**
 * 描述：</b>ErpCategoryController<br>
 * 
 * @author p3.jeecg
 * @since：2016年06月13日 15时00分30秒 星期一
 * @version:1.0
 */
@Controller
@RequestMapping("/p3/cms/erpCategory")
public class ErpCategoryController extends BaseController {
	@Autowired
	private ErpCategoryDao erpCategoryDao;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public void list(@ModelAttribute ErpCategory query, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
		try {
			LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<ErpCategory> list = erpCategoryDao.getAll(query, pageNo, pageSize);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("erpCategory", query);
			velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
			String viewName = "cms/erpCategory-list.vm";
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 详情
	 * 
	 * @return
	 */
	@RequestMapping(params = "toDetail", method = RequestMethod.GET)
	public void cmsMenuDetail(@RequestParam(required = true, value = "id") String id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpCategory-detail.vm";
		ErpCategory erpCategory = erpCategoryDao.get(id);
		velocityContext.put("erpCategory", erpCategory);
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public void toAddDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpCategory-add.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute ErpCategory erpCategory) {
		AjaxJson j = new AjaxJson();
		try {
			if (StringUtil.isEmpty(erpCategory.getParentCode())) {// 无上级
				erpCategory.setId(new SimpleTreeIdBuild().getId(this.erpCategoryDao, null));
				erpCategory.setParentCode(null);
			} else {// 有上级
				erpCategory.setId(new SimpleTreeIdBuild().getId(this.erpCategoryDao, erpCategory.getParentCode()));
			}
			erpCategoryDao.insert(erpCategory);
			j.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toEdit", method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id") String id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		ErpCategory erpCategory = erpCategoryDao.get(id);
		velocityContext.put("erpCategory", erpCategory);
		String viewName = "cms/erpCategory-edit.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(params = "doEdit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute ErpCategory erpCategory) {
		AjaxJson j = new AjaxJson();
		try {
			erpCategoryDao.update(erpCategory);
			j.setMsg("编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDelete", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id") String id) {
		AjaxJson j = new AjaxJson();
		try {
			ErpCategory erpCategory = new ErpCategory();
			erpCategory.setId(id);
			erpCategoryDao.delete(erpCategory);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

	@RequestMapping(params = "tree",headers="Accept=application/json")
	@ResponseBody
	public List<ErpCategory> tree() {
		MiniDaoPage<ErpCategory> list = erpCategoryDao.getTree();
		List<ErpCategory> erpCategoryList = list.getResults();
		return erpCategoryList;
	}
	
//	@RequestMapping(params = "treeApi",headers="Accept=application/json")
//	@ResponseBody
//	public List<ErpCategory> treeApi() {
//		MiniDaoPage<ErpCategory> list = erpCategoryDao.getTree();
//		List<ErpCategory> erpCategoryList = list.getResults();
//		return erpCategoryList;
//	}
}
