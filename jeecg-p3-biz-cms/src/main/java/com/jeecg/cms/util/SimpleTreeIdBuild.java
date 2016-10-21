package com.jeecg.cms.util;

import org.jeecgframework.p3.core.common.utils.StringUtil;

import com.jeecg.cms.dao.CmsMenuDao;
import com.jeecg.cms.dao.ErpCategoryDao;

/**
 * 根据 P3 1.0的TableTree 的页面组件规则的ID 生成器 (代码逻辑摘抄自 jeecg)
 * 
 * @author jg_huangxg
 *
 */
public class SimpleTreeIdBuild {

	public String getId(CmsMenuDao cmsMenuDao, String parentCode) {

		String idCode = null;
		String localMaxCode = null;
		if (StringUtil.isEmpty(parentCode)) {// 无上级
			localMaxCode = getMaxLocalCode(cmsMenuDao, null);
			idCode = YouBianCodeUtil.getNextYouBianCode(localMaxCode);
		} else {// 有上级
			localMaxCode = getMaxLocalCode(cmsMenuDao, parentCode);
			idCode = YouBianCodeUtil.getSubYouBianCode(parentCode, localMaxCode);
		}

		return idCode;

	}

	public String getId(ErpCategoryDao erpCategoryDao, String parentCode) {

		String idCode = null;
		String localMaxCode = null;
		if (StringUtil.isEmpty(parentCode)) {// 无上级
			localMaxCode = getMaxLocalCode(erpCategoryDao, null);
			idCode = YouBianCodeUtil.getNextYouBianCode(localMaxCode);
		} else {// 有上级
			localMaxCode = getMaxLocalCode(erpCategoryDao, parentCode);
			idCode = YouBianCodeUtil.getSubYouBianCode(parentCode, localMaxCode);
		}

		return idCode;

	}

	private synchronized String getMaxLocalCode(CmsMenuDao cmsMenuDao, String parentCode) {
		if (StringUtil.isEmpty(parentCode)) {
			parentCode = "";
		}
		int localCodeLength = parentCode.length() + YouBianCodeUtil.zhanweiLength;

		return cmsMenuDao.getMaxLocalCode(String.valueOf(localCodeLength), parentCode);
	}

	private synchronized String getMaxLocalCode(ErpCategoryDao erpCategoryDao, String parentCode) {
		if (StringUtil.isEmpty(parentCode)) {
			parentCode = "";
		}
		int localCodeLength = parentCode.length() + YouBianCodeUtil.zhanweiLength;

		return erpCategoryDao.getMaxLocalCode(String.valueOf(localCodeLength), parentCode);
	}
}
