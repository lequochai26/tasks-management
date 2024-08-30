<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
    #header-title a h3 {
        margin-bottom: 0;
        margin-top: 0;
        margin-block-start: 0;
        margin-block-end: 0;
    }
</style>

<div class="container-fluid py-3 text-white font-bold bg-primary d-flex align-items-center">
<%--    Show / hide sidebar button--%>
    <div>
        <button type="button" class="btn btn-lg btn-primary rounded-circle"
                data-bs-toggle="tooltip"
                title="Ẩn / hiện sidebar"
                id="toggle-sidebar-button">
            <i class="bi bi-layout-sidebar-inset"></i>
        </button>
    </div>

<%--    Header title--%>
    <div id="header-title" class="ms-2 flex-grow-1">
        <a href="${pageContext.request.contextPath}" class="text-light">
            <h3>
                <c:out value="${param.title}" />
            </h3>
        </a>
    </div>

<%--    User display area--%>
    <div>
        <div class="dropdown">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown"
                    style="font-size: 17px;">
                <i class="bi bi-person"></i>
                <c:out value="${requestScope.user.fullName}" />
            </button>
            <ul class="dropdown-menu">
                <li>
                    <button class="dropdown-item"
                            data-bs-toggle="modal"
                            data-bs-target="#user-setting-dialog">
                        <i class="bi bi-gear"></i>
                        <c:out value="Cài đặt" />
                    </button>
                </li>
                <li>
                    <a class="dropdown-item" href="${contextPath}/auth?action=logout">
                        <i class="bi bi-door-open"></i>
                        <c:out value="Đăng xuất" />
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>

<script>
    $(document).ready(
        function () {
            const sidebarButton = $('#toggle-sidebar-button')
            const sidebarButtonIcon = $('#toggle-sidebar-button i');

            sidebarButton.click(
                function () {
                    $('#sidebar').animate({width: 'toggle'})
                    sidebarButtonIcon.toggleClass("bi-layout-sidebar")
                    sidebarButtonIcon.toggleClass("bi-layout-sidebar-inset")
                }
            )
        }
    )
</script>