require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        echarts:'plugins/echart/lib/echarts'
    }
});

require(['jquery','echarts','manage/js/requestData','manage/js/initbarFirst','manage/js/initCrytalRodChart','manage/js/initBrokenLineChart'],
    function($,echarts,RequestData,InitChart,CrytalRod,brokenLineChart) {

        //等径生长晶棒情况
        var onCrystalChart = echarts.init(document.getElementById("crystalRodChart"), null, {});
        var initCrystalRod = RequestData.getCrystalData();
        initCrystalRod.then(function(resultData){
            CrytalRod.initCrystalRodChart(onCrystalChart,resultData);
        });

        // 实时断线情况
        var onBrokenLineChart = echarts.init(document.getElementById("brokenLineChart"), null, {});
        //初始化在制品
        var initBrokenLineData = RequestData.getBrokenLineData();
        initBrokenLineData.then(function(resultData){
            brokenLineChart.initBrokenLineChart(onBrokenLineChart,resultData);
        });

        //定时请求数据
        window.setInterval(function(){
            initWip = RequestData.getBrokenLineData();
            initWip.then(function(resultData){
                brokenLineChart.initBrokenLineChart(onBrokenLineChart,resultData);
            });
        },5000);


        //需要加料炉数据预测
        var initAmountData = RequestData.getAmountData();
        initAmountData.then(function(resultData){
            InitChart.initChart(resultData,"stoveForecastChart");
        });

        // 最后一桶料等径平均工时统计
        var initYield = RequestData.getYieldData();
        initYield.then(function(resultData){
            InitChart.initChart(resultData,"lastMaterialChart");
        });



    });