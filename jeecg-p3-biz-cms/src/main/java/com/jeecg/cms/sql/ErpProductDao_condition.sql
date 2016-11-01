<#if (erpProduct.columnId)?? && erpProduct.columnId ?length gt 0>
		    /* 类型id */
			and ep.column_id like CONCAT('%', :erpProduct.columnId ,'%') 
</#if>
<#if (erpProduct.name)?? && erpProduct.name ?length gt 0>
		    /* ONECODE */
			and ep.NAME = :erpProduct.name
</#if>
<#if (erpProduct.location)?? && erpProduct.location ?length gt 0>
		    /* ONECODE */
			and ep.LOCATION = :erpProduct.location
</#if>
<#if (erpProduct.brand)?? && erpProduct.brand ?length gt 0>
		    /* ONECODE */
			and ep.BRAND = :erpProduct.brand
</#if>
<#if (erpProduct.model)?? && erpProduct.model ?length gt 0>
		    /* ONECODE */
			and ep.MODEL = :erpProduct.model
</#if>