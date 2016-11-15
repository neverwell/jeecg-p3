package com.jeecg.cms.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.aop.MiniDaoHandler;
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
import com.jeecg.cms.dao.ErpProductDao;
import com.jeecg.cms.entity.ErpCode;
import com.jeecg.cms.entity.ErpProduct;
import com.jeecg.cms.util.Reader18;

@Controller
@RequestMapping("/p3/cms/erpProduct")
public class ErpProductController extends BaseController {
	@Autowired
	private ErpProductDao erpProductDao;
	@Autowired
	private ErpCodeDao erpCodeDao;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public void list(@ModelAttribute ErpProduct query, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		try {
			LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<ErpProduct> list = erpProductDao.getAll(query, pageNo, pageSize);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("erpProduct", query);
			velocityContext.put("pageInfos", SystemTools.convertPaginatedList(list));
			String viewName = "cms/erpProduct-list.vm";
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 入库页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "inout", method = { RequestMethod.GET, RequestMethod.POST })
	public void in(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info(request, " inout page");
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-in.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 出库页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "out", method = { RequestMethod.GET, RequestMethod.POST })
	public void out(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info(request, " out page");
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-out.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 详情
	 * 
	 * @return
	 */
	@RequestMapping(params = "toDetail", method = RequestMethod.GET)
	public void erpProductDetail(@RequestParam(required = true, value = "id") String id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-detail.vm";
		ErpProduct erpProduct = erpProductDao.get(id);
		velocityContext.put("erpProduct", erpProduct);
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
		String viewName = "cms/erpProduct-add.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute ErpProduct erpProduct) {
		AjaxJson j = new AjaxJson();
		try {
			String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			erpProduct.setId(randomSeed);
			erpProduct.setInDate(new Date());
			erpProductDao.insert(erpProduct);
			j.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 批量保存信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doMultiAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doMultiAdd(@RequestParam(required = true, value = "columnId") String columnId) {
		/*
		 * 添加的时候需要添加产品列表数据信息
		 */
		List<ErpProduct> erpProducts = new ArrayList<ErpProduct>();
		Date now = new Date();
		for (int i = 0; i < 10; i++) {
			ErpProduct ep = new ErpProduct();
			String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			ep.setId(randomSeed);
			ep.setCode(randomSeed);
			ep.setColumnId(columnId);
			ep.setInDate(now);
			erpProducts.add(ep);
		}

		AjaxJson j = new AjaxJson();
		try {
			for (ErpProduct p : erpProducts) {
				// randomSeed = UUID.randomUUID().toString().replaceAll("-",
				// "").toUpperCase();
				// erpProduct.setId(randomSeed);
				erpProductDao.insert(p);
			}
			j.setMsg("批量保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("批量保存失败");
		}
		return j;
	}

	/**
	 * 入库界面，开始启动扫描
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "toInStart", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void toInStart(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true, value = "antenna") String antenna,
			@RequestParam(required = true, value = "columnId") String columnId,
			@RequestParam(required = false, value = "location") String location,
			@RequestParam(required = false, value = "model") String model,
			@RequestParam(required = false, value = "brand") String brand,
			@RequestParam(required = false, value = "name") String name) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-inStart.vm";
		velocityContext.put("antenna", antenna);
		velocityContext.put("columnId", columnId);
		velocityContext.put("location", location);
		velocityContext.put("model", model);
		velocityContext.put("brand", brand);
		velocityContext.put("name", name);
		try {
			Reader18.funcStart(antenna);
			velocityContext.put("status", antenna + "正在扫描");
		} catch (Exception e) {
			e.printStackTrace();
			velocityContext.put("status", antenna + "启动扫描异常");
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 停止扫描，读取生成的文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "toInStop", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void toInStop(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true, value = "antenna") String antenna,
			@RequestParam(required = true, value = "columnId") String columnId,
			@RequestParam(required = false, value = "location") String location,
			@RequestParam(required = false, value = "model") String model,
			@RequestParam(required = false, value = "brand") String brand,
			@RequestParam(required = false, value = "name") String name) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-inStop.vm";
		velocityContext.put("antenna", antenna);
		velocityContext.put("columnId", columnId);
		try {
			List<ErpProduct> erpProducts = new ArrayList<ErpProduct>();
			List<String> scanedEpcIds = Reader18.funcStop(antenna);
			// List<String> scanedEpcIds = Reader18.mock("b", antenna);
			List<String> strs = new ArrayList<String>();
			for (String epcId : scanedEpcIds) {
				ErpCode ec = erpCodeDao.getByCode(epcId);
				if (null == ec) {// 编码不在关联表内
					strs.add(epcId);
				} else {
					ErpProduct ep = new ErpProduct();
					String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
					ep.setId(randomSeed);
					ep.setName(name);
					ep.setCode(epcId);
					ep.setColumnId(columnId);
					ep.setInDate(new Date());
					ep.setLocation(location);
					ep.setModel(model);
					ep.setBrand(brand);
					erpProducts.add(ep);
				}
			}
			for (ErpProduct p : erpProducts) {
				erpProductDao.insert(p);
			}
			velocityContext.put("status", antenna + "停止扫描;" + "已录入" + erpProducts.size() + "产品");
			velocityContext.put("strs", strs);
		} catch (Exception e) {
			e.printStackTrace();
			velocityContext.put("status", antenna + "停止扫描异常");
		} finally {
			// System.out.println("delete");
			Reader18.deleteFile(antenna);
		}
		// System.out.println("response");
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 出库界面，开始启动扫描
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "toOutStart", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void toOutStart(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true, value = "antenna") String antenna) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-outStart.vm";
		velocityContext.put("antenna", antenna);
		try {
			Reader18.funcStart(antenna);
			velocityContext.put("status", antenna + "正在扫描");
		} catch (Exception e) {
			e.printStackTrace();
			velocityContext.put("status", antenna + "启动扫描异常");
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 停止扫描，读取生成的文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "toOutStop", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void toutStop(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true, value = "antenna") String antenna) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/erpProduct-outStop.vm";
		velocityContext.put("antenna", antenna);
		try {
			List<String> scanedEpcIds = Reader18.funcStop(antenna);
			// List<String> scanedEpcIds = Reader18.mock("b", antenna);
			for (String epcId : scanedEpcIds) {
				erpProductDao.deleteByCode(epcId);
				ErpCode erpCode = erpCodeDao.getByCode(epcId);
				erpCodeDao.delete(erpCode);
			}
			velocityContext.put("status", antenna + "停止扫描;" + "已撤出" + scanedEpcIds.size() + "产品");
		} catch (Exception e) {
			e.printStackTrace();
			velocityContext.put("status", antenna + "停止扫描异常");
		} finally {
			Reader18.deleteFile(antenna);
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
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
		ErpProduct erpProduct = erpProductDao.get(id);
		velocityContext.put("erpProduct", erpProduct);
		String viewName = "cms/erpProduct-edit.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(params = "doEdit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute ErpProduct erpProduct) {
		AjaxJson j = new AjaxJson();
		try {
			erpProductDao.update(erpProduct);
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
			ErpProduct erpProduct = new ErpProduct();
			erpProduct.setId(id);
			erpProductDao.delete(erpProduct);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

}
