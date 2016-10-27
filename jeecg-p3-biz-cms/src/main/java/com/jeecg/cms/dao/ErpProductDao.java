package com.jeecg.cms.dao;

import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.springframework.stereotype.Repository;

import com.jeecg.cms.entity.CmsArticle;
import com.jeecg.cms.entity.ErpCode;
import com.jeecg.cms.entity.ErpProduct;

@Repository
public interface ErpProductDao extends CmsDao {

	/**
	 * 查询返回Java对象
	 * 
	 * @param id
	 * @return
	 */
	@Sql("SELECT * FROM erp_product WHERE ID = :id")
	ErpProduct get(@Param("id") String id);

	/**
	 * 修改数据
	 * 
	 * @param erpProduct
	 * @return
	 */
	int update(@Param("erpProduct") ErpProduct erpProduct);

	/**
	 * 插入数据
	 * 
	 * @param act
	 */
	void insert(@Param("erpProduct") ErpProduct erpProduct);

	/**
	 * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
	 * 
	 * @param erpProduct
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResultType(ErpProduct.class)
	public MiniDaoPage<ErpProduct> getAll(@Param("erpProduct") ErpProduct erpProduct, @Param("page") int page,
			@Param("rows") int rows);

	@ResultType(ErpProduct.class)
	public MiniDaoPage<ErpProduct> getAll();

	@ResultType(ErpProduct.class)
	public MiniDaoPage<ErpProduct> getListByMenu(@Param("coulmnId") String coulmnId);

	@ResultType(String.class)
	public String getCount(@Param("coulmnId") String coulmnId);

	@Sql("DELETE from erp_product WHERE ID = :erpProduct.id")
	public void delete(@Param("erpProduct") ErpProduct erpProduct);

	public void deleteByCode(@Param("code") String code);

	/**
	 * 查询返回Java对象
	 * 
	 * @param code
	 * @return
	 */
	@Sql("SELECT * FROM erp_product WHERE CODE = :code")
	ErpProduct getByCode(@Param("code") String code);

}
