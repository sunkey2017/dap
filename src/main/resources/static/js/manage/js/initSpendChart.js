define(['echarts'],function (echarts){

    var initSpendChart = function (option,divId){
        var spendChart = echarts.init(document.getElementById(divId), null, {});

        var data2 = [[['差旅费',1000, 1600,1400, 1500],['司龄分布',2,3,4,6]],[['差旅费',1300, 1800, 1400, 1700],['职级分布',4,6,8,9]]];
        var schema1 = [
            {name: '差旅费', index: 0},
            {name: '应酬费', index: 1}

        ];

        var schema2 = [
            {name: '司龄分布', index: 0},
            {name: '职级分布', index: 1}

        ];

        var fieldNames1 = schema1.map(function (item) {
            return item.name;
        });

        var fieldNames2 = schema2.map(function (item) {
            return item.name;
        });
        var app = {};
        app.config = null;
        app.config = {
            xAxis: '司龄分布',
            yAxis: '差旅费',
            onChange: function () {
                if (data) {
                    myChart.setOption({

                        xAxis: {
                            type: 'category',
                            data:data2.filter(function temp(x){return x[0][0]==app.config.yAxis && x[1][0]==app.config.xAxis})[0][1].slice(1),
                            name: app.config.xAxis
                        },
                        yAxis: {
                            name: app.config.yAxis
                        },
                        series: {

                            data:data2.filter(function temp(x) {return x[0][0]==app.config.yAxis && x[1][0]==app.config.xAxis})[0][0].slice(1),
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'}
                                ]
                            }

                        }
                    });
                }
            }
        };

        app.configParameters = {
            xAxis: {
                options: fieldNames2
            },
            yAxis: {
                options: fieldNames1
            }
        };

        spendChart.setOption({
            title: {},
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, 0.01],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            },
            xAxis: {
                type: 'category',
                data: [2,3,4,6],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        color:['#f2f2f2'],
                        width:0,
                        type:'solid'
                    }
                }
            },
            series: [
                {
                    type: 'bar',
                    data: [2460,1640,2900,3204]
                }
            ]
        });
    };

    return {
        initSpendChart: initSpendChart
    };

});