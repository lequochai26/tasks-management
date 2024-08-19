<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/18/2024
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .sidebar-width {
        width: 225px;
    }
</style>

<%--Sidebar--%>
<div id="sidebar" class="border-end border-top sidebar-width">
<%--    List group--%>
    <div class="list-group sidebar-width" style="border-radius: 0;">
        <p class="list-group-item fw-bold" style="margin: 0;">
            Khối công việc
        </p>

        <a href="#" class="list-group-item list-group-item-action">
            <i class="bi bi-clipboard"></i>
            Công việc của bạn
        </a>

        <p class="list-group-item fw-bold" style="margin: 0;">
            Khối quản lý
        </p>

        <a href="#" class="list-group-item list-group-item-action">
            <i class="bi bi-clipboard"></i>
            Quản lý công việc
        </a>

        <a href="#" class="list-group-item list-group-item-action">
            <i class="bi bi-people"></i>
            Quản lý người dùng
        </a>
    </div>
</div>