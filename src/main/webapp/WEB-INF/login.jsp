<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/17/2024
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <h1>
        Đăng nhập
    </h1>

    <form method="post" action="${pageContext.request.contextPath}/login">
<%--        Textbox username--%>
        <div>
            <input type="text" name="username" placeholder="Tên đăng nhập" required />
        </div>

<%--        Textbox password --%>
        <div>
            <input type="password" name="password" placeholder="Mật khẩu" required />
        </div>

<%--            Login button--%>
        <div>
            <input type="submit" value="Đăng nhập" />
        </div>
    </form>
</body>
</html>
