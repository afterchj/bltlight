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

    <title>Light</title>

    <link rel="shortcut icon" href="#" type="image/png">
    <link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/jquery-ui.css">
    <script src="${ctx}/static/lib/jquery-1.10.2.min.js"></script>
    <script src="${ctx}/static/lib/jquery-ui-1.10.3.min.js"></script>
    <script src="${ctx}/static/lib/jquery.nicescroll.js"></script>
    <script src="${ctx}/static/lib/scripts.js"></script>
    <script src="${ctx}/static/js/publicAjax.js"></script>
    <%--<script src="${ctx}/static/js/loadHtml.js"></script>--%>
    <script type="text/javascript" src="${ctx}/static/js/vue.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var storage = window.localStorage;
            $('#nav1-1').bind("click", function () {
                storage.setItem('key', this.id);
            });
            $('#nav1-2').bind("click", function () {
                storage.setItem('key', this.id);
            });
            $('#nav1-3').bind("click", function () {
                storage.setItem('key', this.id);
            });
            $('#nav1-4').bind("click", function () {
                storage.setItem('key', this.id);
            });
            $('#nav1-5').bind("click", function () {
                storage.setItem('key', this.id);
            });
            var id = storage.getItem('key');
            if (id != undefined && id != "") {
                $(".menu-list a").css("color", "#fff");
                $(".menu-list a").css("background-color", "#424f63");
                $("#" + id).css("color", "#65cea7");
                $("#" + id).css("background-color", "#2a323f");
            }
        });
    </script>
</head>
<body>

<div class="defaultLeft">
    <%--左边导航栏--%>
    <h2 style="color: white;margin: 10px 10px;">云追光系统</h2>
    <div class="left-side-inner">
        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked custom-nav" id="leftNav">
            <li><i class="fa"></i></li>

            <li class="menu-list" hidden="hidden"><a href="${ctx}/userInfo" id="nav1-1">
                <i class="fa"></i> <span>用户系统</span></a>

            </li>
            <li class="menu-list" hidden="hidden"><a href="${ctx}/meshInfo" id="nav1-2">
                <i class="fa"></i> <span> 网络信息</span></a>

            </li>
            <li class="menu-list" hidden="hidden"><a href="${ctx}/meshSystem" id="nav1-3">
                <i class="fa"></i> <span>网络系统</span></a>

            <li class="menu-list" hidden="hidden"><a href="${ctx}/lightInfo" id="nav1-4"><i
                    class="fa"></i> <span>灯具信息</span></a>
            </li>
            </li>
            <li class="menu-list" hidden="hidden"><a href="${ctx}/productInfo" id="nav1-5"><i
                    class="fa"></i> <span>产品信息</span></a>
            </li>
            <li><a href="${ctx}/loginOut"><i class="fa"></i> <span>Login Page</span></a></li>
        </ul>
    </div>
</div>
</body>
</html>
