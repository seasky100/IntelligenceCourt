$(window).load(function (){
    /*城市总得分*/
    getCityRank(start_time,end_time);
    var init_city ='南昌';
    getCourtRank(init_city,start_time,end_time);
    getCourtSorce(init_city,start_time,end_time);
    firstIndex();
    firstIndexSorce(init_city,start_time,end_time);
    latestCourt(init_city);
})
/*点击城市触发函数，重新加载数据*/
myChart4.on('click',function(params){
    console.log(params);
    var cityName = params.name;
    getCourtRank(cityName,start_time,end_time);
    getCourtSorce(cityName,start_time,end_time);
    firstIndexSorce(cityName,start_time,end_time);
    latestCourt(cityName);
})

/*获得城市排名*/
function getCityRank(start,end){
    var p = {"start": start, "end": end}
    $.ajax({
        type: "post",
        url: "/getCityRank",
        data: JSON.stringify(p),
        dataType: "json",
        contentType:"application/json",
        beforeSend:function(){
            myChart5.showLoading();
        },
        success: function (result) {
            myChart5.hideLoading();
            var citys = new Array();
            var sorce = new Array();
            var i = 0;
            for (var key in result) {
                citys[i] = key;
                sorce[i] = result[key];
                i++
            }
            myChart5.setOption({
                yAxis: {
                    data: citys
                },
                series: [
                    {
                        data: sorce
                    }
                ]
            })
        }
    })
}

/*法院得分排名*/
function getCourtRank(cityName,start,end){
    var p ={"start":start,"end":end}
    $.ajax({
        type:"post",
        url:'/getCourtRank?city='+cityName,
        data:JSON.stringify(p),
        dataType:"json",
        contentType:"application/json",
        success:function(result){
            $("#table1").empty();
            $("#table2").empty();
            var table1_html='<tbody><tr><th scope="col">排名</th><th scope="col">法院</th><th scope="col">得分</th></tr>';
            var len =5;
            if(result.length < 5){
                len = result.length;
            }
            for(var i=0;i<len;i++){
                table1_html = table1_html + '<tr><td><span>'+(i+1)+'</span></td><td>'+result[i].name+'</td><td>'+ (result[i].endSorce) +'分<br></td></tr>';
            }
            table1_html = table1_html +'</tbody>';
            $("#table1").append(table1_html);
            var table2_html='<ul>';
            for(var j=0;j<result.length;j++){
                table2_html = table2_html + '<li style="line-height: 40px;height: 40px;"><p ><span style="color: rgba(255,255,255,.4);display: inline-block;width: 50px">No.'+(j+1)+'</span><span style="color: rgba(255,255,255,.4);display: inline-block;width: 250px">'+ result[j].name +'</span><span style="color: rgba(255,255,255,.4);">'+ (result[j].endSorce) +'分</span></p></li>';
            }
            table2_html = table2_html + '</ul>';
            $("#table2").append(table2_html);
            $('#table2').liMarquee({
                direction: 'up',/*身上滚动*/
                runshort: false,/*内容不足时不滚动*/
                scrollamount: 20/*速度*/
            });
        }
    })
}


/*得分环状图*/
function getCourtSorce(cityName,start,end){
    var p ={"start":start,"end":end}
    $.ajax({
        type:"post",
        url:'/getCourtRank?city='+cityName,
        data:JSON.stringify(p),
        dataType:"json",
        contentType:"application/json",
        success:function(result){
            var servicedata=[];
            var courtName = [];
            var t =0;
            for(var i=0;i<result.length;i++){
                var sum = new Object();
                sum.value=result[i].endSorce;
                sum.name=result[i].name;
                servicedata[i]=sum;
                courtName[i]=result[i].name;
                t=i;
            }
            for(var j=0;j<result.length;j++){
                var ss= new Object();
                var xx = new Object();
                ss.value = 0;
                ss.name = "";
                xx.show = false;
                ss.label = xx;
                ss.labelLine=xx;
                servicedata[++t] = ss;
            }
            myChart2.setOption({
                legend:{
                    data:courtName
                },
                series:[{
                    data:servicedata
                }]
            })
        }
    })
}

