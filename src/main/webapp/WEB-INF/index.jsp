<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/18/2024
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--Template URL creating--%>
<c:url var="templateUrl" value="templates/common.jsp">
    <c:param name="viewUrl" />
</c:url>

<%--Including template--%>
<jsp:include page="${templateUrl}" />