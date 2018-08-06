<%--
  Created by IntelliJ IDEA.
  User: hongjian.chen
  Date: 2018/7/30
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
//            $("#maniForm").bind("click", function () {
            $("#myForm").submit(function () {
                var userName = $("#name").val();
                $.ajax({
                    url: "webchat/" + userName,
//                    dataType: "json",
                    async:false,//异步还是同步
                    success: function () {
                        window.open("toChat");
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>
    This is a index page!
</h1>
<form method="get" id="myForm">
    <p>昵称： <input type="text" id="name" name="username">&nbsp;<input id="maniForm" type="submit" value="登陆"></p>
</form>
</body>
</html>
