define(['echarts'],function (echarts){

    var initIndustryInfoChart = function (optionData,divId){
        var industryInfoChart = echarts.init(document.getElementById(divId), null, {});

        datax = optionData.datax;
        data2 = optionData.data2;
        data3 = optionData.data3;

        var datas = []
        for(var i =0;i<datax.length;i++){
            var obj = {
                xAxis: {
                    data: data2[i]
                },
                title: [{
                    text: data2[i][0]+'\n'+String(data3[i]),
                    textAlign: 'center',
                    left: '40%',
                    top: '45%',
                    textStyle: {
                        color : '#fff',
                        fontSize: 20,
                    }
                }],

                series: [{
                    data: [data3[i]],
                    symbolSize: function(val) {
                        return Math.pow(val,0.25)*0.1;
                    }
                }]
            }
            datas.push(obj)
        };

        industryInfoChart.setOption({
            //timeline基本配置都写在baseoption 中
            baseOption: {
                title: [{
                    text: '',
                    textAlign: 'center',
                    left: '50%',
                    top: '45%',
                    textStyle: {
                        color: '#fff',
                        fontSize: 20,
                    }
                }],
                grid: {
                    top: 80,
                    bottom: 100,
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow',
                            label: {
                                show: true,
                                formatter: function (params) {
                                    return params.value.replace('\n', '');
                                }
                            }
                        }
                    }
                },

                timeline: {
                    axisType: 'category',
                    orient: 'vertical',
                    autoPlay: true,
                    inverse: true,
                    playInterval: 1000,
                    left: 180,
                    right: 300,
                    top: 20,
                    bottom: 20,
                    width: 55,
                    height: null,
                    data: datax,
                },
                xAxis: [
                    {
                        show : false,
                        'type':'category',
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
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '价格',
                        show:false,
                        axisLabel: {
                            textStyle: {
                                color: '#fff'
                            }
                        },
                    }
                ],
                series:[{type:'scatter'}],
                tooltip: {}
            },
            //变量则写在options中
            options:datas
        })
    };

    return {
        initIndustryInfoChart: initIndustryInfoChart
    };

});