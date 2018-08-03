<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords" content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>产品信息</title>

    <link rel="stylesheet" href="${ctx}/static/css/style.css">
    <link rel="stylesheet" href="${ctx}/static/css/jquery-ui.css">
    <script src="${ctx}/static/js/publicAjax.js"></script>
    <script src="${ctx}/static/js/page.js"></script>
    <script src="${ctx}/static/js/loadData.js"></script>
    <script src="${ctx}/static/js/productInfo.js"></script>
</head>

<body>
<div class="page-heading">
    <div style="float: left">
        <input type="text" id="proName" placeholder="产品名称：">
        <input type="text" id="proType" placeholder="产品类型：">
        <input type="text" id="mName" placeholder="网络名称：">
        <input type="text" id="gName" placeholder="组名称：">
        <input type="text" id="pName" placeholder="区名称：">
        <input type="button" value="搜索" id="button" class="btn btn-default">
    </div>
</div>
<div class="wrapper">
    <div class="row">
        <div class="col-sm-12">
            <section class="panel">
                <header class="panel-heading">
                    产品信息
                </header>
                <div class="panel-body">
                    <div class="adv-table editable-table ">
                        <table class="table table-striped table-hover table-bordered" id="editable-sample">
                            <thead>
                            <tr>
                                <th>产品名称</th>
                                <th>光效</th>
                                <th>产品类型</th>
                                <th>用户名称</th>
                                <th>网络名称</th>
                                <th>总数量</th>
                            </tr>
                            </thead>
                            <tbody>
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
