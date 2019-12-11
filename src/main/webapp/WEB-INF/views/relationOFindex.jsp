<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
    <title>指标关系</title>
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
    <script src="../../js/jquery-3.4.1.min.js"></script>
</head>
<body>
<table id="relation" lay-filter="relation"></table>
<form id="editForm" method="post" hidden="hidden" style="width: 200px">
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 一级指标：<input type="text" id="edit_firstIndex" readonly="readonly"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 权重：<input type="text" height="30px" id="edit_firstWeight"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 二级指标：<input type="text" id="edit_secIndex" readonly="readonly"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 权重：<input type="text" height="30px" id="edit_secWeight"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 二级指标：<input type="text" id="edit_thirIndex" readonly="readonly"></div>
    <div style="margin:15px 5px 10px 5px;color: #0C0C0C;width: 70px"> 权重：<input type="text" height="30px" id="edit_thirWeight"></div>
</form>
<script src="../../layui/layui.js"></script>
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
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#relation'
           /* ,height: 'full-20'*/
            ,url: '/getRelation' //数据接口
            ,page: true //开启分页
            ,toolbar:true
            ,title:'指标关系表'
            ,totalRow:true
            ,defaultToolbar: ['filter', 'print', 'exports']
            ,cols: [[ //表头
                {field: 'id', title: 'ID',templet: '#col_index', width:80, sort: true, fixed: 'left'}
                ,{field: 'firstIndex', title: '一级指标'}
                ,{field: 'firstWeight', title: '权重',sort: true}
                ,{field: 'secondIndex', title: '二级指标'}
                ,{field: 'secondWeight', title: '权重',sort: true}
                ,{field: 'thirdIndex', title: '三级指标'}
                ,{field: 'thirdWeight', title: '权重',sort: true}
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
        table.on('tool(relation)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            /*console.log(data);
            firstIndex: "智慧审判"
            firstWeight: 0.5
            secondIndex: "案件关联"
            secondWeight: 0.5
            thirdIndex: "案件关联度"
            thirdWeight: 0.5*/
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
                        $("#edit_firstIndex").attr("value", data.firstIndex);
                        $("#edit_firstWeight").attr("value", data.firstWeight);
                        $("#edit_secIndex").attr("value", data.secondIndex);
                        $("#edit_secWeight").attr("value", data.secondWeight);
                        $("#edit_thirIndex").attr("value", data.thirdIndex);
                        $("#edit_thirWeight").attr("value", data.thirdWeight);
                    },
                    btn1:function(index, layero){
                        var param = {"firstIndex":$("#edit_firstIndex").val(),"firstWeight":$("#edit_firstWeight").val(),"secIndex":$("#edit_secIndex").val(),"secWeight":$("#edit_secWeight").val(),"thirIndex":$("#edit_thirIndex").val(),"thirWeight":$("#edit_thirWeight").val()};
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
                                }else if(result == "-1"){
                                    layer.msg("没有权限",{
                                        icon: 2,
                                        offset: '260px',
                                        time: 2000
                                    })
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
