<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/29/2024
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="modal" id="user-setting-dialog">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form action="${contextPath}/update-user" method="post">
                <%--            Header--%>
                <div class="modal-header">
                    <h5 class="modal-title">Cài đặt</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="resetUserSettingDialog()"></button>
                </div>

                <%--            Body--%>
                <div class="modal-body">
                    <%--                    Full name--%>
                    <div class="form-floating mb-3">
                        <input type="text"
                               id="fullName"
                               name="fullName"
                               placeholder="Họ và tên"
                               value="${requestScope.user.fullName}"
                               class="form-control" />
                        <label for="fullName">Họ và tên</label>
                    </div>

                    <%--                    Password--%>
                    <div class="form-floating">
                        <input type="password"
                               id="password"
                               name="password"
                               placeholder="Mật khẩu"
                               class="form-control" />
                        <label for="password">Mật khẩu</label>
                    </div>
                </div>

                <%--            Actions--%>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary-outline" data-bs-dismiss="modal"
                            onclick="resetUserSettingDialog()">
                        Hủy
                    </button>
                    <button type="submit" class="btn btn-primary">
                        Cập nhật
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function resetUserSettingDialog() {
        $("#user-setting-dialog #fullName").val("${requestScope.user.fullName}");
        $("#user-setting-dialog #password").val("");
    }
</script>