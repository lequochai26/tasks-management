<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/18/2024
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="w-100 h-100 d-flex flex-column" style="height: fit-content;">
<%--    Action bar--%>
    <div class="d-flex flex-row border-bottom">
<%--        Search area--%>
        <div style="width: fit-content;" class="p-3">
            <form action="#" method="get">
                <div class="input-group" style="width: 500px;">
                    <input type="text" name="keyword" class="form-control"
                           placeholder="Tên đăng nhập / vai trò / họ và tên" />
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
            <button class="btn btn-success btn-add">
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
            <tr>
                <td style="vertical-align: middle;">
                    lequochai
                </td>
                <td style="vertical-align: middle;">
                    Lê Quốc Hải
                </td>
                <td style="vertical-align: middle;">
                    ADMIN
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Cập nhật">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="ms-2 btn btn-danger"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    nguyenhoangkhuyen
                </td>
                <td style="vertical-align: middle;">
                    Nguyễn Hoàng Khuyến
                </td>
                <td style="vertical-align: middle;">
                    DEVELOPER
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Cập nhật">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="ms-2 btn btn-danger"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    nguyenthithanhhiue
                </td>
                <td style="vertical-align: middle;">
                    Nguyễn Thị Thanh Hiếu
                </td>
                <td style="vertical-align: middle;">
                    DEVELOPER
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Cập nhật">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="ms-2 btn btn-danger"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    vongchutan
                </td>
                <td style="vertical-align: middle;">
                    Vòng Chủ Tân
                </td>
                <td style="vertical-align: middle;">
                    QUALITY_CONTROL
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Cập nhật">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="ms-2 btn btn-danger"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    doanghongquihao
                </td>
                <td style="vertical-align: middle;">
                    Đoàn Hồng Quí Hào
                </td>
                <td style="vertical-align: middle;">
                    QUALITY_CONTROL
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Cập nhật">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="ms-2 btn btn-danger"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>