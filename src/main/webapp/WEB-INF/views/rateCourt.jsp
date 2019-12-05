<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
    <title>法院评分</title>
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
    <script src="../../js/jquery-3.4.1.min.js"></script>
    <script src="../../layui/layui.js"></script>
</head>
<body>
<div style="margin-top: 10px;margin-left: 5px">
    <div class="layui-inline"><input type="text" placeholder="输入法院名称" style="height: 38px;" id="court_name"></div>
    <button type="button" class="layui-btn" id="query_court">查询</button>
</div>
<hr>
<h1 align="center" id="court_title" style="display: none;"></h1>
<hr>
<div id="main" style="width: 60%;margin:0px auto">
    <div calss="index_class" id="test">
        <%--<div class="thirdIndexName">案件关联度</div><div><div id="test1"></div></div>--%>
    </div>
    <div class="layui-btn" id="submit" style="display: none">提交</div>
</div>
<div style="display: none" id="shii_username"><shiro:principal property="username"/></div>
<script>
    var index_length = 0;//总的三级指标长度
    window.sessionStorage.clear();
    $("#query_court").click(function () {
        if ($("#court_name").val() != '') {
            $.ajax({
                type: "post",
                url: "/getCourt?court=" + $("#court_name").val(),
                dataType: "json",
                success: function (result) {
                    if (result.status == "1") {
                        $("#court_title").html(result.name);
                        document.getElementById("court_title").style.display = "";//显示
                        $.ajax({
                            type: "post",
                            url: "/getIndexLevel",
                            dataType: "json",
                            success: function (result) {
                                index_length
                                var str = '';
                                for (var i = 0; i < result.length; i++) {
                                    if(result[i].tname != null){
                                        str = str + '<div class="thirdIndexName">' + (i + 1) + '、 ' + result[i].tname + '</div><div><div name="' + result[i].tname + '"  class="star" id="rate' + (i + 1) + '"></div></div>';
                                        index_length++;
                                    }
                                }
                                $("#test").append(str);
                                rateRender();
                            }
                        });
                        document.getElementById("submit").style.display = "";//显示
                    } else if (result.status == 0) {
                        alert("输入法院不存在！！！");
                    }
                }
            });
        } else {
            alert("请输入法院名称");
        }
    });
</script>
<script>
    function rateRender() {
        layui.use('rate', function () {
            var rate = layui.rate;
            $(".star").each(function () {
                var id = $(this).attr("id");
                rate.render({
                    elem: '#' + id,
                    half: true,
                    text: true,
                    choose: function (value) {
                        var tname = document.getElementById("" + id + "").getAttribute("name");
                        window.sessionStorage.setItem(tname, value * 20);

                    }
                });
            });
        });
    }
</script>
<script>
    $("#submit").click(function () {
        console.log("==="+$("#shii_username").html())
        var storage = window.sessionStorage;
        console.log("index_len=="+index_length+";;;storlen=="+storage.length)
        if (index_length == storage.length) {

            /*console.log(storage.length);*/
            var data = '{';
            for (var i = 0, len = storage.length; i < len; i++) {
                var key = storage.key(i);
                var value = storage.getItem(key);
                data = data + '"' + key + '"' + ":" + value + ",";
            }
            data = data.substring(0, data.length - 1);
            data = data + '}';
            /*console.log(data);*/
            var dataJson = eval('(' + data + ')');
            var courtName =$("#court_title").html();
            var uid = '';
            /*获取userid*/
            $.ajax({
                type:'post',
                url:'/getUid?username='+$("#shii_username").html(),
                dataType: "json",
                contentType: "application/json",
                success:function(result){
                    console.log("uid===="+result);
                    uid = result;
                }
            })

            $.ajax({
                type: 'post',
                url: '/submitRateSorce?court=' + courtName + '&uid='+uid,
                data: JSON.stringify(dataJson),
                dataType: "json",
                contentType: "application/json",
                success: function (result) {
                    if (result == 1) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('提交评测成功!!!', {
                                icon: 1,
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                /*for (var i = 0, len = storage.length; i < len; i++) {
                                    var key = storage.key(i);
                                    if (key != "username") {
                                        sessionStorage.removeItem(key);
                                    }
                                }*/
                                window.sessionStorage.clear();
                                location.reload();
                            })
                        });
                    } else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('提交评测失败，请重新提交!', {
                                icon: 2,
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                            })
                        });
                    }
                }
            })
        } else {
            alert("有部分指标未评分！！！");
        }
    });
</script>
</body>
</html>
