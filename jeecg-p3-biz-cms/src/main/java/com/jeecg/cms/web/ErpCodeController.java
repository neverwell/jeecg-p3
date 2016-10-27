package com.jeecg.cms.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
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

import com.jeecg.cms.dao.ErpCodeDao;
import com.jeecg.cms.entity.ErpCode;
import com.jeecg.cms.entity.ErpProduct;
import com.jeecg.cms.util.Reader18;

@Controller
@RequestMapping("/p3/cms/erpCode")
public class ErpCodeController extends BaseController {
	@Autowired
	private ErpCodeDao erpCodeDao;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public void list(@ModelAttribute ErpCode query, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
		try {
			LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<ErpCode> list = erpCodeDao.getAll(query, pageNo, pageSize);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("erpCode", query);
			velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
			String viewName = "cms/erpCode-list.vm";
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
	public void erpCodeDetail(@RequestParam(required = true, value = "id") Integer id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpCode-detail.vm";
		ErpCode erpCode = erpCodeDao.get(id);
		velocityContext.put("erpCode", erpCode);
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
		String viewName = "cms/erpCode-add.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute ErpCode erpCode) {
		AjaxJson j = new AjaxJson();
		try {
			erpCodeDao.insert(erpCode);
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
	public void toEdit(@RequestParam(required = true, value = "id") Integer id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		ErpCode erpCode = erpCodeDao.get(id);
		velocityContext.put("erpCode", erpCode);
		String viewName = "cms/erpCode-edit.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(params = "doEdit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute ErpCode erpCode) {
		AjaxJson j = new AjaxJson();
		try {
			erpCodeDao.update(erpCode);
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
	public AjaxJson doDelete(@RequestParam(required = true, value = "id") Integer id) {
		AjaxJson j = new AjaxJson();
		try {
			ErpCode erpCode = new ErpCode();
			erpCode.setId(id);
			erpCodeDao.delete(erpCode);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

}
