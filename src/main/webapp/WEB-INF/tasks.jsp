<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/21/2024
  Time: 2:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="viewUrl" value="../views/tasks-view.jsp" />
<c:url var="templateUrl" value="templates/common.jsp">
    <c:param name="viewUrl" value="${viewUrl}" />
</c:url>

<jsp:include page="${templateUrl}" />