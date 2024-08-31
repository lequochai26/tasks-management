<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/21/2024
  Time: 2:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="w-100 h-100 d-flex flex-column overflow-y-hidden">
<%--    Header row--%>
    <div class="p-3 border-bottom">
        <div class="row">
<%--            Keyword input area--%>
            <div class="col-4">
                <form action="${contextPath}/tasks" method="get">
                    <div class="input-group">
                        <input type="text" placeholder="Tiêu đề công việc"
                               class="form-control"
                               id="keyword"
                               name="keyword"
                               value="${param.keyword}" />
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i>
                        </button>
                    </div>
                </form>
            </div>

<%--            Add task button--%>
            <div class="col-8 text-end">
                <a href="${contextPath}/task-detail">
                    <button class="btn btn-success btn-add">
                        <i class="bi bi-plus"></i>
                        Thêm công việc
                    </button>
                </a>
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
            <c:if test="${not empty requestScope.tasks}">
                <c:forEach var="task" items="${requestScope.tasks}">
                    <tr>
                        <td style="vertical-align: middle;">
                            <div class="w-100">
                                <div class="fw-bold">
                                    ${task.title}
                                </div>
                                <div>
                                    <span>
                                        Chịu trách nhiệm thực hiện:
                                    </span>
                                    <c:if test="${not empty task.responsibility and not empty requestScope.users and not empty requestScope.users[task.responsibility]}">
                                        <span class="text-primary">
                                            <c:out value="${requestScope.users[task.responsibility].fullName}" />
                                        </span>
                                    </c:if>
                                    <c:if test="${empty task.responsibility}">
                                        <span class="text-secondary">
                                            <c:out value="Chưa có" />
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty task.responsibility and (empty requestScope.users or empty requestScope.users[task.responsibility])}">
                                        <span class="text-secondary">
                                            <c:out value="Không xác định" />
                                        </span>
                                    </c:if>
                                </div>
                                <div>
                                    <span>
                                        Chịu trách nhiệm kiểm thử:
                                    </span>
                                    <c:if test="${not empty task.tester and not empty requestScope.users and not empty requestScope.users[task.tester]}">
                                        <span class="text-primary">
                                            <c:out value="${requestScope.users[task.tester].fullName}" />
                                        </span>
                                    </c:if>
                                    <c:if test="${empty task.tester}">
                                        <span class="text-secondary">
                                            <c:out value="Chưa có" />
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty task.tester and (empty requestScope.users or empty requestScope.users[task.tester])}">
                                        <span class="text-secondary">
                                            <c:out value="Không xác định" />
                                        </span>
                                    </c:if>
                                </div>
                                <div>
                                    <span>
                                        Trạng thái:
                                    </span>
                                    <span class="status-${task.status.name()}">
                                        <c:out value="${task.status.name()}" />
                                    </span>
                                </div>
                                <div>
                                    <span>
                                        Giai đoạn:
                                    </span>
                                    <span class="progress-${task.progress.name()}">
                                        <c:out value="${task.progress.name()}" />
                                    </span>
                                </div>
                            </div>
                        </td>
                        <td style="vertical-align: middle;">
                            <a href="${contextPath}/task-detail?id=${task.id}">
                                <button class="btn btn-primary"
                                        data-bs-toggle="tooltip"
                                        title="Chi tiết">
                                    <i class="bi bi-eye"></i>
                                </button>
                            </a>
                            <button class="btn btn-danger me-3"
                                    data-bs-toggle="tooltip"
                                    onclick="showModal('deleteTaskModal-${task.id}')"
                                    title="Xóa">
                                <i class="bi bi-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<%-- Delete task confirm dialogs --%>
<c:if test="${not empty requestScope.tasks}">
    <c:forEach items="${requestScope.tasks}" var="task">
        <div class="modal fade" id="deleteTaskModal-${task.id}">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                        <%--            Header--%>
                    <div class="modal-header">
                        <h4 class="modal-title">Cảnh báo</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                        <%--            Body--%>
                    <div class="modal-body">
                        Bạn có chắc muốn xóa công việc <span class="text-danger">
                                ${task.title}
                            </span> không ?
                    </div>

                        <%--            Footer--%>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-primary"
                                data-bs-dismiss="modal">
                            Hủy
                        </button>

                        <a href="${contextPath}/tasks?action=delete&id=${task.id}">
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

<%-- Badge initialization --%>
<script>
    $(document).ready(() => {
        const badges = {
            "status-NEW": "bg-secondary",
            "status-PROCESSING": "bg-primary",
            "status-DONE": "bg-success",
            "progress-ANALYZING": "bg-primary",
            "progress-DEVELOPING": "bg-primary",
            "progress-TESTING": "bg-primary",
            "progress-TEST_ATTEMPTING": "bg-primary",
            "progress-TEST_FAILED": "bg-danger",
            "progress-TEST_SUCCESS": "bg-success"
        };

        for (const [key, value] of Object.entries(badges)) {
            $('.' + key).addClass("badge " + value);
        }
    })
</script>