/*一级指标权重比例*/
function firstIndex(){
    var p ={"start":start,"end":end}
    $.ajax({
        type:"post",
        url:'/getFirstIndex',
        data:JSON.stringify(p),
        dataType:"json",
        contentType:"application/json",
        success:function(result){
            var firstIndexName = [];
            var serData = [];
            for(var i=0;i<result.length;i++){
                var sum=new Object();
                firstIndexName[i] =result[i].fname;
                sum.value = result[i].fweight;
                sum.name=result[i].fname;
                serData[i] = sum;
            }
            myChart1.setOption({
                legend:{
                    data:firstIndexName
                },
                series:[{
                    data:serData
                }]
            });
        }
    })
}

/*一级指标得分*/
function firstIndexSorce(cityName,start,end){

    var p ={"start":start,"end":end}
    $.ajax({
        type:"post",
        url:'/getFirstIndexSorce?city='+cityName,
        data:JSON.stringify(p),
        dataType:"json",
        contentType:"application/json",
        success:function(result){
            var index = [];
            for(var j=0;j<4;j++){
                index[j] = result[j].firstIndexName;
            }
            var court=[];
            var shenpan=[];
            var zhixing=[];
            var guanli=[];
            var fuwu=[];
            var m=0;
            var n=0;
            var v=0;
            var b=0;
            var c=0;
            for(var i=0;i<result.length;i++){
                if( (i+1)%4 ==0 ){
                    court[m] = result[i].court;
                    m++;
                }
                if( i%4 == 0 ){
                    shenpan[n]=result[i].sorce;
                    n++;
                }
                if( i%4 == 1 ){
                    zhixing[v] =result[i].sorce;
                    v++;
                }
                if( i%4 == 2 ){
                    guanli[b] =result[i].sorce;
                    b++;
                }
                if( i%4 == 3 ){
                    fuwu[c] =result[i].sorce;
                    c++;
                }
            }
            var indexObj = new Array();
            indexObj[0]=shenpan;
            indexObj[1]=zhixing;
            indexObj[2]=guanli;
            indexObj[3]=fuwu;
            /*for(var t=0;t<indexObj.length;t++){
                console.log(indexObj[t])
            }*/
            var serData=[]
            for(var k=0;k<index.length;k++){
                var sum = new Object();
                sum.name= index[k];
                sum.type= 'bar';
                sum.stack = '一级指标';
                sum.data = indexObj[k];
                serData[k]=sum;
            }
            /*console.log(serData)*/
            myChart3.setOption({
                legend:{
                    data:index
                },
                xAxis:[
                    {
                        data:court
                    }
                ],
                series: serData
            });
        }
    })

}

/*最新被评分法院*/
function latestCourt(cityName){
    $.ajax({
        type:"post",
        url:'/getLatestCourt?city='+cityName,
        dataType:"json",
        success:function(result){
            $("#table3").empty();
            var table3_html='<ul>';
            for(var j=0;j<result.length;j++){
                table3_html = table3_html + '<li style="line-height: 40px;height: 40px;"><p ><span style="color: rgba(255,255,255,.4);display: inline-block;width: 70%;">'+ result[j].name +'</span><span style="color: rgba(255,255,255,.4);display: inline-block;width: 30%;">'+ result[j].time.split(" ")[0]+'</span></p></li>';
            }
            table3_html = table3_html + '</ul>';
            $("#table3").append(table3_html);
            $('#table3').liMarquee({
                direction: 'up',/*身上滚动*/
                runshort: false,/*内容不足时不滚动*/
                scrollamount: 20/*速度*/
            });
        }
    })
}