require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        message: 'plugins/message/message.min',
        page: 'plugins/page/page'
    }
});

require(['jquery', 'manage/js/requestData','common', 'manage/js/initAlarm', 'message', 'page','initUser'],
    function ($, RequestData,commom, initAlarm,message, page,initUser) {
        $(function () {
            var stationId = getUrlParam("stationId");
            // 初始化分页数据
            var pageNum = 0;
            // 记录数据
            var totalData =[];
            //报警信息
            var alarmData = RequestData.getAlarmInfos(stationId);
            alarmData.then(function(resultData){
                var data = JSON.parse(resultData);
                totalData = JSON.parse(resultData);
                // 确定一共有多少页，每页默认10条数据；
                pageNum = pageCount(data.length,10);
                alarmApi.handlePagePagination(1,10,data);
                //翻页
                $(".pagediv").createPage({
                    pageNum: pageNum,
                    current: 1,
                    backfun: function(e) {
                        alarmApi.handlePagePagination(e.current, 10, totalData);
                    }
                });
            });

            $("#yibiao-menu").click(
                function () {
                    toPageWithParmas("detail", "stationId="+stationId);
                });
            $("#buju-menu").click(
                function () {
                    toPageWithParmas("layout", "stationId="+stationId);
                });

            /**
             * 计算页数
             * @param totalnum 总数
             * @param limit 条数
             * @returns {number} 返回页数
             */
            function pageCount (totalnum,limit){
                return totalnum > 0 ? ((totalnum < limit) ? 1 : ((totalnum % limit) ? (parseInt(totalnum / limit) + 1) : (totalnum / limit))) : 0;
            }


        });
    });