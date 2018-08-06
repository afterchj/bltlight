/**
 * Created by quanquan.sun on 2017/11/10.
 */
var page = function () {

    return {

        init: function (currentpage, pagecount) {
            $(document).ready(function () {
                //var length = pagecount;
                if ($("#pagination")) {
                    //var pagecount = length;
                    var pagesize = 15;
                    var counts, pagehtml = "";
                    if (pagecount % pagesize == 0) {
                        counts = parseInt(pagecount / pagesize);
                    } else {
                        counts = parseInt(pagecount / pagesize) + 1;
                    }
                    //只有一页内容
                    if (pagecount <= pagesize) {
                        pagehtml = "";
                    }
                    //大于一页内容
                    if (pagecount > pagesize) {
                        pagehtml += '<li class="fenye" id="1"><a>首页</a></li>';
                        pagehtml += '<li class="fenye" id="last"><a>上一页</a></li>';
                        for (var i = 0; i < counts; i++) {
                            pagehtml += '<li class="fenye" id="' + (i + 1) + '"><a>' + (i + 1) + '</a></li>';
                        }
                        pagehtml += '<li class="fenye" id="next"><a>下一页</a></li>';
                        pagehtml += '<li class="fenye" id="'+counts+'"><a>尾页</a></li>';
                    }
                    $("#pagination").html(pagehtml);
                }
            })
        }
    }
}();