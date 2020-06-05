require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        echarts: 'plugins/echart/lib/echarts',
        bmap: 'plugins/echart/lib/bmap.min',
        xcConfirm:'plugins/confirm/xcConfirm'
    }
});

require(['jquery','echarts','manage/js/requestData','manage/js/initSubStationChart', 'common','xcConfirm','manage/js/initSubStationOfWeather','initUser'],
    function($,echarts,RequestData,InitSubStationChart, common, xcConfirm,initSubStationOfWeather, initUser) {

        var stationId = getUrlParam("stationId")
        // 折现面积图
        // InitSubStationChart.initAreaChart(null,"areaChart");

        // 初始化日期
        let date = new Date();
        let dateStr = InitSubStationChart.dateFormat("YYYY-mm-dd", date);
        let monthStr = InitSubStationChart.dateFormat("YYYY-mm", date);
        let yearStr = InitSubStationChart.dateFormat("YYYY", date);

        // 绑定事件（日）
        $('.chart-btn-date').on('click', function(e) {
            InitSubStationChart.queryChartData(null,'day',stationId);
            e.preventDefault();


            //设置选中效果
            $(".chart-btn-date").addClass("btn-click");
            $(".chart-btn-weak").removeClass("btn-click");
            $(".chart-btn-month").removeClass("btn-click");
            $(".chart-btn-year").removeClass("btn-click");

            // 清除已选择的时间
            document.getElementById("daypicker").value="";

            $('#daypicker').val(dateStr);
            document.getElementById("daypicker").style.display="inline";
            document.getElementById("yearpicker").style.display="none";
            document.getElementById("weekpicker").style.display="none";
            document.getElementById("monthpicker").style.display="none";
        });
       // 周
        $('.chart-btn-weak').on('click', function(e) {
            InitSubStationChart.queryChartData(null,'week',stationId);
            e.preventDefault();

            $(".chart-btn-weak").addClass("btn-click");
            $(".chart-btn-date").removeClass("btn-click");
            $(".chart-btn-month").removeClass("btn-click");
            $(".chart-btn-year").removeClass("btn-click");
            document.getElementById("weekpicker").value="";

            $('#weekpicker').val(dateStr);

            document.getElementById("weekpicker").style.display="inline";
            document.getElementById("daypicker").style.display="none";
            document.getElementById("yearpicker").style.display="none";
            document.getElementById("monthpicker").style.display="none";
        });
        // 月
        $('.chart-btn-month').on('click', function(e) {
            InitSubStationChart.queryChartData(null,'month',stationId);
            e.preventDefault();

            $(".chart-btn-month").addClass("btn-click");
            $(".chart-btn-weak").removeClass("btn-click");
            $(".chart-btn-date").removeClass("btn-click");
            $(".chart-btn-year").removeClass("btn-click");
            document.getElementById("monthpicker").value="";

            $('#monthpicker').val(monthStr);

            document.getElementById("daypicker").style.display="none";
            document.getElementById("yearpicker").style.display="none";
            document.getElementById("weekpicker").style.display="none";
            document.getElementById("monthpicker").style.display="inline";
        });
        // 年
        $('.chart-btn-year').on('click', function(e) {
            InitSubStationChart.queryChartData(null,'year',stationId);
            e.preventDefault();

            $(".chart-btn-year").addClass("btn-click");
            $(".chart-btn-weak").removeClass("btn-click");
            $(".chart-btn-month").removeClass("btn-click");
            $(".chart-btn-date").removeClass("btn-click");
            document.getElementById("yearpicker").value="";
            $('#yearpicker').val(yearStr);

            document.getElementById("daypicker").style.display="none";
            document.getElementById("weekpicker").style.display="none";
            document.getElementById("yearpicker").style.display="inline";
            document.getElementById("monthpicker").style.display="none";
        });


    });

