<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/21/2024
  Time: 2:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w-100 h-100 d-flex flex-column overflow-y-hidden">
<%--    Header row--%>
    <div class="p-3 border-bottom">
        <div class="row">
<%--            Keyword input area--%>
            <div class="col-4">
                <div class="input-group">
                    <input type="text" placeholder="Tiêu đề công việc"
                           class="form-control"
                           id="keyword"
                           name="keyword" />
                    <button class="btn btn-primary">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </div>

<%--            Add task button--%>
            <div class="col-8 text-end">
                <button class="btn btn-success btn-add">
                    <i class="bi bi-plus"></i>
                    Thêm công việc
                </button>
            </div>
        </div>
    </div>

<%--    Tasks list--%>
    <div class="flex-grow-1 p-3 overflow-y-scroll">
        <table class="table">
<%--            Heading--%>
            <thead>
            <tr>
                <th>
                    Thông tin công việc
                </th>
                <th>
                    Hành động
                </th>
            </tr>
            </thead>

<%--            Body--%>
            <tbody>
            <tr>
                <td style="vertical-align: middle;">
                    <div class="w-100">
                        <div class="fw-bold">
                            Thêm chức năng ở giao diện danh sách sản phẩm
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm thực hiện:
                            </span>
                            <span class="text-primary">
                                Nguyễn Hoàng Khuyến
                            </span>
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm kiểm thử:
                            </span>
                            <span class="text-primary">
                                Đoàn Hồng Quí Hào
                            </span>
                        </div>
                        <div>
                            <span>
                                Trạng thái:
                            </span>
                            <span class="badge bg-secondary">
                                NEW
                            </span>
                        </div>
                        <div>
                            <span>
                                Giai đoạn:
                            </span>
                            <span class="badge bg-primary">
                                ANALYZING
                            </span>
                        </div>
                    </div>
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Chi tiết">
                        <i class="bi bi-eye"></i>
                    </button>
                    <button class="btn btn-danger me-3"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    <div class="w-100">
                        <div class="fw-bold">
                            Chỉnh sửa giao diện thêm mới sản phẩm
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm thực hiện:
                            </span>
                            <span class="text-primary">
                                Nguyễn Thị Thanh Hiếu
                            </span>
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm kiểm thử:
                            </span>
                            <span class="text-primary">
                                Lê Quốc Hải
                            </span>
                        </div>
                        <div>
                            <span>
                                Trạng thái:
                            </span>
                            <span class="badge bg-warning">
                                PROCESSING
                            </span>
                        </div>
                        <div>
                            <span>
                                Giai đoạn:
                            </span>
                            <span class="badge bg-primary">
                                DEVELOPING
                            </span>
                        </div>
                    </div>
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Chi tiết">
                        <i class="bi bi-eye"></i>
                    </button>
                    <button class="btn btn-danger me-3"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    <div class="w-100">
                        <div class="fw-bold">
                            Cải tiến giao diện chi tiết sản phẩm
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm thực hiện:
                            </span>
                            <span class="text-primary">
                                Nguyễn Hoàng Khuyến
                            </span>
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm kiểm thử:
                            </span>
                            <span class="text-primary">
                                Lê Quốc Hải
                            </span>
                        </div>
                        <div>
                            <span>
                                Trạng thái:
                            </span>
                            <span class="badge bg-success">
                                DONE
                            </span>
                        </div>
                        <div>
                            <span>
                                Giai đoạn:
                            </span>
                            <span class="badge bg-primary">
                                TEST_SUCCESS
                            </span>
                        </div>
                    </div>
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Chi tiết">
                        <i class="bi bi-eye"></i>
                    </button>
                    <button class="btn btn-danger me-3"
                            data-bs-toggle="tooltip"
                            title="Xóa">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>

            <tr>
                <td style="vertical-align: middle;">
                    <div class="w-100">
                        <div class="fw-bold">
                            Cải tiến giao diện quản lý kênh bán hàng
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm thực hiện:
                            </span>
                            <span class="text-primary">
                                Nguyễn Thị Thanh Hiếu
                            </span>
                        </div>
                        <div>
                            <span>
                                Chịu trách nhiệm kiểm thử:
                            </span>
                            <span class="text-primary">
                                Đoàn Hồng Quí Hào
                            </span>
                        </div>
                        <div>
                            <span>
                                Trạng thái:
                            </span>
                            <span class="badge bg-success">
                                DONE
                            </span>
                        </div>
                        <div>
                            <span>
                                Giai đoạn:
                            </span>
                            <span class="badge bg-primary">
                                TEST_SUCCESS
                            </span>
                        </div>
                    </div>
                </td>
                <td style="vertical-align: middle;">
                    <button class="btn btn-primary"
                            data-bs-toggle="tooltip"
                            title="Chi tiết">
                        <i class="bi bi-eye"></i>
                    </button>
                    <button class="btn btn-danger me-3"
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