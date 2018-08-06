/**
 * Created by quanquan.sun on 2017/11/10.
 */
function loadData(paramsArr,result,pagecount,params){
    var currentpage = 1;
    //var pagecount = paramsArr[0].length;
    var pagesize = 15;
    var counts = 0;
    if (pagecount % pagesize == 0) {
        counts = parseInt(pagecount / pagesize);
    } else {
        counts = parseInt(pagecount / pagesize) + 1;
    }
    jQuery(document).ready(function () {
        publicAjax.init(result, currentpage, paramsArr);
        if(result == 1) {
            page.init(currentpage, pagecount);
        }
    });
    $('.fenye').click(function () {
        $("#tbody").remove();
        var page1 = $(this).attr("id");
        if(page1 == "last"){
            if(localStorage.getItem('currentpage')!=null) {
                page1 = parseInt(localStorage.getItem('currentpage')) - 1;
            }else{
                page1 = currentpage;
            }
            if(page1<1){
                page1=1;
            }
        }else if(page1 == "next"){
            if(localStorage.getItem('currentpage')!=null) {
                page1 = parseInt(localStorage.getItem('currentpage')) + 1;
            }else{
                page1 = parseInt(currentpage+1);
            }
            if(page1>counts){
                page1 = counts;
            }
        }
        localStorage.setItem('currentpage',page1);
        params.page = page1;
        $.ajax({
            type: 'post',
            url: params.url,
            data: params ,
            success: function (data) {
                var paramsArr;
                if(params.url == "/light"){
                    paramsArr = [data.macArr, data.uNameArr, data.mNameArr,data.pNameArr, data.gNameArr, data.lNameArr, data.stateArr];
                }else if(params.url == "/product"){
                    paramsArr = [data.lName, data.irr_eff, data.type,data.uName, data.mName,data.num];
                }
                var result = data.result;
                var pageCount = data.count;
                loadData(paramsArr,result,pageCount,params);
                jQuery(document).ready(function () {
                    publicAjax.init(result, page1, paramsArr);
                    $(".fenye a").css("color","#535351");
                    $(".fenye a").css("background-color","#EFF2F7");
                    $("#"+page1+" a").css("color","#fff");
                    $("#"+page1+" a").css("background-color","#65CEA7");
                });
            }
        });
    });
    localStorage.clear();
}