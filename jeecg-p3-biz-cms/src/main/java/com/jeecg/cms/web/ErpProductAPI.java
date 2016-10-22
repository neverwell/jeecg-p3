//package com.jeecg.cms.web;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.jeecgframework.minidao.pojo.MiniDaoPage;
//import org.jeecgframework.p3.core.web.BaseController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.jeecg.cms.dao.ErpProductDao;
//import com.jeecg.cms.entity.ErpCode;
//import com.jeecg.cms.entity.ErpProduct;
//
//@Controller
//@RequestMapping("/api/erpProduct")
//public class ErpProductAPI extends BaseController {
//	@Autowired
//	private ErpProductDao erpProductDao;
//
//	private Map<String, Object> result = new HashMap();
//
//	/**
//	 * 列表
//	 * 
//	 * @return
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/list/{pageNo}/{pageSize}", method = { RequestMethod.GET,
//			RequestMethod.POST }, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> List(@PathVariable int pageNo, @PathVariable int pageSize) throws Exception {
//		try {
//			// LOG.info(request, " back list");
//			// 分页数据
//			MiniDaoPage<ErpProduct> list = erpProductDao.getAll(null, pageNo, pageSize);
//			result.put("status", true);
//			result.put("summary", "");
//			result.put("data", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("status", false);
//			result.put("summary", "查询失败");
//			result.put("data", null);
//		}
//		return result;
//	}
//
//	/**
//	 * 根据id查询
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> detail(@PathVariable int id, HttpServletResponse response, HttpServletRequest request)
//			throws Exception {
//		try {
//			ErpCode erpCode = erpCodeDao.get(id);
//			result.put("status", true);
//			result.put("summary", "");
//			result.put("data", erpCode);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("status", false);
//			result.put("summary", "查询失败");
//			result.put("data", null);
//		}
//		return result;
//	}
//
//	/**
//	 * 根据code查询
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/code/{code}", method = RequestMethod.GET, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> code(@PathVariable String code, HttpServletResponse response, HttpServletRequest request)
//			throws Exception {
//		try {
//			ErpCode erpCode = new ErpCode();
//			erpCode.setCode(code);
//			MiniDaoPage<ErpCode> list = erpCodeDao.getAll(erpCode, 1, 100);
//			result.put("status", true);
//			result.put("summary", "");
//			result.put("data", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("status", false);
//			result.put("summary", "查询失败");
//			result.put("data", null);
//		}
//		return result;
//	}
//
//	/**
//	 * 根据oneCode查询
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/oneCode/{oneCode}", method = RequestMethod.GET, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> oneCode(@PathVariable String oneCode, HttpServletResponse response,
//			HttpServletRequest request) throws Exception {
//		try {
//			ErpCode erpCode = new ErpCode();
//			erpCode.setOneCode(oneCode);
//			MiniDaoPage<ErpCode> list = erpCodeDao.getAll(erpCode, 1, 100);
//			result.put("status", true);
//			result.put("summary", "");
//			result.put("data", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.put("status", false);
//			result.put("summary", "查询失败");
//			result.put("data", null);
//		}
//		return result;
//	}
//
//	/**
//	 * 添加
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/add", method = { RequestMethod.POST }, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> add(@RequestBody List<ErpCode> erpCodes) {
//		for (ErpCode ec : erpCodes) {
//			erpCodeDao.insert(ec);
//		}
//		result.put("status", true);
//		result.put("summary", "添加成功");
//		result.put("data", null);
//		return result;
//	}
//
//	/**
//	 * 编辑
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
//			RequestMethod.POST }, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> doEdit(@RequestBody ErpCode erpCode) {
//		int re = erpCodeDao.update(erpCode);
//		if (re > 0) {
//			result.put("status", true);
//			result.put("summary", "编辑成功");
//			result.put("data", null);
//		} else {
//			result.put("status", false);
//			result.put("summary", "编辑失败");
//			result.put("data", null);
//		}
//		return result;
//	}
//
//	/**
//	 * 删除
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
//	@ResponseBody
//	public Map<String, Object> delete(@PathVariable int id, HttpServletResponse response, HttpServletRequest request)
//			throws Exception {
//		ErpCode erpCode = new ErpCode();
//		erpCode.setId(id);
//		int re = erpCodeDao.delete(erpCode);
//		if (re > 0) {
//			result.put("status", true);
//			result.put("summary", "删除成功");
//			result.put("data", null);
//		} else {
//			result.put("status", false);
//			result.put("summary", "删除失败");
//			result.put("data", null);
//		}
//		return result;
//	}
//
//}
