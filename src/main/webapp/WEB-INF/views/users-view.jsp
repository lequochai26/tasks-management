<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/18/2024
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="w-100 h-100 d-flex flex-column" style="height: fit-content;">
<%--    Action bar--%>
    <div class="d-flex flex-row border-bottom">
<%--        Search area--%>
        <div style="width: fit-content;" class="p-3">
            <form action="${contextPath}/users-action" method="get">
                <div class="input-group" style="width: 500px;">
                    <input type="text" name="keyword" class="form-control"
                           placeholder="Tên đăng nhập / vai trò / họ và tên"
                           value="${param.keyword}"/>
                    <button type="submit" class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Tìm kiếm">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </form>
        </div>

<%--        Create area--%>
        <div class="flex-grow-1 text-end p-3">
<%--            Add button--%>
            <button class="btn btn-success btn-add"
                    data-bs-toggle="modal"
                    data-bs-target="#addUserModal">
                <i class="bi bi-plus"></i>
                Thêm người dùng
            </button>
        </div>
    </div>

<%--    Datatable--%>
    <div class="flex-grow-1 table-responsive p-3">
        <table class="table">
            <thead>
            <tr>
                <th>
                    Tên đăng nhập
                </th>
                <th>
                    Họ và tên
                </th>
                <th>
                    Vai trò
                </th>
                <th>
                    Hành động
                </th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${not empty requestScope.users}">
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td style="vertical-align: middle;">
                            ${user.username}
                        </td>
                        <td style="vertical-align: middle;">
                            ${user.fullName}
                        </td>
                        <td style="vertical-align: middle;">
                            ${user.role}
                        </td>
                        <td style="vertical-align: middle;">
                            <button class="btn btn-primary"
                                    data-bs-toggle="tooltip"
                                    title="Cập nhật"
                                    onclick="showEditUserModal('${user.username}')">
                                <i class="bi bi-pencil"></i>
                            </button>
                            <button class="ms-2 btn btn-danger"
                                    data-bs-toggle="tooltip"
                                    title="Xóa"
                                    onclick="showModal('deleteUserModal-${user.username}')">
                                <i class="bi bi-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${empty requestScope.users}">
                <tr>
                    <td colspan="4">
                        Không có dữ liệu
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<%-- Add User modal --%>
<div class="modal fade" id="addUserModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form action="${contextPath}/users-action?action=create" method="post">
                <%--            Header--%>
                <div class="modal-header">
                    <h4 class="modal-title">Thêm người dùng</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <%--            Body--%>
                <div class="modal-body">
                    <%--                Username input--%>
                    <div class="form-floating mb-3 mt-3">
                        <input type="text" class="form-control"
                               id="username"
                               placeholder="Tên đăng nhập"
                               name="username" />
                        <label for="username">Tên đăng nhập</label>
                    </div>

                    <%--                Password input--%>
                    <div class="form-floating mb-3 mt-3">
                        <input type="password" class="form-control"
                               id="password"
                               placeholder="Mật khẩu"
                               name="password" />
                        <label for="password">Mật khẩu</label>
                    </div>

                    <%--                Fullname input--%>
                    <div class="form-floating mb-3 mt-3">
                        <input type="text" class="form-control"
                               id="fullName"
                               placeholder="Họ và tên"
                               name="fullName" />
                        <label for="fullName">Họ và tên</label>
                    </div>

                    <%--                Role--%>
                    <div class="mb-3">
                        <select id="role" name="role" class="form-select py-3">
                            <option value="DEVELOPER">Lập trình viên</option>
                            <option value="QUALITY_CONTROL">Kiểm thử viên</option>
                            <option value="ADMIN">Quản lý</option>
                        </select>
                    </div>
                </div>

                <%--            Footer--%>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger"
                            data-bs-dismiss="modal">
                        Hủy
                    </button>

                    <button type="submit" class="btn btn-success btn-add ms-3">
                        Thêm
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%-- Edit User modal --%>
<c:if test="${not empty requestScope.users}">
    <c:forEach items="${requestScope.users}" var="user">
        <div class="modal fade" id="editUserModal-${user.username}">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <form action="${contextPath}/users-action?action=update" method="post">
                            <%--            Header--%>
                        <div class="modal-header">
                            <h4 class="modal-title">${user.username}</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                            <%--            Body--%>
                        <div class="modal-body">
                                <%--                Username input--%>
                            <input type="hidden" value="${user.username}"
                                   name="username"
                                   id="edit_username" />

                                <%--                Password input--%>
                            <div class="form-floating mb-3 mt-3">
                                <input type="password" class="form-control"
                                       id="edit_password"
                                       placeholder="Mật khẩu"
                                       name="password" />
                                <label for="edit_password">Mật khẩu</label>
                            </div>

                                <%--                Fullname input--%>
                            <div class="form-floating mb-3 mt-3">
                                <input type="text" class="form-control"
                                       id="edit_fullName"
                                       placeholder="Họ và tên"
                                       name="fullName"
                                       value="${user.fullName}" />
                                <label for="edit_fullName">Họ và tên</label>
                            </div>

                                <%--                Role--%>
                            <div class="mb-3">
                                <select id="edit_role" name="role" class="form-select py-3">
                                    <option value="DEVELOPER" ${user.role.name() eq 'DEVELOPER' ? 'selected' : ''}>
                                        Lập trình viên
                                    </option>
                                    <option value="QUALITY_CONTROL" ${user.role.name() eq 'QUALITY_CONTROL' ? 'selected' : ''}>
                                        Kiểm thử viên
                                    </option>
                                    <option value="ADMIN" ${user.role.name() eq 'ADMIN' ? 'selected' : ''}>
                                        Quản lý
                                    </option>
                                </select>
                            </div>
                        </div>

                            <%--            Footer--%>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-danger"
                                    data-bs-dismiss="modal">
                                Hủy
                            </button>

                            <button type="submit" class="btn btn-primary btn-add ms-3">
                                Cập nhật
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<%-- Delete user confirm dialog --%>
<c:if test="${not empty requestScope.users}">
    <c:forEach items="${requestScope.users}" var="user">
        <div class="modal fade" id="deleteUserModal-${user.username}">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                        <%--            Header--%>
                    <div class="modal-header">
                        <h4 class="modal-title">Cảnh báo</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                        <%--            Body--%>
                    <div class="modal-body">
                        Bạn có chắc muốn xóa người dùng ${user.username} không ?
                    </div>

                        <%--            Footer--%>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-primary"
                                data-bs-dismiss="modal">
                            Hủy
                        </button>

                        <a href="${contextPath}/users-action?action=delete&username=${user.username}">
                            <button type="button" class="btn btn-danger ms-3">
                                Có
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<script>
    function showEditUserModal(username) {
        const editUserModal = new bootstrap.Modal(document.getElementById("editUserModal-" + username));
        editUserModal.show();
    }

    function showModal(id) {
        const modal = new bootstrap.Modal(document.getElementById(id));
        modal.show();
    }
</script>