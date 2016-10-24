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
		<#if erpProduct.code ?exists>
		   LOCATION = :erpProduct.location,
		</#if>
		<#if erpProduct.code ?exists>
		   IN_DATE = :erpProduct.inDate,
		</#if>
		<#if erpProduct.code ?exists>
		   BRAND = :erpProduct.brand,
		</#if>
		<#if erpProduct.code ?exists>
		   MODEL = :erpProduct.model,
		</#if>
WHERE ID = :erpProduct.id