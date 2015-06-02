<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'employee.label', default: 'Employee')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-employee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <g:secHasRole role='ROLE_HR'>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </g:secHasRole>
            </ul>
        </div>
        <div id="show-employee" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list">
                <g:each in="${g.secVisibleEmployeeAttrs()}">
                    <li class="fieldcontain">
                        <span class="property-label">${it[0]}</span>
                        <span class="property-value">
                        <f:display bean="employee" property="${it[1]}" />
                        </span>
                    </li>
                </g:each>
            </ol>
            <g:secHasRole role='ROLE_HR'>
            <g:form resource="${employee}" method="DELETE">
                <fieldset class="buttons">
                   <g:hiddenField name="${_csrf.parameterName}" value="${_csrf.token}"/>
                   <g:link class="edit" action="edit" resource="${employee}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
            </g:secHasRole>
        </div>
    </body>
</html>
