define(['echarts'],function (echarts){

    var initChart = function (option,divId){
        var chart = echarts.init(document.getElementById(divId), null, {});
        var optionJson = JSON.parse(option)
        chart.setOption(optionJson);
    };

    return {
        initChart: initChart
    };

});