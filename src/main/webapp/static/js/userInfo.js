/**
 * Created by quanquan.sun on 2017/11/6.
 */

$.ajax({
    type: 'post',
    url: "/user/user",
    success: function (data) {
        console.log(data);
        var paramsArr = [data.uNameArr, data.coNameArr, data.pNumArr];
        var result = data.result;
        loadData(paramsArr,result);
    }
});

$('#button').click(function () {
    $("#tbody").remove();
    $(".fenye").remove();
    var uName = $("#uName").val();
    var coName = $("#coName").val();
    var pNum = $("#pNum").val();
    $.ajax({
        type: 'post',
        url: "/user",
        data: {uName: uName,coName:coName,pNum:pNum},
        success: function (data) {
            var paramsArr = [data.uNameArr, data.coNameArr, data.pNumArr];
            var result = data.result;
            loadData(paramsArr,result);
        }
    })
});