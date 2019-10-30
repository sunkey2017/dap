define(['echarts'],function (echarts){

    var initWorkHourOutChart = function (optionData,divId){
        var workHourOutChart = echarts.init(document.getElementById(divId), null, {});
        data = optionData.data;
        data1 = optionData.data1;

        workHourOutChart.setOption({

            title : {},
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : data1,
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
                        rotate:40,
                        textStyle: {
                            color: '#fff',
                            fontSize:13
                        }
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel: {
                        textStyle: {
                            color: '#fff'
                        }
                    },
                }
            ],
            series : [
                {
                    name:'每工时产出',
                    type:'bar',
                    data:data,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
            ]
        });
    };

    return {
        initWorkHourOutChart: initWorkHourOutChart
    };

});