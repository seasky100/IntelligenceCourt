<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>智慧法院</title>
    <script type="text/javascript" src="index/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="index/js/echarts-4.2.1.min.js"></script>
    <script type="text/javascript" src="index/js/jquery.liMarquee.js"></script>
    <script type="text/javascript" src="index/js/jquery.cxselect.min.js"></script>
    <link rel="stylesheet" href="index/css/comon0.css">
</head>
<body>
<input type="text" id="select_time" style="position: absolute;z-index:1000;margin-top: 42px;margin-left: 10px"
       placeholder="选择时间">
<button type="button" id="query" style="position: absolute;z-index:1000;margin-top: 42px;margin-left: 135px">查询</button>
<div style="background-color: #010b46;background-image: radial-gradient(#012ae6,#030d4a)">
    <%--<div class="loading">
        <div class="loadbox"><img src="index/images/loading.gif"> 页面加载中...</div>
    </div>--%>
    <div class="back"></div>
    <div class="head">
        <div class="weather"><span id="showTime"></span></div>
        <h1>智慧法院大数据统计</h1>
    </div>
    <script>
        var t = null;
        var start_time = new Date().getFullYear() + "-" + (new Date().getMonth() + 1);
        var end_time = new Date().getFullYear() + "-" + (new Date().getMonth() + 2);
        t = setTimeout(time, 1000);/*開始运行*/
        function time() {
            clearTimeout(t);/*清除定时器*/
            dt = new Date();
            var y = dt.getFullYear();
            var mt = dt.getMonth() + 1;
            var day = dt.getDate();
            var h = dt.getHours();
            var m = dt.getMinutes();
            var s = dt.getSeconds();
            document.getElementById("showTime").innerHTML = y + "年" + mt + "月" + day + "日" + h + "时" + m + "分" + s + "秒";
            t = setTimeout(time, 1000);
        }
    </script>
    <div class="mainbox">
        <ul class="clearfix">
            <li>
                <div class="boxall" style="height:400px;">
                    <div class="alltitle">城市总得分</div>
                    <div class="navboxall" id="echart5"></div>
                </div>
                <div class="boxall" style="height:260px;">
                    <div class="alltitle">最新被评分法院滚动</div>
                    <div class="navboxall">
                        <div class="wraptit">
                            <span>法院</span><span></span><span></span><span>时间</span>
                        </div>
                        <div class="wrap" id="table3">

                        </div>
                        <%--<table class="table3" width="100%" border="0" cellspacing="0" cellpadding="0" id="table3">
                        </table>--%>
                    </div>
                </div>
                <div class="boxall" style="height:260px;">
                    <div class="alltitle">一级指标权重</div>
                    <div class="navboxall" id="echart1"></div>
                </div>
            </li>
            <li>

                <div class="boxall" style="height:580px;">
                    <div class="navboxall" id="map"></div>
                </div>
                <div class="boxall" style="height:355px">
                    <div class="alltitle">法院一级指标得分</div>
                    <div class="navboxall" id="echart3"></div>
                </div>
            </li>
            <li>
                <div class="boxall" style="height:300px">
                    <div class="alltitle">法院得分排名</div>
                    <div class="navboxall">
                        <table class="table1" width="100%" border="0" cellspacing="0" cellpadding="0" id="table1">
                        </table>
                    </div>
                </div>

                <div class="boxall" style="height: 250px">
                    <div class="alltitle">法院排名滚动</div>
                    <div class="navboxall" id="table2">
                    </div>

                </div>

                <div class="boxall" style="height:350px">
                    <div class="alltitle">得分环状图</div>
                    <div class="navboxall" id="echart2"></div>
                </div>


            </li>
        </ul>

    </div>
</div>
<script type="text/javascript" src="laydate/laydate.js"></script>
<script type="text/javascript" src="index/js/js.js"></script>
<script type="text/javascript" src="index/js/jiangxi.js"></script>
<script type="text/javascript" src="index/js/map.js"></script>
<script type="text/javascript" src="index/js/function.js"></script>
<script>
    /*var start_time = "";
    var end_time = "";*/
    var start ='';
    var end='';
    //执行一个laydate实例
    laydate.render({
        elem: '#select_time' //指定元素
        , type: 'month'
        , range: true
        , done: function (value, date, endDate) {
            start = date.year + "-" + date.month;
            end = endDate.year + "-" + (endDate.month + 1);
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
        }
    });
    $("#query").click(function () {
        /*console.log("看看开始"+start);
        console.log("看看结束"+end);*/
        start_time = start;
        end_time =end;
        if (start != "" && end != "") {
            start_time = start;
            end_time =end;
            getCityRank(start_time,end_time);
            var init_city ='南昌';
            getCourtRank(init_city,start_time,end_time);
            getCourtSorce(init_city,start_time,end_time);
            firstIndex();
            firstIndexSorce(init_city,start_time,end_time);
            latestCourt(init_city);
        } else {
            alert("请选择时间");
        }
    });
</script>
<script>
    $(window).load(function () {
        $('.wrap,.adduser').liMarquee({
            direction: 'up',/*身上滚动*/
            runshort: false,/*内容不足时不滚动*/
            scrollamount: 20/*速度*/
        });

    });
</script>
</body>
</html>
