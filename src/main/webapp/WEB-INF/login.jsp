<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/24/2024
  Time: 3:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="messages" value="${requestScope.messages}" />

<html>
<head>
    <title>Đăng nhập</title>

<%--    Bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

<%--    jQuery--%>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<%--    Custom CSS--%>
    <style>
        h3 {
            margin: 0;
        }

        .WARNING {
            display: inline-block;
            margin-right: 10px;
            background-color: gold;
            width: 20px;
            height: 20px;
            border-radius: 5px;
        }

        .ERROR {
            display: inline-block;
            margin-right: 10px;
            background-color: red;
            width: 20px;
            height: 20px;
            border-radius: 5px;
        }

        .INFO {
            display: inline-block;
            margin-right: 10px;
            background-color: dodgerblue;
            width: 20px;
            height: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="vw-100 vh-100 d-flex align-items-center justify-content-center">
        <div style="height: fit-content; width: 450px;" class="border pt-4 ps-4 pe-4">
            <form action="${contextPath}/auth?action=login" method="post">
                <div class="bg-primary p-3 rounded-2 mb-3">
                    <h3 class="text-light">
                        Test thay đổi chút xíu
                    </h3>
                </div>

                <div class="form-floating mb-3">
                    <input type="text" id="username" name="username"
                           class="form-control"
                           placeholder="Tên đăng nhập"
                           value="${param.username}"
                           required />
                    <label for="username">Tên đăng nhập</label>
                </div>

                <div class="form-floating mb-3">
                    <input type="password" id="password" name="password"
                           class="form-control"
                           placeholder="Mật khẩu"
                           value="${param.password}"
                           required />
                    <label for="password">Mật khẩu</label>
                </div>

                <div class="p-3">
                    <div>
                        <button type="submit" class="btn btn-outline-primary rounded-0"
                                style="width: 100%">
                            Đăng nhập
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <%-- Toasts display --%>
    <c:if test="${not empty messages}">
        <div class="toast-container position-fixed top-0 end-0 p-3">
            <c:forEach var="message" items="${messages}">
                <div class="toast fade show" role="alert" aria-live="assertive" aria-atomic="true">
                    <div class="toast-header">
                        <i class="${message.type.name()}"></i>
                        <strong class="me-auto">${message.type.title}</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body">
                            ${message.content}
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
