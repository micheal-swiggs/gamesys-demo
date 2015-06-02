<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'employee.label', default: 'Employee')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-employee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <g:secHasRole role='ROLE_HR'>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </g:secHasRole>
            </ul>
        </div>
        <div id="list-employee" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <form method="post" action="${g.createLink(action:'dateFilter')}">
                <fieldset>
                <div class="fieldcontain">
                    <label>Start Date</label>
                    <g:datePicker name="startDate" precision="day" />
                </div>
                <div class="fieldcontain">
                    <label>End Date</label>
                    <g:datePicker name="endDate" precision="day" />
                </div>
                <div class="fieldcontain">
                    <input type="submit" value="Search">
                </div>
                </fieldset>
                <g:hiddenField name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <table>
                <thead>
                    <tr>
                        <g:each in="${g.secVisibleEmployeeAttrs()}">
                        <th class="sortable">${it[0]}</th>
                        </g:each>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${employeeList}" var="employee">
                    <tr>
                        <td>
                            <a href="/employee/show/${employee.id}">
                            <f:display bean="${employee}" property="fullName" />
                            </a>
                        </td>
                        <g:each in="${g.secVisibleEmployeeAttrs().findAll{it[1] != 'fullName'}}" var="attr">
                        <td><f:display bean="${employee}" property="${attr[1]}" /></td>
                        </g:each>
                    </tr>
                    </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${employeeCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
