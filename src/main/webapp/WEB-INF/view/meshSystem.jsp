<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>网络系统</title>

    <link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/jquery-ui.css">
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/meshSystem.js"></script>

    <!--<script type="text/javascript">-->
    <!--function () {-->
    <!---->
    <!--}-->
    <!--</script>-->
</head>
<style>
    .panel-body {
        padding-top: 5%;
        padding-bottom: 5%
    }

    .panel-box {
        float: left;
        width: 15%;
        margin-left: 10%
    }

    .panel-box-child {
        height: 400px;
        overflow-x: auto;
        border: 1px solid
    }

    .panel-box-child ul {
        padding: 3%;
    }

    .panel-box-child li {
        list-style: none;
        margin-bottom: 1%;
    }

    li {
        float: left;
        list-style: none;
        margin-left: 10px;
    }
</style>

<script type="text/javascript">
    //    mesh = new Vue({
    //        el: '#mesh',
    //        data: {
    //            content: []
    //        }
    //    });
    //    place = new Vue({
    //        el: '#place',
    //        data: {
    //            content: []
    //        }
    //    });
    //    group = new Vue({
    //        el: '#group',
    //        data: {
    //            content: []
    //        }
    //    });


    //    $(document).ready(function () {
    //    });


</script>
<body>

<%--<div style="background:#424f63;width: auto;height: 100%;float: left">--%>
<%--<%@ include file="../../nav.jsp" %>--%>
<%--</div>--%>
<%--<div style="float: left;width: 980px;margin: 0 auto">--%>
<div class="page-heading">
    <div style="margin-top: 80px;margin-bottom: 30px">
        <ul>
            <li><input type="text" id="mName" placeholder="网络名："></li>
            <li>< <input type="button" value="搜索" id="button" class="btn btn-default"></li>
        </ul>
    </div>
</div>
<div class="wrapper">
    <div class="row">
        <div class="col-sm-12">
            <section class="panel">
                <header class="panel-heading">
                    网络信息
                </header>
                <div class="panel-body">
                    <div class="panel-box">
                        <div class="panel-box-child">
                            <ul id="mesh">
                                <li v-for="item in content" v-bind:id="item.mid" onclick=checkPlace(this.id)>
                                    {{item.mName}}
                                </li>
                            </ul>
                        </div>
                        <div align="center" style="margin-top: 5%">
                            <input type="button" value="修改" class="btn btn-default">
                            <input type="button" value="删除" class="btn btn-default">
                            <input type="button" id="switch" value="开灯" class="btn btn-default"
                                   onclick=switchLights()>
                            <input type="button" value="冻结" class="btn btn-default">
                        </div>
                    </div>
                    <div class="panel-box">
                        <div class="panel-box-child">
                            <ul id="place">
                                <li v-for="item in content" v-bind:id="item.pid" onclick=checkGroup(this.id)>
                                    {{item.pName}}
                                </li>
                            </ul>
                        </div>
                        <div align="center" style="margin-top: 5%">
                            <input type="button" value="修改" class="btn btn-default">
                            <input type="button" value="删除" class="btn btn-default">
                            <input type="button" value="开关" class="btn btn-default">
                        </div>
                    </div>
                    <div class="panel-box">
                        <div class="panel-box-child">
                            <ul id="group">
                                <li v-for="item in content">{{item.gName}}</li>
                            </ul>
                        </div>
                        <div align="center" style="margin-top: 5%">
                            <input type="button" value="修改" class="btn btn-default">
                            <input type="button" value="删除" class="btn btn-default">
                            <input type="button" value="开关" class="btn btn-default">
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<%--</div>--%>
</body>

</html>
