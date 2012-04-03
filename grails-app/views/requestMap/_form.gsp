<%@ page import="com.cygnus.sys.umgt.RequestMap" %>



<div class="fieldcontain ${hasErrors(bean: requestMapInstance, field: 'url', 'error')} required">
	<label for="url">
		<g:message code="requestMap.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="url" required="" value="${requestMapInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: requestMapInstance, field: 'configAttribute', 'error')} required">
	<label for="configAttribute">
		<g:message code="requestMap.configAttribute.label" default="Config Attribute" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="configAttribute" required="" value="${requestMapInstance?.configAttribute}"/>
</div>

