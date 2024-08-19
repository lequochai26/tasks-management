<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/18/2024
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="../views/users-view.jsp" var="viewUrl" />

<c:url value="templates/common.jsp" var="templateUrl">
    <c:param name="viewUrl" value="${viewUrl}" />
</c:url>

<jsp:include page="${templateUrl}" />