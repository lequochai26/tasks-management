<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="messages" value="${requestScope.messages}" />

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý công việc</title>

<%--    Bootstrap--%>
    <link href="${contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
    <link href="${contextPath}/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

<%--    JQuery--%>
    <script src="${contextPath}/jquery-3.7.1.min.js"></script>

<%--    CSS--%>
    <style>
        .btn-add {
            background-color: #0eb90f;
            border-color: #0eb90f;
        }

        a {
            text-decoration: none;
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

<%--    CKEditor--%>
    <link rel="stylesheet" href="${contextPath}/ckeditor5/ckeditor5.css">
    <script type="importmap">
        {
            "imports": {
                "ckeditor5": "${contextPath}/ckeditor5/ckeditor5.js",
                "ckeditor5/": "${contextPath}/ckeditor5/"
            }
        }
    </script>
    <script type="module">
        import {
            ClassicEditor,
            Essentials,
            Paragraph,
            Bold,
            Italic,
            Font,
            Strikethrough,
            Subscript,
            Superscript,
            Code,
            Heading,
            Link,
            AutoLink,
            BlockQuote,
            CodeBlock,
            Alignment,
            List,
            TodoList,
            Indent,
            IndentBlock
        } from 'ckeditor5';

        ClassicEditor
            .create( document.querySelector( '.use-ckeditor' ), {
                plugins: [
                    Essentials, Paragraph, Bold, Italic, Font, Strikethrough, Heading, Subscript, Superscript,
                    Code, Link, AutoLink, BlockQuote, CodeBlock, Alignment, List, TodoList,
                    Indent, IndentBlock
                ],
                toolbar: {
                    items: [
                        'undo', 'redo',
                        '|',
                        'heading',
                        '|',
                        'fontfamily', 'fontsize', 'fontColor', 'fontBackgroundColor',
                        '|',
                        'bold', 'italic', 'strikethrough', 'subscript', 'superscript', 'code',
                        '|',
                        'link', 'blockQuote', 'codeBlock',
                        '|',
                        'alignment',
                        '|',
                        'bulletedList', 'numberedList', 'todoList', 'outdent', 'indent',
                    ],
                    shouldNotGroupWhenFull: true
                }
            } )
            .then( editor => {
                window.editor = editor;
            } )
            .catch( error => {
                console.error( error );
            } );
    </script>

<%--    Modal interaction scripts--%>
    <script>
        function showModal(id) {
            const modal = new bootstrap.Modal(document.getElementById(id));
            modal.show();
        }
    </script>
</head>

<body>
<%--    Header URL--%>
<c:url var="headerUrl" value="../components/header.jsp">
    <c:param name="title" value="Quản lý công việc" />
</c:url>

<%--    Sidebar URL--%>
<c:url var="sidebarUrl" value="../components/sidebar.jsp" />

<div class="d-flex flex-column vh-100 overflow-y-hidden">
<%--    Header--%>
    <jsp:include page="${headerUrl}" />

<%--    Body--%>
    <div class="flex-grow-1 d-flex flex-row overflow-y-hidden">
<%--        Sidebar--%>
        <jsp:include page="${sidebarUrl}" />

<%--        View--%>
        <div class="flex-grow-1 overflow-y-hidden">
<%--            Default content--%>
            <c:if test="${empty param.viewUrl}">
                <div class="w-100 h-100 p-2 d-flex flex-column align-items-center justify-content-center">
                    <h3>
                        Chào mừng bạn đã trở lại!
                    </h3>

                    <h4>
                        Để di chuyển đến giao diện bạn mong muốn, hãy chọn một trong các mục phía bên tay trái.
                    </h4>
                </div>
            </c:if>

<%--            View display--%>
            <c:if test="${not empty param.viewUrl}">
                <jsp:include page="${param.viewUrl}" />
            </c:if>
        </div>
    </div>
</div>

<%-- User setting dialog --%>
<jsp:include page="../components/user-setting-dialog.jsp" />

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

<%--Bootstrap scripts--%>
<script>
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })
</script>