/**
 * Created by quanquan.sun on 2017/11/6.
 */
$('#button').click(function () {
    $("#tbody").remove();
    $(".fenye").remove();
    var uName = $("#uName").val();
    console.log(uName);
    $.ajax({
        type: 'post',
        url: "/mesh",
        data: {uName: uName},
        success: function (data) {
            var paramsArr = [data.uName, data.mName, data.pName, data.gName];
            var result = data.result;
            loadData(paramsArr,result);
        }
    })
});