<%@ page import="com.cygnus.sys.umgt.RequestMap" %>



<div class="fieldcontain ${hasErrors(bean: requestMapInstance, field: 'configAttribute', 'error')} ">
	<label for="configAttribute">
		<g:message code="requestMap.configAttribute.label" default="Config Attribute" />
		
	</label>
	<g:textField name="configAttribute" value="${requestMapInstance?.configAttribute}" />
</div>

<div class="fieldcontain ${hasErrors(bean: requestMapInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="requestMap.url.label" default="Url" />
		
	</label>
	<g:textField name="url" value="${requestMapInstance?.url}" />
</div>

