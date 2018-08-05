<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>用户系统</title>

    <link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/jquery-ui.css">
    <script src="${ctx}/static/js/publicAjax.js"></script>
    <script src="${ctx}/static/js/page.js"></script>
    <script src="${ctx}/static/js/loadData.js"></script>
    <script src="${ctx}/static/js/userInfo.js"></script>
</head>

<body>

<div class="page-heading">
    <div style="float: left">
        <input type="text" id="uName" placeholder="用户名称：">
        <input type="text" id="coName" placeholder="公司名称：">
        <input type="text" id="pNum" placeholder="联系方式：">
        <input type="button" value="搜索" id="button" class="btn btn-default">
    </div>
</div>

<div class="wrapper">
    <div class="row">
        <div class="col-sm-12">
            <section class="panel">
                <header class="panel-heading">
                    用户信息
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <table class="table table-striped table-hover table-bordered" id="editable-sample">
                            <thead>
                            <tr>
                                <th>用户名</th>
                                <th>公司名称</th>
                                <th>联系方式</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.userName}</td>
                                    <td>${user.company}</td>
                                    <td>${user.telephone}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <ul class="pagination" id="pagination">
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>

</body>
</html>
