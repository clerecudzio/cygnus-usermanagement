<%@ page import="com.cygnus.sys.umgt.PersonAuthorities" %>



<div class="fieldcontain ${hasErrors(bean: personAuthoritiesInstance, field: 'authorities', 'error')} ">
	<label for="authorities">
		<g:message code="personAuthorities.authorities.label" default="Authorities" />
		
	</label>
	<g:select id="authorities" name="authorities.id" from="${com.cygnus.sys.umgt.Authorities.list()}" optionKey="id" required="" value="${personAuthoritiesInstance?.authorities?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personAuthoritiesInstance, field: 'person', 'error')} ">
	<label for="person">
		<g:message code="personAuthorities.person.label" default="Person" />
		
	</label>
	<g:select id="person" name="person.id" from="${com.cygnus.sys.umgt.Person.list()}" optionKey="id" required="" value="${personAuthoritiesInstance?.person?.id}" class="many-to-one"/>
</div>

