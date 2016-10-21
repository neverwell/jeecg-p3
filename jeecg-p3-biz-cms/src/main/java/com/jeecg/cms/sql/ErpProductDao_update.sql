UPDATE erp_product
SET 
	   <#if erpProduct.name ?exists>
		   NAME = :erpProduct.name,
	   </#if>
	   <#if erpProduct.columnId ?exists>
		   COLUMN_ID = :erpProduct.columnId,
		</#if>
	   <#if erpProduct.code ?exists>
		   CODE = :erpProduct.code,
		</#if>
WHERE ID = :erpProduct.id