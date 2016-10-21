		<#if (erpProduct.columnId)?? && erpProduct.columnId ?length gt 0>
		    /* 类型id */
			and ep.column_id like CONCAT('%', :erpProduct.columnId ,'%') 
		</#if>
