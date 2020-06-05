require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        echarts: 'plugins/echart/lib/echarts',
        bmap: 'plugins/echart/lib/bmap.min'
    }
});

require(['jquery', 'echarts', 'bmap', 'manage/js/requestData', 'manage/js/initMainStationChart','initUser'],
    function ($, echarts, BMap, RequestData, InitMainStation, initUser) {

        //地图
        var allPowerStationData = RequestData.allPowerStationData("");
        allPowerStationData.then(function(resultData){
            InitMainStation.initMapChart(resultData, "mapChart");
            $(function () {
                InitMainStation.initTableAndInfo(resultData,'all');
            })
        });


    });