<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="../../js/jquery-3.4.1.min.js"></script>
    <script src="/layui/layui.js"></script>
</head>
<body>
<table id="ROLE" lay-filter="ROLE"></table>
<form id="editForm" method="post" hidden="hidden" style="width: 200px">
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 角色：<input type="text" id="edit_rname" readonly="readonly"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 权重：<input type="text" height="30px" id="edit_weight"></div>
</form>
<script type="text/html" id="col_index">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">修改权重</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#ROLE'
            ,height: 'full-50'
            ,url: '/getRole' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'rid', title: 'ID',templet: '#col_index',width:80, sort: true, fixed: 'left'}
                ,{field: 'rname', title: '角色'}
                ,{field: 'rweight', title: '权重'}
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
        table.on('tool(ROLE)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            /*console.log(data);*/
            if(layEvent === 'edit'){ //编辑
                //do something
                layer.open({
                    title: '修改权重',
                    type: 1,
                    /*area : [ '62%', '80%' ],*/
                    maxmin: true,
                    shadeClose: true,
                    content: $('#editForm'),
                    btn: ['更新', '取消'],
                    shade: [0.8, '#393D49'],
                    success: function (layero, index) {
                        $("#edit_rname").attr("value", data.rname);
                        $("#edit_weight").attr("value", data.rweight);
                    },
                    btn1:function(index, layero){
                        var param = {"rid":data.rid,"rname":$("#edit_rname").val(),"rweight":$("#edit_weight").val()};
                        $.ajax({
                            type: "post",
                            url: "/updateRoleWeight",
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

            } else if(layEvent === 'LAYTABLE_TIPS'){
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

    });
</script>
</body>
</html>
