<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Light:<sitemesh:write property='title'/></title>
</head>
<body>
<jsp:include page="./nav.jsp"></jsp:include>
<div class="defaultBody">
    <sitemesh:write property='body'/>
    <div class="defaultFoot">
        <span>Copyright © 2008-<script type="text/javascript">document.write(new Date().getFullYear().toString())</script> 苏州天平先进数字科技有限公司 版权所有</span>
    </div>
</div>


</body>
</html>
