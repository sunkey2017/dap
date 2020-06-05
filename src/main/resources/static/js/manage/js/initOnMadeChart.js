define(['echarts'],function (echarts){

    var initOnMadeChart = function (onMadeChart,optionData){
        var jsonOptionData = JSON.parse(optionData);
        var xData = jsonOptionData.area;
        var yData = jsonOptionData.data;
        onMadeChart.setOption({
            title : {
                text: '',
                textStyle:{
                    color : '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            xAxis: {
                type: 'category',
                data: xData,
                splitLine: {
                    lineStyle: {
                        type: 'dashed'
                    }
                },
                axisLine:{
                    lineStyle:{
                        color: '#87cefa'
                    },
                },
                axisLabel : {
                    interval:0,
                    rotate:40,
                    textStyle: {
                        color: '#fff',
                        fontSize:13
                    }
                }
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    }
                },
            },
            series : [
                {
                    tooltip:{},
                    name:'直接访问',
                    type:'bar',
                    barWidth: '60%',
                    data:yData,
                    itemStyle:{
                        normal:{
                            color:function(params){
                                if(params.value >0 && params.value <300){
                                    return "Chartreuse";
                                }else if(params.value >=300 && params.value<=500 ){
                                    return "yelllow";
                                }
                                return "red";
                            }
                        }
                    }
                }
            ]
        });
    };

    return {
        initOnMadeChart: initOnMadeChart
    };

});