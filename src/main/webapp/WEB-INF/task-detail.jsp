<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/30/2024
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="viewUrl" value="../views/task-detail-view.jsp" />
<c:url var="templateUrl" value="templates/common.jsp">
    <c:param name="viewUrl" value="${viewUrl}" />
</c:url>

<jsp:include page="${templateUrl}" />