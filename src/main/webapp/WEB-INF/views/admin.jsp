<%@ page import="javax.xml.bind.SchemaOutputResolver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script src="../../js/jquery-3.4.1.min.js"></script>
</head>
<body class="layui-layout-body">
<%
    /*Object user = session.getAttribute("user");*/ //从session里把a拿出来，并赋值给M
    /*System.out.println("user========----"+user);*/

%>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">智慧法院</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <%--<ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="../../icon/head.jpg" class="layui-nav-img">
                    <%--${sessionScope.username }--%>
                    <%--${sessionScope.user.username }--%>
                    <shiro:principal property="username"/>
                </a>
                <%--<dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>--%>
            </li>
            <li class="layui-nav-item"><a href="/logout">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">指标管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="relation">指标关系</a></dd>
                        <dd><a href="javascript:;" id="firstIndex">一级指标</a></dd>
                        <dd><a href="javascript:;" id="secondIndex">二级指标</a></dd>
                        <dd><a href="javascript:;" id="thirdIndex">三级指标</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">账号管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="staff">法院用户账号</a></dd>
                        <dd><a href="javascript:;" id="masses">普通用户账号</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;" id="role">用户权重</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">开始评分</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="select_court">法院选择</a></dd>
                        <%--<dd><a href="javascript:;">上传Excel</a></dd>--%>
                    </dl>
                </li>
                <%--<li class="layui-nav-item"><a href="javascript:;">下载评分结果</a></li>--%>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <%--<div style="padding: 15px;">内容主体区域</div>--%>
        <iframe src="/relationOFindex" id ="iframe" frameborder="0" height="100%" width="100%" ></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © IntelligenceCourt.com
    </div>
</div>
<script src="../../layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });

    $("#relation").click(function(){
        $("#iframe").attr("src","/relationOFindex");
    });
    $("#firstIndex").click(function(){
        $("#iframe").attr("src","/firstIndex");
    });
    $("#secondIndex").click(function(){
        $("#iframe").attr("src","/secondIndex");
    });
    $("#thirdIndex").click(function(){
        $("#iframe").attr("src","/thirdIndex");
    });
    $("#staff").click(function(){
        $("#iframe").attr("src","/staff");
    });
    $("#masses").click(function(){
        $("#iframe").attr("src","/masses");
    });
    $("#role").click(function(){
        $("#iframe").attr("src","/role");
    });
    $("#select_court").click(function(){
        $("#iframe").attr("src","/select_court");
    });



</script>
</body>
</html>
