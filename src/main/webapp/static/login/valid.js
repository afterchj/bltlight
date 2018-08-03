/**
 * Created by quanquan.sun on 2017/6/19.
 */
localStorage.clear();
var result = document.getElementById('result').value;
var code ; //在全局定义验证码
if(result != ""){
    alert(result);
}
window.onload = function createCode(){


    ///////////////////////////////////////////////////////
    var nums = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];
    try{
        var canvas = document.getElementById("verifyCanvas");
        var context = canvas.getContext("2d");
        $("#support").html("html5 canvas is supported");
    }catch(err){
        $("#support").html("html5 canvas is not supported");
    }
    var i = 0;

    context.fillStyle = "#FFFFFF";
    context.fillRect(0, 0, canvas.width, canvas.height);

    var rand1 = nums[Math.floor(Math.random() * nums.length)];
    var rand2 = nums[Math.floor(Math.random() * nums.length)];
    var rand3 = nums[Math.floor(Math.random() * nums.length)];
    var rand4 = nums[Math.floor(Math.random() * nums.length)];
    for (i = 0; i < 4; i++) {
        drawline(canvas, context);
    }
    for (i = 0; i < 30; i++) {
        drawDot(canvas, context);
    }
    context.fillStyle = "#FF0000";
    context.font = "25px Arial";
    context.fillText(rand1, 10, 20);
    context.fillText(rand2, 30, 20);
    context.fillText(rand3, 50, 20);
    context.fillText(rand4, 70, 20);

    code = rand1+rand2+rand3+rand4;//把code值赋给验证码
};


$('#verifyCanvas').click(function(){
    var nums = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];
    var canvas = document.getElementById("verifyCanvas");
    var context = canvas.getContext("2d");
    var i=0;
    context.fillStyle = "#FFFFFF";
    context.fillRect(0, 0, canvas.width, canvas.height);
    var rand1 = nums[Math.floor(Math.random() * nums.length)];
    var rand2 = nums[Math.floor(Math.random() * nums.length)];
    var rand3 = nums[Math.floor(Math.random() * nums.length)];
    var rand4 = nums[Math.floor(Math.random() * nums.length)];
    for (i = 0; i < 4; i++) {
        drawline(canvas, context);
    }
    for (i = 0; i < 30; i++) {
        drawDot(canvas, context);
    }
    context.fillStyle = "#FF0000";
    context.font = "25px Arial";
    context.fillText(rand1, 10, 20);
    context.fillText(rand2, 30, 20);
    context.fillText(rand3, 50, 20);
    context.fillText(rand4, 70, 20);
    code = rand1+rand2+rand3+rand4;//把code值赋给验证码
});

function validRefresh(){
    var nums = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"];
    var canvas = document.getElementById("verifyCanvas");
    var context = canvas.getContext("2d");
    var i=0;
    context.fillStyle = "#FFFFFF";
    context.fillRect(0, 0, canvas.width, canvas.height);
    var rand1 = nums[Math.floor(Math.random() * nums.length)];
    var rand2 = nums[Math.floor(Math.random() * nums.length)];
    var rand3 = nums[Math.floor(Math.random() * nums.length)];
    var rand4 = nums[Math.floor(Math.random() * nums.length)];
    for (i = 0; i < 4; i++) {
        drawline(canvas, context);
    }
    for (i = 0; i < 30; i++) {
        drawDot(canvas, context);
    }
    context.fillStyle = "#FF0000";
    context.font = "25px Arial";
    context.fillText(rand1, 10, 20);
    context.fillText(rand2, 30, 20);
    context.fillText(rand3, 50, 20);
    context.fillText(rand4, 70, 20);
    code = rand1+rand2+rand3+rand4;//把code值赋给验证码
}

function drawline(canvas, context) {
    context.moveTo(0, Math.floor(Math.random() * canvas.height));
    context.lineTo(canvas.width, Math.floor(Math.random() * canvas.height));
    context.lineWidth = 0.5;
    context.strokeStyle = 'rgb(50,50,50)';
    context.stroke();
}

function drawDot(canvas, context) {
    var px = Math.floor(Math.random() * canvas.width);
    var py = Math.floor(Math.random() * canvas.height);
    context.moveTo(px, py);
    context.lineTo(px+1, py+1);
    context.lineWidth = 0.2;
    context.stroke();
}

function check(form){
    console.log(code);
    var  userName = document.getElementById('userName').value;
    if(userName==''){
        alert('用户名不能为空');
        document.getElementById('userName').focus();
        return false;
    }
    var password = document.getElementById('password').value;
    if(password==''){
        alert('密码不能为空');
        document.getElementById('password').focus();
        return false;
    }else{
        document.getElementById('password').value = md5(password);
    }
    /*var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写
    if(inputCode.length <= 0) { //若输入的验证码长度为0
        alert("请输入验证码！"); //则弹出请输入验证码
        document.getElementById('password').value = "";
        return false;
    }
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
        alert("验证码输入错误！@_@"); //则弹出验证码输入错误
        validRefresh();//刷新验证码
        document.getElementById("input").value = "";//清空文本框
        document.getElementById('password').value = "";
        return false;
    }*/
    return true;
}