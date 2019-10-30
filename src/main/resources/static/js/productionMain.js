require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        echarts:'plugins/echart/lib/echarts'
    }
});

require(['jquery','echarts','manage/js/requestData','manage/js/initbarFirst','manage/js/initSpendChart','manage/js/initQualityAlarmChart','manage/js/initOnMadeChart','manage/js/initWorkHourOutChart'],
    function($,echarts,RequestData,InitChart,SpendChart,QuaAlarm,OnMadeChart,WorkHourOutChart) {

        var orderData = RequestData.getOrderData();

        //订单完成率
        orderData.then(function(resultData){
            InitChart.initChart(resultData,"orderChart");
        });

        //月成本产出
        var initSummaryOut = RequestData.getSummaryout();
            initSummaryOut.then(function(resultData){
            InitChart.initChart(resultData,"histogramChart");
        });

       // 在制品情况
        var onMadeChart = echarts.init(document.getElementById("wipChart"), null, {});
        //初始化在制品
        var initWip = RequestData.getWipData();
        initWip.then(function(resultData){
            OnMadeChart.initOnMadeChart(onMadeChart,resultData);
        });
        //定时请求数据
        window.setInterval(function(){
            initWip = RequestData.getWipData();
            initWip.then(function(resultData){
                OnMadeChart.initOnMadeChart(onMadeChart,resultData);
            });
        },5000);

        //在职人员司龄分布
        /*var initCompanyAge = RequestData.getCompanyAgeData();
        initCompanyAge.then(function(resultData){
            InitChart.initChart(resultData,"CompanyAgeChart");
        });*/

        //工时产出
        var initWorkHourOut = RequestData.initWorkHourOutData();
        initWorkHourOut.then(function(resultData){
            WorkHourOutChart.initWorkHourOutChart(JSON.parse(resultData),"CompanyAgeChart");
        });

        //在职人员学历分布
        var initFs=RequestData.getFromalSchool();
        initFs.then(function(resultData){
            InitChart.initChart(resultData,"onJobChart");
        });

        //人员花费请款
        var initSpendChart = RequestData.initSpendData();
        initSpendChart.then(function(resultData){
            SpendChart.initSpendChart(resultData,"chargeChart");
        });
        SpendChart.initSpendChart(null,"chargeChart");

        //质量
        var initQuality = RequestData.getQualityData();
        initQuality.then(function(resultData){
            QuaAlarm.initQualityChart(resultData,"qualityChart");
        });

        //警报
        var initQuality = RequestData.getAlarmData();
        initQuality.then(function(resultData){
            QuaAlarm.initAlarmChart(resultData,"alarmChart");
        });

        // 成品率
        var initYield = RequestData.getYieldData();
        initYield.then(function(resultData){
            InitChart.initChart(resultData,"yieldChart");
        });



});