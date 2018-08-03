function msehData(mName) {
    $.ajax({
        type: 'post',
        url: "/meshSystem",
        data: {mName: mName},
        success: function (data) {
            console.log('aa', data);
            mesh.content = data;
        }
    });
}
// msehData();

$('#button').click(function () {
    var mName = $("#mName").val();
    msehData(mName);
});
function checkPlace(id) {
    $.ajax({
        type: 'post',
        url: "/meshSystem/place",
        data: {mid: id},
        success: function (data) {
            console.log('bb', data);
            place.content = data;
        }
    });
}
function checkGroup(id) {
    $.ajax({
        type: 'post',
        url: "/meshSystem/group",
        data: {pid: id},
        success: function (data) {
            console.log('cc', data);
            group.content = data;
        }
    });
}

var url = null;
var ws = null;

function connect() {
    url = 'ws://' + window.location.host + '/blt_light/websocket';
    ws = new WebSocket(url);

    ws.onopen = function () {
        console.log('Info: 连接成功.');
    };
    ws.onmessage = function (event) {
        console.log(event.data);
    };
    ws.onclose = function (event) {
        console.log('Info: 断开连接.');
    };
}

connect();

function switchLights() {
    if (ws == null) {
        connect();
    }
    var code = document.getElementById("switch").value;
    var fd = code.indexOf("关灯");
    var switchFlag;
    if (fd == 0) {
        document.getElementById("switch").value = "开灯";
        switchFlag = 2;
    } else if (fd == -1) {
        document.getElementById("switch").value = "关灯";
        switchFlag = 1;
    }
    ws.send(switchFlag.toString());
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeConnection();
}

/**
 * 关闭连接
 */
function closeConnection() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}
// function switchLights() {
//     var code = document.getElementById("switch").value;
//     var fd = code.indexOf("关灯");
//     var switchFlag;
//     if (fd == 0) {
//         document.getElementById("switch").value = "开灯";
//         switchFlag = 2;
//     } else if (fd == -1) {
//         document.getElementById("switch").value = "关灯";
//         switchFlag = 1;
//     }
//
//     ws.send(switchFlag);

// $.ajax({
//     type: 'post',
//     url: "http://ctc-hq.tpadsz.com/blt_light/switch",
//     data: "switchFlag=" + switchFlag,
//     success: function (data) {
//         console.log('cc', data);
//         group.content = data;
//     }
// });

// }