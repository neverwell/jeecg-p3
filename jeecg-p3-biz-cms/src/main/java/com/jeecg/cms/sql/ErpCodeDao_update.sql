UPDATE erp_code
SET 
	   <#if erpCode.oneCode ?exists>
		   ONECODE = :erpCode.oneCode,
		</#if>
	   <#if erpCode.code ?exists>
		   CODE = :erpCode.code,
		</#if>
WHERE ID = :erpCode.id