<%@ page import="com.cygnus.sys.umgt.Authorities" %>



<div class="fieldcontain ${hasErrors(bean: authoritiesInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="authorities.authority.label" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${authoritiesInstance?.authority}"/>
</div>

