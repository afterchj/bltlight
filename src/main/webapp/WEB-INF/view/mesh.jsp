<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<body>
<link rel="stylesheet" href="${ctx}/static/css/style.css">
<link rel="stylesheet" href="${ctx}/static/css/jquery-ui.css">
<script src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/meshSystem.js"></script>

<style type="text/css">
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
</style>
<div class="page-heading">
    <div style="float: left">
        <input type="text" id="mName" placeholder="网络名：">
        <input type="button" value="搜索" id="button" class="btn btn-default">
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
                                <c:forEach items="${items}" var="item">
                                    <li id="${item.mid}" onclick=checkPlace(this.id)>${item.mName}</li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div align="center" style="margin-top: 5%">
                            <input type="button" value="修改" class="btn btn-default">
                            <input type="button" value="删除" class="btn btn-default">
                            <input type="button" id="switch" value="开灯" class="btn btn-default" onclick=switchLights()>
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
</body>
</html>

