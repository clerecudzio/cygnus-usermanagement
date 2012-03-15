<%@ page import="com.cygnus.sys.umgt.Authorities" %>



<div class="fieldcontain ${hasErrors(bean: authoritiesInstance, field: 'authority', 'error')} ">
	<label for="authority">
		<g:message code="authorities.authority.label" default="Authority" />
		
	</label>
	<g:textField name="authority" value="${authoritiesInstance?.authority}" />
</div>

