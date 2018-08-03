<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1,height=device-height,user-scalable=0"/>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
</head>
<body>
<%--<input type="text" id="result" hidden="false" value="<%=result%>">--%>
<div align="center" style="margin-top: 10%">
    <form method="post" action="login" onsubmit="return check(this)" class="bs-example bs-example-form" role="form">
        <table>
            <tr>
                <td colspan="2">
                    <h1>用户登录</h1>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="input-group">
                        <span class="input-group-addon">用户名：</span>
                        <input type="text" name="userName" id="userName" placeholder="userName" class="form-control">
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="input-group">
                        <span class="input-group-addon">密码：</span>
                        <input type="password" name="password" id="password" placeholder="password"
                               class="form-control">
                    </div>
                </td>
            </tr>
            <!--<tr>
                <td>
                    <div class="input-group">
                        <span class="input-group-addon">验证码：</span>
                        <input type = "text" id = "input" class="form-control"/>
                    </div>
                </td>
                <td>
                    <canvas id="verifyCanvas" width="100" height="25"></canvas>
                </td>
            </tr>-->
            <tr>
                <td colspan="2">
                    <input type="submit" value="登录" class="btn btn-default">
                    <input type="button" value="取消" class="btn btn-default">
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${ctx}/static/lib/jquery-1.10.2.min.js"></script>
<script src="${ctx}/static/lib/bootstrap.min.js"></script>
<script src="${ctx}/static/lib/md5.js"></script>
<script src="${ctx}/static/lib/md5.min.js"></script>
<script src="${ctx}/static/login/valid.js"></script>
</body>
</html>