/**
 * Created by quanquan.sun on 2017/11/9.
 */
$('#button').click(function () {
    $("#tbody").remove();
    $(".fenye").remove();
    var uName = $("#uName").val();
    var lName = $("#lName").val();
    var mName = $("#mName").val();
    var gName = $("#gName").val();
    var pName = $("#pName").val();
    var params = {uName: uName,lName:lName,mName:mName,gName:gName,pName:pName,page:1,url:"/light"};
    $.ajax({
        type: 'post',
        url: "/light",
        data: params,
        success: function (data) {
            var paramsArr = [data.macArr, data.uNameArr, data.mNameArr,data.pNameArr, data.gNameArr, data.lNameArr, data.stateArr];
            var result = data.result;
            var pageCount = data.count;
            loadData(paramsArr,result,pageCount,params);
        }
    })
});