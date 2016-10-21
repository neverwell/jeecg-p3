<#if (erpCode.code)??>
		    /* CODE */
			and ec.CODE = :erpCode.code
</#if>
<#if (erpCode.oneCode)??>
		    /* ONECODE */
			and ec.ONECODE = :erpCode.oneCode
</#if>