require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        echarts:'plugins/echart/lib/echarts'
    }
});

require(['jquery','echarts','manage/js/requestData','manage/js/initbarFirst'], function($,echarts,RequestData,InitBar) {

    var initData = RequestData.getMainData();
    initData.then(function(resultData){
        InitBar.initBar(resultData,"histogramChart1");
    });



});