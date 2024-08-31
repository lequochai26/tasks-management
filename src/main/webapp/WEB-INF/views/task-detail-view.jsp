<%--
  Created by IntelliJ IDEA.
  User: lequochai
  Date: 8/30/2024
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="user" value="${requestScope.user}" />
<c:set var="task" value="${requestScope.task}" />
<c:set var="taskProgresses" value="${requestScope.taskProgresses}" />
<c:set var="users" value="${requestScope.users}" />

<form action="${contextPath}/task-detail" method="post" class="w-100 h-100" accept-charset="UTF-8">
    <div class="w-100 h-100 d-flex flex-column overflow-y-hidden">
        <%--    Action bar--%>
        <div class="border border-1 border-bottom p-3 text-end">
            <a href="${contextPath}/tasks">
                <button type="button" class="btn btn-outline-primary">
                    <i class="bi bi-arrow-left"></i>
                    Trở về
                </button>
            </a>

            <button type="submit" class="ms-2 btn btn-primary">
                <i class="bi bi-floppy"></i>
                Lưu
            </button>

            <c:if test="${not empty task and task.status.name() eq 'NEW' and task.responsibility eq user.username}">
                <a href="${contextPath}/task-detail?action=changeStatus&id=${task.id}&status=PROCESSING">
                    <button class="ms-2 btn btn-light" type="button">
                        <i class="bi bi-clipboard-plus"></i>
                        Nhận công việc
                    </button>
                </a>
            </c:if>

            <c:if test="${not empty task and task.status.name() eq 'PROCESSING' and task.responsibility eq user.username}">
                <button class="ms-2 btn btn-success" type="button"
                        data-bs-toggle="modal"
                        data-bs-target="#finishTaskModal">
                    <i class="bi bi-clipboard-check"></i>
                    Hoàn thành
                </button>
            </c:if>
        </div>

        <%--        Form--%>
        <div class="flex-grow-1 overflow-y-scroll p-3">
<%--            Id hidden--%>
            <c:if test="${not empty task}">
                <input type="hidden" id="id" name="id" value="${task.id}" />
            </c:if>

<%--            Title--%>
            <div class="mb-5">
                <input type="text" name="title" id="title"
                       value="${not empty task ? task.title : ''}"
                       placeholder="Tiêu đề"
                       class="form-control py-2 fw-bold"
                       style="font-size: 20px;" required />
            </div>

<%--            Progress--%>
            <div class="mb-3">
                <label for="progress">Giai đoạn: </label>
                <select class="form-select w-25 d-inline-block ms-2" id="progress" name="progress">
                    <option value="">-- Chọn giai đoạn --</option>
                    <c:if test="${not empty taskProgresses}">
                        <c:forEach var="progress" items="${taskProgresses}">
                            <option value="${progress.name()}" ${not empty task.progress and task.progress.name() eq progress.name() ? 'selected' : ''}>
                                    ${progress.name()}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>

<%--            Responsibility and tester--%>
            <c:if test="${user.role.name() eq 'ADMIN'}">
                <div class="row mb-5">
                    <div class="col-6">
                        <label for="responsibility">
                            Chịu trách nhiệm:
                        </label>
                        <select id="responsibility" name="responsibility" class="form-select d-inline-block ms-2"
                                style="width: fit-content;">
                            <option value="">-- Chọn người chịu trách nhiệm --</option>
                            <c:if test="${not empty users and not empty users.get('DEVELOPER')}">
                                <c:forEach var="user" items="${users.get('DEVELOPER')}">
                                    <option value="${user.username}" ${not empty task.responsibility and task.responsibility eq user.username ? 'selected' : ''}>
                                            ${user.fullName}
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div class="col-6">
                        <label for="tester">
                            Chịu trách nhiệm kiểm thử:
                        </label>
                        <select id="tester" name="tester" class="form-select d-inline-block ms-2"
                                style="width: fit-content;">
                            <option value="">-- Chọn người chịu trách nhiệm --</option>
                            <c:if test="${not empty users and not empty users.get('QUALITY_CONTROL')}">
                                <c:forEach var="user" items="${users.get('QUALITY_CONTROL')}">
                                    <option value="${user.username}" ${not empty task.tester and task.tester eq user.username ? 'selected' : ''}>
                                            ${user.fullName}
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
            </c:if>

<%--            Description--%>
            <div>
                <textarea id="description" name="description"
                          class="use-ckeditor" placeholder="Mô tả công việc"
                          required>
                    <c:if test="${not empty task.description}">
                        ${task.description}
                    </c:if>
                </textarea>
            </div>
        </div>
    </div>
</form>

<div class="modal fade" id="finishTaskModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Xác nhận</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                Bạn có chắc muốn hoàn thành công việc này ?
            </div>

            <div class="modal-footer">
                <button class="btn btn-outline-primary me-3" data-bs-dismiss="modal">
                    Không
                </button>

                <a href="${contextPath}/task-detail?action=changeStatus&id=${task.id}&status=DONE">
                    <button class="btn btn-primary">
                        Có
                    </button>
                </a>
            </div>
        </div>
    </div>
</div>