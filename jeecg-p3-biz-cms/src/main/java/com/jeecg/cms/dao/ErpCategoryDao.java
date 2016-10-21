package com.jeecg.cms.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.cms.entity.CmsMenu;
import com.jeecg.cms.entity.ErpCategory;

@Repository
public interface ErpCategoryDao {

	/**
	 * 查询返回Java对象
	 * 
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM erp_category WHERE ID = :id")
	ErpCategory get(@Param("id") String id);

	/**
	 * 修改数据
	 * 
	 * @param cmsMenu
	 * @return
	 */
	int update(@Param("erpCategory") ErpCategory erpCategory);

	/**
	 * 插入数据
	 * 
	 * @param act
	 */
	void insert(@Param("erpCategory") ErpCategory erpCategory);

	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * 
	 * @param erpCategory
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(ErpCategory.class)
	public MiniDaoPage<ErpCategory> getAll(@Param("erpCategory") ErpCategory erpCategory, @Param("page") int page,
			@Param("rows") int rows);

	@ResultType(ErpCategory.class)
	public MiniDaoPage<ErpCategory> getAll();

	@Sql("DELETE from erp_category WHERE ID = :erpCategory.id")
	public void delete(@Param("erpCategory") ErpCategory erpCategory);

	@ResultType(ErpCategory.class)
	public MiniDaoPage<ErpCategory> getTree();

	@ResultType(String.class)
	public String getMaxLocalCode(@Param("localCodeLength") String localCodeLength,
			@Param("parentCode") String parentCode);
}
