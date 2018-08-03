/**
 * Created by quanquan.sun on 2017/1/24 0024.
 */
var publicAjax = function () {

    return {

        init: function (result, currentpage, paramsArr) {
            $("#tbody").remove();
            var table = document.getElementsByTagName('table')[0];
            var tbody = document.createElement('tbody');
            tbody.setAttribute('id', 'tbody');
            if (result == 0) {
                var tr = document.createElement('tr');
                var td = document.createElement('td');
                td.setAttribute('colspan', paramsArr.length);
                td.innerText = "没有结果";
                tr.appendChild(td);
                tbody.appendChild(tr);
            }
            $(document).ready(function () {
                var length = paramsArr[0].length;
                if ($("#pagination")) {
                    var pagecount = length;
                    var pagesize = 15;
                    var counts;
                    if (pagecount % pagesize == 0) {
                        counts = parseInt(pagecount / pagesize);
                    } else {
                        counts = parseInt(pagecount / pagesize) + 1;
                    }
                    //只有一页内容
                    if (pagecount <= pagesize) {
                        for (var i = 0; i < length; i++) {
                            var tr = document.createElement('tr');
                            for (var j = 0; j < paramsArr.length; j++) {
                                var td = document.createElement('td');
                                td.innerText = paramsArr[j][i];
                                tr.appendChild(td);
                            }
                            tbody.appendChild(tr);
                        }
                        table.appendChild(tbody);
                    }
                    //大于一页内容
                    if (pagecount > pagesize) {
                        if (currentpage != counts) {
                            var length1 = currentpage * pagesize;
                        } else {
                            var length1 = length;
                        }
                        for (var i = (currentpage - 1) * pagesize; i < length1; i++) {
                            var tr = document.createElement('tr');
                            for (var j = 0; j < paramsArr.length; j++) {
                                var td = document.createElement('td');
                                td.innerText = paramsArr[j][i];
                                tr.appendChild(td);
                            }
                            tbody.appendChild(tr);
                        }
                        table.appendChild(tbody);
                    }
                }
            });
        }
    };
}();