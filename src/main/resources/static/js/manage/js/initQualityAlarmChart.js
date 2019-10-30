define(['echarts'],function (echarts){

    var initQualityChart = function (data,divId){
        var quaChart = echarts.init(document.getElementById(divId), null, {});
        assemblyOptionData(quaChart,JSON.parse(data),"质量");
    };
    var initAlarmChart = function (data,divId){
        var alarmChart = echarts.init(document.getElementById(divId), null, {});
        assemblyOptionData(alarmChart,JSON.parse(data),"警报");
    };

    var assemblyOptionData = function(curChart,mainData,sName){
        var data = mainData;
        curChart.setOption({
            title: {},
            legend: {
                right: 10,
                textStyle:{
                    color:'#fff'
                }
            },
            xAxis: {
                data:data[1],
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
                splitLine: {
                    lineStyle: {
                        type: 'dashed'
                    }
                },
                scale: true,
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    }
                },
            },
            series: [{
                name: sName,
                data: data[0],
                type: 'scatter',
                symbolSize: function (data) {
                    return data[1]/4;
                },

                label: {
                    emphasis: {
                        show: true,
                        formatter: function (param) {
                            return param.data[1];
                        },
                        position: 'top',
                        color:'#fff'
                    }
                },
                itemStyle: {
                    normal: {
                        shadowBlur: 10,
                        shadowColor: '#99aeb3',
                        shadowOffsetY: 5,
                        color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                            offset: 0,
                            color: 'rgb(251, 118, 123)'
                        }, {
                            offset: 1,
                            color: 'rgb(204, 46, 72)'
                        }])
                    }
                }
            }]
        });
    };


    return {
        initQualityChart: initQualityChart,
        initAlarmChart: initAlarmChart
    };

});