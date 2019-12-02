<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="../../js/jquery-3.4.1.min.js"></script>
    <script src="/layui/layui.js"></script>
</head>
<body>
<div>
    <button class="add_account" style="margin-top: 5px">添加账号</button>
</div>
<table id="ACCOUNT" lay-filter="ACCOUNT"></table>
<form id="editForm" method="post" hidden="hidden" style="width: 200px">
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 账号：<input type="text" id="edit_username" readonly="readonly"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 密码：<input type="text" height="30px" id="edit_password"></div>
</form>
<!--添加账号-->
<form id="addForm" method="post" hidden="hidden" style="width: 200px;">
    <div style="margin:15px 5px 10px 5px;width: 70px"> 账号：<input type="text" id="add_username"></div>
    <div style="margin:15px 5px 10px 5px;width: 70px"> 密码：<input type="text" height="30px" id="add_password"></div>
</form>
<script type="text/html" id="col_index">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改密码</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#ACCOUNT'
            ,height: 'full-50'
            ,url: '/getAccount?role=2' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'uid', title: 'ID',templet: '#col_index',width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名'}
                ,{fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]],
            parseData:function(res){
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
        });

        //监听工具条
        table.on('tool(ACCOUNT)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            /*console.log(data);*/
            if(layEvent === 'edit'){ //编辑
                //do something
                layer.open({
                    title: '修改密码',
                    type: 1,
                    /*area : [ '62%', '80%' ],*/
                    maxmin: true,
                    shadeClose: true,
                    content: $('#editForm'),
                    btn: ['更新', '取消'],
                    shade: [0.8, '#393D49'],
                    success: function (layero, index) {
                        $("#edit_username").attr("value", data.username);
                        /*$("#edit_secondWeight").attr("value", data.sweight);*/
                    },
                    btn1:function(index, layero){
                        var param = {"uid":data.uid,"username":$("#edit_username").val(),"password":$("#edit_password").val()};
                        $.ajax({
                            type: "post",
                            url: "/updatePassword",
                            data:JSON.stringify(param),
                            dataType: "json",
                            contentType: "application/json",
                            success: function (result) {
                                if(result == '1'){
                                    layer.msg('修改成功', {
                                        icon: 1,
                                        offset: '260px',
                                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                                        ,end: function(){
                                            window.location.reload();
                                        }
                                    });
                                }else{
                                    layer.msg("修改失败",{
                                        icon: 2,
                                        offset: '260px',
                                        time: 2000
                                    })
                                }

                            }
                        });
                    }

                });

            }else if(layEvent === 'del'){ //删除
                layer.confirm("是否删除该用户？", {icon: 3, title: '提示'}, function (index) {
                    //do something
                    $.ajax({
                        type: "post",
                        url: "/delAccount?uid=" + data.uid,
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result) {

                        }
                    });
                    obj.del();
                    layer.close(index);
                    window.location.reload();
                });
            } else if(layEvent === 'LAYTABLE_TIPS'){
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

    });
</script>
<script>
    /*添加账号*/
    $(".add_account").click(function () {
        layer.open({
            type: 1,
            title: '添加账号'
            , content: $("#addForm"),
            btn: ['确定', '取消'],
            shade: [0.8, '#393D49'],
            btn1: function (index, layero) {
                var params = {"username": $("#add_username").val(), "password": $("#add_password").val()}
                if ($("#add_username").val() === '' || $("#add_password").val() == '') {
                    layer.msg("用户名或密码不能为空！！");
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/addAccount?role=2",
                        data: JSON.stringify(params),
                        dataType: "json",
                        contentType: "application/json",
                        beforeSend:function(){
                            loadingFlag= layer.msg('正在读取数据，请稍候……', { icon: 16, shade: 0.01,shadeClose:false,time:60000 });
                        },
                        success: function (respMsg) {
                            $("#loading").empty(); //ajax返回成功，清除loading图标
                            /*console.log(respMsg)*/
                            layer.close(loadingFlag);
                            if (respMsg == "1") {
                                /*layer.closeAll();*/
                                layer.msg('添加成功', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                    ,end: function(){
                                        window.location.reload();
                                    }
                                });
                            } else {
                                layer.msg('添加失败', {
                                    icon: 3,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                });
                            }

                        }
                    });
                }

            }
        });

        //将一级标签添加到下拉框中
        $("#init").siblings(".class").remove();
        $.ajax({
            url: "/getOneIndexList?level=1",
            type: "GET",
            dataType: "json",
            success: function (result) {
                var downBox = document.getElementById("parent"); //selectCategory为select定义的id

                for( var i = 0; i < result.length;i++){
                    var option = document.createElement("option");// 创建添加option属性
                    option.setAttribute("value",result[i].fid);// 给option的value添加值
                    option.innerText = result[i].fname;// 打印option对应的纯文本
                    downBox.appendChild(option);//给select添加option子标签
                }
            }
        })
    })
</script>
</body>
</html>
