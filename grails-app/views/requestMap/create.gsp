<%@ page import="com.cygnus.sys.umgt.RequestMap" %>
<!doctype html>
<html>
<!-- Generated by Cygnus-templateEngine -->
	<head>
		<meta name="layout" content="cygnus-forms">
		<g:set var="entityName" value="${message(code: 'requestMap.label', default: 'RequestMap')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<parameter name="pageEName" value="requestMap" />
	</head>
	<body>
		<a href="#create-requestMap" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-requestMap" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${requestMapInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${requestMapInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:formRemote name="cygnusrequestMapCreateForm" update="pageContent" url="[controller:'requestMap', action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:formRemote>
		</div>
	</body>
</html>
