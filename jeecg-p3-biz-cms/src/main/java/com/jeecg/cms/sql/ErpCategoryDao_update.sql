UPDATE erp_category
SET 
	   <#if erpCategory.name ?exists>
		   NAME = :erpCategory.name,
		</#if>
	   <#if erpCategory.parentCode ?exists>
		   PARENT_CODE = :erpCategory.parentCode,
		</#if>
WHERE id = :erpCategory.id