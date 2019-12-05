<%--
  Created by IntelliJ IDEA.
  User: yezhi
  Date: 2019/11/12
Time: 16:47
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<meta charset="UTF-8">
<title>智慧法院平台</title>
<link rel="stylesheet" type="text/css" href="login/css/style2.0.css">
<style type="text/css">
    ul li {
        font-size: 30px;
        color: #2ec0f6;
    }

    .tyg-div {
        z-index: -1000;
        float: left;
        position: absolute;
        left: 5%;
        top: 20%;
    }

    .tyg-p {
        font-size: 14px;
        font-family: 'microsoft yahei';
        position: absolute;
        top: 135px;
        left: 60px;
    }

    .tyg-div-denglv {
        z-index: 1000;
        float: right;
        position: absolute;
        right: 3%;
        top: 10%;
    }

    .tyg-div-form {
        background-color: #23305a;
        width: 300px;
        height: auto;
        margin: 120px auto 0 auto;
        color: #2ec0f6;
    }

    .tyg-div-form form {
        padding: 10px;
    }

    .tyg-div-form form input[type="text"] {
        width: 270px;
        height: 30px;
        margin: 25px 10px 0px 0px;
    }

    .tyg-div-form form input[type="password"] {
        width: 270px;
        height: 30px;
        margin: 25px 10px 0px 0px;
    }

    .tyg-div-form form button {
        cursor: pointer;
        width: 270px;
        height: 44px;
        margin-top: 25px;
        padding: 0;
        background: #2ec0f6;
        -moz-border-radius: 6px;
        -webkit-border-radius: 6px;
        border-radius: 6px;
        border: 1px solid #2ec0f6;
        -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
        0 2px 7px 0 rgba(0, 0, 0, .2);
        -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
        0 2px 7px 0 rgba(0, 0, 0, .2);
        box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
        0 2px 7px 0 rgba(0, 0, 0, .2);
        font-family: 'PT Sans', Helvetica, Arial, sans-serif;
        font-size: 14px;
        font-weight: 700;
        color: #fff;
        text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
        -o-transition: all .2s;
        -moz-transition: all .2s;
        -webkit-transition: all .2s;
        -ms-transition: all .2s;
    }
</style>
<body>
<div class="tyg-div">
    <ul>
        <li>让</li>
        <li>
            <div style="margin-left:20px;">数</div>
        </li>
        <li>
            <div style="margin-left:40px;">据</div>
        </li>
        <li>
            <div style="margin-left:60px;">改</div>
        </li>
        <li>
            <div style="margin-left:80px;">变</div>
        </li>
        <li>
            <div style="margin-left:100px;">生</div>
        </li>
        <li>
            <div style="margin-left:120px;">活</div>
        </li>
    </ul>
</div>
<div id="contPar" class="contPar">
    <div id="page1" style="z-index:1;">
        <div class="title0">智慧法院平台</div>
        <div class="title1">公以至仁，正以尽义，廉以树威，明以立信</div>
        <div class="imgGroug">
            <ul>
                <img alt="" class="img0 png" src="login/img/page1_0.png">
                <img alt="" class="img1 png" src="login/img/page1_1.png">
                <img alt="" class="img2 png" src="login/img/page1_2.png">
            </ul>
        </div>
        <img alt="" class="img3 png" src="login/img/page1_3.jpg">
    </div>
</div>
<div class="tyg-div-denglv">
    <div class="tyg-div-form">
        <form action="">
            <h2>登录</h2>
            <p class="tyg-p">欢迎访问 智慧能力</p>
            <div style="margin:5px 0px;">
                <input type="text" id="loginNumber" name="username" placeholder="请输入账号..."/>
            </div>
            <div style="margin:5px 0px;">
                <input type="password" id="loginPassword" name="password" placeholder="请输入密码..."/>
            </div>
            <%--<div style="margin:5px 0px;">
                <input type="text" id="code" style="width:150px;" placeholder="请输入验证码..."/>
                <img src="/getVerifyCode" style="vertical-align:bottom;" alt="验证码"
                     onclick="this.src=this.src+'?'+Math.random()"/>
            </div>--%>
            <button type="button" id="loginButton">登<span style="width:20px;"></span>录</button>
        </form>
    </div>
</div>
<a href="/admin" id="jump" style="display:none;"></a>
<script type="text/javascript" src="login/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="login/js/com.js"></script>
<%--<!--[if IE 6]>
<script language="javascript" type="text/javascript" src="./script/ie6_png.js"></script>
<script language="javascript" type="text/javascript">
    DD_belatedPNG.fix(".png");
</script>
<![endif]-->--%>
<script>
    /*$("#loginButton").click(function () {
        /!*console.log("hhhh")*!/
        var username = $("#loginNumber").val();
        var password = $("#loginPassword").val();
        var code = $("#code").val();
        /!*console.log(code);*!/
        var params = {"username": username, "password": password};
        $.ajax({
            type: "post",
            url: "/loginCheck?code=" + code,
            dataType: 'json',
            data: JSON.stringify(params),
            contentType: 'application/json',
            success: function (result) {
                console.log("登录测试：" + result);
                if (result == 1) {
                    /!*alert("登陆成功");*!/
                    $("#jump")[0].click();
                } else if (result == -20) {
                    alert("验证码错误！！！");
                    window.location.reload()
                } else if (result == -30) {
                    alert("密码错误！！！");
                    window.location.reload()
                }else if(result == -12){
                    alert("账号不存在")
                }
            }
        });
    });*/
    $("#loginButton").click(function(){
        /*发送请求，做登录认证*/
        $.post("/loginCheck",$("form").serialize(),function(data){
            console.log(data);
            /*将json格式字符串转成json数据*/
            data = $.parseJSON(data);
            if(data.success){
                /*跳转到首页*/
                $("#jump")[0].click();
            }else{
                alert(data.msg);
            }
        });
    })
</script>
</body>
</html>
