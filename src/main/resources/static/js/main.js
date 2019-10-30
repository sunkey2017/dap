require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        echarts: 'plugins/echart/lib/echarts',
        bmap: 'plugins/echart/lib/bmap.min'
    }
});

require(['jquery','echarts','bmap','manage/js/requestData','manage/js/initbarFirst','manage/js/initMapChart','manage/js/singleCrystalChart','manage/js/initIndustryInfoChart'],
    function($,echarts,BMap,RequestData,InitChart,InitMapChart,SingleCrystalChart,IndustryInfoChart) {

    //公司产能分布地图
    InitMapChart.initMapChart(null,"mapChart");

    //市场占有份额
    var initData = RequestData.getMainData();
    initData.then(function(resultData){
        InitChart.initChart(resultData,"histogramChart");
    });

    //物料累计入库金额
    var initAmountData = RequestData.getAmountData();
    initAmountData.then(function(resultData){
        InitChart.initChart(resultData,"cumulativeChart");
    });

    //月物料单价
    var initBomPriceData = RequestData.getBomPriceData();
      initBomPriceData.then(function(resultData){
        InitChart.initChart(resultData,"bomPriceChart");
    });

    //单晶市场占比
    var initSingleCrystalData = RequestData.initSingleCrystalData();
        initSingleCrystalData.then(function(resultData){
            SingleCrystalChart.initSingleCrystalChart(JSON.parse(resultData),"singleCrystalChart");
    });

    //行业价格信息
    var initIndustryInfoData = RequestData.initIndustryInfoData();
        initIndustryInfoData.then(function(resultData){
            IndustryInfoChart.initIndustryInfoChart(JSON.parse(resultData),"industryInfoChart");
    });
});