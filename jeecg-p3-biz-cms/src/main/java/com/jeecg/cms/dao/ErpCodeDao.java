package com.jeecg.cms.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.cms.entity.ErpCode;
import com.jeecg.cms.entity.ErpProduct;

@Repository
public interface ErpCodeDao extends CmsDao {

	/**
	 * 查询返回Java对象
	 * 
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM erp_code WHERE ID = :id")
	ErpCode get(@Param("id") Integer id);

	/**
	 * 查询返回Java对象
	 * 
	 * @param code
	 * @return
	 */
	@Sql("SELECT * FROM erp_code WHERE CODE = :code")
	ErpCode getByCode(@Param("code") String code);

	/**
	 * 查询返回Java对象
	 * 
	 * @param oneCode
	 * @return
	 */
	@Sql("SELECT * FROM erp_code WHERE ONECODE = :oneCode")
	ErpCode getByOneCode(@Param("oneCode") String oneCode);

	/**
	 * 修改数据
	 * 
	 * @param erpCode
	 * @return
	 */
	int update(@Param("erpCode") ErpCode erpCode);

	/**
	 * 插入数据
	 * 
	 * @param erpCode
	 */
	void insert(@Param("erpCode") ErpCode erpCode);

	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * 
	 * @param erpCode
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(ErpCode.class)
	public MiniDaoPage<ErpCode> getAll(@Param("erpCode") ErpCode erpCode, @Param("page") int page,
			@Param("rows") int rows);

	@ResultType(ErpCode.class)
	public MiniDaoPage<ErpCode> getAll();

	// @ResultType(ErpCode.class)
	// public MiniDaoPage<ErpCode> getListByMenu(@Param("coulmnId") String
	// coulmnId);

	// @ResultType(String.class)
	// public String getCount(@Param("coulmnId") String coulmnId);

	@Sql("DELETE from erp_code WHERE ID = :erpCode.id")
	public int delete(@Param("erpCode") ErpCode erpCode);

}
