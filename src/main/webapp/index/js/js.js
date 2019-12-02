/*$(window).load(function () {
    $(".loading").fadeOut()
})*/

var myChart1 = echarts.init(document.getElementById('echart1'));
var myChart2 = echarts.init(document.getElementById('echart2'));
var myChart3 = echarts.init(document.getElementById('echart3'));
var myChart5 = echarts.init(document.getElementById('echart5'));

var option1 = {
    tooltip: {
        trigger: 'item',
        formatter: "{b} : {c} ({d}%)"
    },
    legend: {
        right: 0,
        top: 30,
        height: 160,
        itemWidth: 10,
        itemHeight: 10,
        itemGap: 10,
        textStyle: {
            color: 'rgba(255,255,255,.6)',
            fontSize: 12
        },
        orient: 'vertical',
        data: []
    },
    calculable: true,
    series: [
        {
            name: ' ',
            color: ['#62c98d', '#2f89cf', '#4cb9cf', '#53b666', '#62c98d', '#205acf', '#c9c862', '#c98b62', '#c962b9', '#7562c9', '#c96262', '#c25775', '#00b7be'],
            type: 'pie',
            radius: [30, 70],
            center: ['35%', '50%'],
            roseType: 'radius',
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },

            lableLine: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },

            data: [
                /*{value: 10, name: '图例1'},
                {value: 5, name: '图例2'},
                {value: 15, name: '图例3'},
                {value: 25, name: '图例4'},
                {value: 20, name: '图例5'},*/

            ]
        },
    ]
};
var option2 = {
    tooltip: {
        trigger: 'item',
        formatter: "{b} : {c} ({d}%)"
    },
    legend: {
        top: '0%',
        data: [],
        icon: 'circle',
        textStyle: {
            color: 'rgba(255,255,255,.6)',
        }
    },
    calculable: true,
    series: [{
        name: '',
        color: ['#62c98d', '#2f89cf', '#4cb9cf', '#53b666', '#62c98d', '#205acf', '#c9c862', '#c98b62', '#c962b9', '#c96262'],
        type: 'pie',
        //起始角度，支持范围[0, 360]
        startAngle: 0,
        //饼图的半径，数组的第一项是内半径，第二项是外半径
        radius: [51, 100],
        //支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
        center: ['50%', '50%'],

        //是否展示成南丁格尔图，通过半径区分数据大小。可选择两种模式：
        // 'radius' 面积展现数据的百分比，半径展现数据的大小。
        //  'area' 所有扇区面积相同，仅通过半径展现数据大小
        roseType: 'area',
        //是否启用防止标签重叠策略，默认开启，圆环图这个例子中需要强制所有标签放在中心位置，可以将该值设为 false。
        avoidLabelOverlap: false,
        label: {
            normal: {
                show: true,
                //  formatter: '{c}辆'
            },
            emphasis: {
                show: true
            }
        },
        labelLine: {
            normal: {
                show: true,
                length2: 1,
            },
            emphasis: {
                show: true
            }
        },
        data: [
            /*{value: 1, name: '图例1',},
            {value: 4, name: '图例2',},
            {value: 5, name: '图例3',},
            {value: 6, name: '图例4',},
            {value: 9, name: '图例5',}*//*,


            {value: 0, name: "", label: {show: false}, labelLine: {show: false}},
            {value: 0, name: "", label: {show: false}, labelLine: {show: false}},
            {value: 0, name: "", label: {show: false}, labelLine: {show: false}},
            {value: 0, name: "", label: {show: false}, labelLine: {show: false}},
            {value: 0, name: "", label: {show: false}, labelLine: {show: false}},*/


        ]
    }]
};
var option3 = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:[]
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis :{
        type : 'category',
        data : [],
        axisLabel:{
            color: function (value, index) {
                return value >= 0 ? 'green' : 'white';
            },
            formatter:function(params){
                var newParamsName = "";// 最终拼接成的字符串
                var paramsNameNumber = params.length;// 实际标签的个数
                var provideNumber = 3;// 每行能显示的字的个数
                var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                /** 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签 */

                if (paramsNameNumber > provideNumber) {// 条件等同于rowNumber>1
                    /** 循环每一行,p表示行 */
                    for (var p = 0; p < rowNumber; p++) {
                        var tempStr = "";// 表示每一次截取的字符串
                        var start = p * provideNumber;// 开始截取的位置
                        var end = start + provideNumber;// 结束截取的位置
                        // 此处特殊处理最后一行的索引值
                        if (p == rowNumber - 1) {
                            // 最后一次不换行
                            tempStr = params.substring(start, paramsNameNumber);
                        } else {
                            // 每一次拼接字符串并换行
                            tempStr = params.substring(start, end) + "\n";
                        }
                        newParamsName += tempStr;// 最终拼成的字符串
                    }

                } else {
                    // 将旧标签的值赋给新标签
                    newParamsName = params;
                }
                //将最终的字符串返回
                return newParamsName
            }
        }
    },
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : []
};
var option5 = {
    tooltip: {
        show: false
    },
    grid: {
        top: '0%',
        left: '65',
        right: '14%',
        bottom: '0%',
    },
    xAxis: {
        min: 0,
        max: 500,
        splitLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisLabel: {
            show: false
        }
    },
    yAxis: {
        data: [],
        //offset: 15,
        axisTick: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisLabel: {
            color: 'rgba(255,255,255,.6)',
            fontSize: 14
        }
    },
    series: [{
        type: 'bar',
        label: {
            show: true,
            zlevel: 10000,
            position: 'right',
            padding: 10,
            color: '#49bcf7',
            fontSize: 14,
            formatter: '{c}'

        },
        itemStyle: {
            color: '#49bcf7'
        },
        barWidth: '15',
        data: [],
        z: 10
    }, {
        type: 'bar',
        barGap: '-100%',
        itemStyle: {
            color: '#fff',
            opacity: 0.1
        },
        barWidth: '15',
        data: [500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500],
        z: 5
    }],
};

myChart1.setOption(option1);
myChart2.setOption(option2);
myChart3.setOption(option3);
myChart5.setOption(option5);

window.addEventListener("resize", function () {
    myChart1.resize();
    myChart2.resize();
    myChart3.resize();
    myChart5.resize();
});












