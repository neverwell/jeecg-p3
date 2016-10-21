SELECT * FROM erp_product WHERE 1=1
<#if coulmnId ?exists && coulmnId?length gt 0>
	and column_id = :coulmnId
</#if>
