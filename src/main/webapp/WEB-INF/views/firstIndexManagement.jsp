<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>一级指标管理</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="../../js/jquery-3.4.1.min.js"></script>
    <script src="/layui/layui.js"></script>
</head>
<body>
<div>
    <button class="add_index" style="margin-top: 5px">添加指标</button>
</div>
<table id="FIRST" lay-filter="FIRST"></table>
<form id="editForm" method="post" hidden="hidden" style="width: 200px">
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 一级指标：<input type="text" id="edit_firstIndex"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 权重：<input type="text" height="30px" id="edit_firstWeight"></div>
</form>
<!--添加指标-->
<form id="addForm" method="post" hidden="hidden" style="width: 200px;">
    <div style="margin:15px 5px 10px 5px;width: 70px"> 一级指标：<input type="text" id="name"></div>
    <div style="margin:15px 5px 10px 5px;width: 70px"> 权重：<input type="text" height="30px" id="weight"></div>
</form>
<script type="text/html" id="col_index">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="barDemo">
    <shiro:hasPermission name="index:edit">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:lacksPermission name="index:edit">
        <a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="edit">编辑</a>
    </shiro:lacksPermission>
    <shiro:hasPermission name="index:delete">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
    <shiro:lacksPermission name="index:delete">
        <a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="del">删除</a>
    </shiro:lacksPermission>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#FIRST'
            ,height: 'full-50'
            ,url: '/getFirstIndex' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'fid', title: 'ID',templet: '#col_index',width:80, sort: true, fixed: 'left'}
                ,{field: 'fname', title: '一级指标'}
                ,{field: 'fweight', title: '权重',sort: true}
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
        table.on('tool(FIRST)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            /*console.log(data);*/
            if(layEvent === 'edit'){ //编辑
                //do something
                layer.open({
                    title: '编辑分类',
                    type: 1,
                    /*area : [ '62%', '80%' ],*/
                    maxmin: true,
                    shadeClose: true,
                    content: $('#editForm'),
                    btn: ['更新', '取消'],
                    shade: [0.8, '#393D49'],
                    success: function (layero, index) {

                        $("#edit_firstIndex").attr("value", data.fname);
                        $("#edit_firstWeight").attr("value", data.fweight);
                    },
                    btn1:function(index, layero){
                        var flag = 0;//记录指标名是否被修改，0是未修改，1是修改
                        if($("#edit_firstIndex").val() != data.fname){
                            flag = 1;
                        }
                        var param = {"fid":data.fid,"firstIndex":$("#edit_firstIndex").val(),"firstWeight":$("#edit_firstWeight").val(),"flag":flag};
                        $.ajax({
                            type: "post",
                            url: "/updateWeight",
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
                layer.confirm("是否删除该指标？", {icon: 3, title: '提示'}, function (index) {
                    //do something
                    $.ajax({
                        type: "post",
                        url: "/delIndex?level=1&id=" + data.fid,
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
    /*添加指标*/
    $(".add_index").click(function () {
        layer.open({
            type: 1,
            title: '添加指标'
            , content: $("#addForm"),
            btn: ['确定', '取消'],
            shade: [0.8, '#393D49'],
            btn1: function (index, layero) {
                //按钮【按钮一】的回调
                var params = {"name": $("#name").val(), "weight": $("#weight").val()}
                if ($("#name").val() === '' || $("#weight").val() == '') {
                    layer.msg("指标或权重不能为空！！");
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/addIndex?level=1",
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
    })
</script>
</body>
</html>
