/**
 * Created by quanquan.sun on 2017/11/13.
 */
$('#button').click(function () {
    $("#tbody").remove();
    $(".fenye").remove();
    var proName = $("#proName").val();
    var proType = $("#proType").val();
    var mName = $("#mName").val();
    var gName = $("#gName").val();
    var pName = $("#pName").val();
    var params = {proName: proName,proType:proType,mName:mName,gName:gName,pName:pName,page:1,url:"/product"};
    $.ajax({
        type: 'post',
        url: "/product",
        data: params,
        success: function (data) {
            var paramsArr = [data.lName, data.irr_eff, data.type,data.uName, data.mName,data.num];
            var result = data.result;
            var pageCount = data.count;
            loadData(paramsArr,result,pageCount,params);
        }
    })
});