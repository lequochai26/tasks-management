<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/18/2024
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="user" value="${requestScope.user}" />

<style>
    .sidebar-width {
        width: 225px;
    }
</style>

<%--Sidebar--%>
<div id="sidebar" class="border-end border-top sidebar-width">
<%--    List group--%>
    <div class="list-group sidebar-width" style="border-radius: 0;">
        <a href="${contextPath}/tasks" class="list-group-item list-group-item-action ${pageContext.request.servletPath.endsWith('tasks.jsp') ? 'active' : ''}">
            <i class="bi bi-clipboard"></i>
            Quản lý công việc
        </a>

        <c:if test="${not empty user and user.role.name() eq 'ADMIN'}">
            <a href="${contextPath}/users-action" class="list-group-item list-group-item-action ${pageContext.request.servletPath.endsWith('users.jsp') ? 'active' : ''}">
                <i class="bi bi-people"></i>
                Quản lý người dùng
            </a>
        </c:if>

    </div>
</div>