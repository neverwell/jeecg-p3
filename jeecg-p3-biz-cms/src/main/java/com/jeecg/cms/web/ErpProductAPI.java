package com.jeecg.cms.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import com.jeecg.cms.dao.ErpCodeDao;
import com.jeecg.cms.dao.ErpProductDao;
import com.jeecg.cms.entity.ErpCode;
import com.jeecg.cms.entity.ErpProduct;

@Controller
@RequestMapping("/api/erpProduct")
public class ErpProductAPI extends BaseController {
	@Autowired
	private ErpProductDao erpProductDao;
	@Autowired
	private ErpCodeDao erpCodeDao;
	private Map<String, Object> result = new HashMap();

	/**
	 * 列表
	 * 
	 * @return
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list/{pageNo}/{pageSize}", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> List(@PathVariable int pageNo, @PathVariable int pageSize) throws Exception {
		try {
			// LOG.info(request, " back list");
			// 分页数据
			MiniDaoPage<ErpProduct> list = erpProductDao.getAll(null, pageNo, pageSize);
			result.put("status", true);
			result.put("summary", "");
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false);
			result.put("summary", "查询失败");
			result.put("data", null);
		}
		return result;
	}

	// /**
	// * 根据id查询
	// *
	// * @return
	// */
	// @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET,
	// headers = "Accept=application/json")
	// @ResponseBody
	// public Map<String, Object> detail(@PathVariable int id,
	// HttpServletResponse response, HttpServletRequest request)
	// throws Exception {
	// try {
	// ErpCode erpCode = erpCodeDao.get(id);
	// result.put("status", true);
	// result.put("summary", "");
	// result.put("data", erpCode);
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.put("status", false);
	// result.put("summary", "查询失败");
	// result.put("data", null);
	// }
	// return result;
	// }
	//
	// /**
	// * 根据code查询
	// *
	// * @return
	// */
	// @RequestMapping(value = "/code/{code}", method = RequestMethod.GET,
	// headers = "Accept=application/json")
	// @ResponseBody
	// public Map<String, Object> code(@PathVariable String code,
	// HttpServletResponse response, HttpServletRequest request)
	// throws Exception {
	// try {
	// ErpCode erpCode = new ErpCode();
	// erpCode.setCode(code);
	// MiniDaoPage<ErpCode> list = erpCodeDao.getAll(erpCode, 1, 100);
	// result.put("status", true);
	// result.put("summary", "");
	// result.put("data", list);
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.put("status", false);
	// result.put("summary", "查询失败");
	// result.put("data", null);
	// }
	// return result;
	// }

	/**
	 * 根据oneCode查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/oneCode/{oneCode}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> oneCode(@PathVariable String oneCode, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		ErpCode erpCode = erpCodeDao.getByOneCode(oneCode);
		if (null == erpCode || (erpCode.getCode() == null || erpCode.getCode().length() <= 0)) {
			result.put("status", false);
			result.put("summary", "此一维码尚未入库");
			result.put("data", null);
		} else {
			ErpProduct erpProduct = erpProductDao.getByCode(erpCode.getCode());
			if (null == erpProduct) {
				result.put("status", false);
				result.put("summary", "没有此商品");
				result.put("data", null);
			} else {
				result.put("status", true);
				result.put("summary", "");
				result.put("data", erpProduct);
			}
		}
		return result;
	}

	/**
	 * 入库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/in", method = { RequestMethod.POST }, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> in(@RequestBody ErpProduct erpProduct) {
		ErpCode erpCode = erpCodeDao.getByOneCode(erpProduct.getOneCode());
		if (null == erpCode || (erpCode.getCode() == null || erpCode.getCode().length() <= 0)) {
			result.put("status", false);
			result.put("summary", "此一维码尚未入库");
			result.put("data", null);
		} else {
			String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			erpProduct.setId(randomSeed);
			erpProduct.setCode(erpCode.getCode());
			erpProduct.setInDate(new Date());
			erpProductDao.insert(erpProduct);
			result.put("status", true);
			result.put("summary", "入库成功");
			result.put("data", null);
		}
		return result;
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> edit(@RequestBody ErpProduct erpProduct) {
		int re = erpProductDao.update(erpProduct);
		if (re > 0) {
			result.put("status", true);
			result.put("summary", "编辑成功");
			result.put("data", null);
		} else {
			result.put("status", false);
			result.put("summary", "编辑失败");
			result.put("data", null);
		}
		return result;
	}

	/**
	 * 出库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/out", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Map<String, Object> out(@RequestBody ErpProduct erpProduct, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		ErpCode erpCode = erpCodeDao.getByOneCode(erpProduct.getOneCode());
		if (null == erpCode || (erpCode.getCode() == null || erpCode.getCode().length() <= 0)) {
			result.put("status", false);
			result.put("summary", "此一维码尚未入库");
			result.put("data", null);
		} else {
			// erpProductDao.deleteByCode(erpCode.getCode());出库不删除编码关系
			erpCodeDao.delete(erpCode);
			result.put("status", true);
			result.put("summary", "出库成功");
			result.put("data", null);
		}
		return result;
	}

}
