define(['echarts'],function (echarts){

    var initSingleCrystalChart = function (optionData,divId){
        var singleCrystalChart = echarts.init(document.getElementById(divId), null, {});
        //var jsonOptionData = JSON.parse(optionData);
        jsonDatax = optionData.datax;
        jsonData2 = optionData.data2;
        jsonData3 = optionData.data3;
        var datas = [];
        for(var i =0;i<jsonDatax.length;i++){
            var obj = {
                xAxis: {
                    data: jsonData2[i]
                },
                series: [{
                    data: jsonData3[i]
                }]
            }
            datas.push(obj)
        };

        singleCrystalChart.setOption({
            //timeline基本配置都写在baseoption 中
            baseOption: {
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
                                },
                                textStyle:{
                                    color:'#fff'
                                }
                            }
                        }
                    }
                },

                timeline: {

                    //loop: false,
                    axisType: 'category',
                    show: true,
                    autoPlay: true,
                    playInterval: 5000,
                    data: jsonDatax,
                    label:{
                        fontSize:10,
                        rotate:30,
                        textStyle:{
                            color : '#fff'
                        }
                    }

                },
                xAxis: [
                    {
                        'type':'category',
                        axisLabel : {//坐标轴刻度标签的相关设置。
                            interval:0,
                            rotate:"45"
                        },
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
                        name: '出货量(mW)',
                        axisLabel: {
                            textStyle: {
                                color: '#fff'
                            }
                        }
                    }
                ],
                series:[{type:'bar'}],

                tooltip: {}
            },
            //变量则写在options中
            options:datas
        })


    };

    return {
        initSingleCrystalChart: initSingleCrystalChart
    };

});