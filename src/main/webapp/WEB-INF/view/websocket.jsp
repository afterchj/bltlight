<%@ page import="com.tpadsz.after.util.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Spring4 websocket实例</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <style type="text/css">
        #connect-container {
            float: left;
            width: 300px
        }

        #connect-container div {
            padding: 5px;
        }

        #console-container {
            float: left;
            margin-left: 15px;
            width: 600px;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 170px;
            overflow-y: scroll;
            padding: 5px;
            width: 100%;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
    </style>

    <%--<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>--%>

    <script type="text/javascript">
        var url = null;
        var ws = null;
        var transports = [];

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
        }

        function connect(urlPath) {
            url = 'ws://' + window.location.host + "${ctx}" + urlPath;
//            url = 'ws://' + window.location.host + "/web_ssm" + urlPath;
            ws = new WebSocket(url);

            ws.onopen = function () {
                setConnected(true);
                log('Info: 连接成功.');
            };
            ws.onmessage = function (event) {
                log(event.data);
            };
            ws.onclose = function (event) {
                setConnected(false);
                log('Info: 断开连接.');
                log(event);
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
        }

        function echo() {
            if (ws != null) {
                var message = document.getElementById('message').value;
                ws.send(message);
            } else {
                alert('没有建立连接，请连接服务！');
            }
        }

        //        function updateUrl(urlPath) {
        //            if (urlPath.indexOf('sockjs') != -1) {
        //                url = urlPath;
        //                document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
        //            }
        //            else {
        //                if (window.location.protocol == 'http:') {
        //                    url = 'ws://' + window.location.host + "/blt_light" + urlPath;
        //                } else {
        //                    url = 'wss://' + window.location.host + "/blt_light" + urlPath;
        //                }
        //                document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
        //            }
        //        }

        function updateTransport(transport) {
            alert(transport);
            transports = (transport == 'all') ? ['websocket',
                    'xdr-streaming',
                    'xhr-streaming',
                    'iframe-eventsource',
                    'iframe-htmlfile',
                    'xdr-polling',
                    'xhr-polling',
                    'iframe-xhr-polling',
                    'jsonp-polling'] : [transport];
        }

        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }

        function testAjax(flag) {

            $.ajax({
                type: "POST",
                url: "test",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({"switchFlag": '' + flag + ''}),
                success: function (data) {
                    console.log('result', data);
                }
            });
        }
    </script>
</head>
<body>
<noscript>
    <h2 style="color: #ff0000">
        Seems your browser doesn't support Javascript! Websockets
        rely on Javascript being enabled. Please enable
        Javascript and reload this page!
    </h2>
</noscript>
<div>
    <div id="connect-container">
        <input id="radio1" type="radio" name="group1" onclick="testAjax('0');">
        <label for="radio1">开</label>
        <input id="radio2" type="radio" name="group1" onclick="testAjax('1');">
        <label for="radio2">开</label>
        <%--<input id="radio1" type="radio" name="group1" onclick="updateUrl('/websocket');">--%>
        <%--<label for="radio1">W3C WebSocket</label>--%>
        <%--<br>--%>
        <%--<input id="radio2" type="radio" name="group1" onclick="updateUrl('/sockjs/websocket');">--%>
        <%--<label for="radio2">SockJS</label>--%>
        <div id="sockJsTransportSelect" style="visibility:hidden;">
            <span>SockJS transport:</span>
            <select onchange="updateTransport(this.value)">
                <option value="all">all</option>
                <option value="websocket">websocket</option>
                <option value="xdr-streaming">xdr-streaming</option>
                <option value="xhr-streaming">xhr-streaming</option>
                <option value="iframe-eventsource">iframe-eventsource</option>
                <option value="iframe-htmlfile">iframe-htmlfile</option>
                <option value="xdr-polling">xdr-polling</option>
                <option value="xhr-polling">xhr-polling</option>
                <option value="iframe-eventsource">iframe-eventsource</option>
                <option value="iframe-xhr-polling">iframe-xhr-polling</option>
                <option value="jsonp-polling">jsonp-polling</option>
            </select>
        </div>
        <div>
            <button id="connect" onclick="connect('/websocket');">连接服务器</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
        </div>
        <div>
            <textarea id="message" style="width: 280px">测试消息!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">发送消息</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>
</html>
