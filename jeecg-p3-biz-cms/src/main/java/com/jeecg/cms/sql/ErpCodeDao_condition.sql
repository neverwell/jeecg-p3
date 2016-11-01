<#if (erpCode.code)?? && erpCode.code ?length gt 0>
		    /* CODE */
			and ec.CODE = :erpCode.code
</#if>
<#if (erpCode.oneCode)?? && erpCode.oneCode ?length gt 0>
		    /* ONECODE */
			and ec.ONECODE = :erpCode.oneCode
</#if>