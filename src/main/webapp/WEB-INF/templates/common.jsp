<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>

<%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

<%--    JQuery--%>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<%--    CSS--%>
    <style>
        .btn-add {
            background-color: #0eb90f;
            border-color: #0eb90f;
        }
    </style>
</head>

<body>
<%--    Header URL--%>
<c:url var="headerUrl" value="../components/header.jsp">
    <c:param name="title" value="Quản lý công việc" />
</c:url>

<%--    Sidebar URL--%>
<c:url var="sidebarUrl" value="../components/sidebar.jsp" />

<div class="d-flex flex-column vh-100">
<%--    Header--%>
    <jsp:include page="${headerUrl}" />

<%--    Body--%>
    <div class="flex-grow-1 d-flex flex-row">
<%--        Sidebar--%>
        <jsp:include page="${sidebarUrl}" />

<%--        View--%>
        <div class="flex-grow-1">
<%--            Default content--%>
            <c:if test="${empty param.viewUrl}">
                <div class="w-100 h-100 p-2 d-flex flex-column align-items-center justify-content-center">
                    <h3>
                        Chào mừng bạn đã trở lại!
                    </h3>

                    <h4>
                        Để di chuyển đến giao diện bạn mong muốn, hãy chọn một trong các mục phía bên tay trái.
                    </h4>
                </div>
            </c:if>

<%--            View display--%>
            <c:if test="${not empty param.viewUrl}">
                <jsp:include page="${param.viewUrl}" />
            </c:if>
        </div>
    </div>
</div>

</body>
</html>

<%--Bootstrap scripts--%>
<script>
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })
</script>