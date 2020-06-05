require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min'
    }
});

require(['jquery', 'manage/js/requestData','common', 'initUser'],
    function ($, RequestData,commom, initUser) {
        $(function () {
            var stationId = getUrlParam("stationId");


            $("#yibiao-menu").click(
                function () {
                    $("#yibiao-menu").addClass('choose-bottom');
                    $("#buju-menu").removeClass('choose-bottom');
                    toPageWithParmas("detail", "stationId="+stationId);
                });
            $("#buju-menu").click(
                function () {
                    $("#yibiao-menu").addClass('choose-bottom');
                    $("#buju-menu").removeClass('choose-bottom');
                    toPageWithParmas("layout", "stationId="+stationId);
                });

            
        });
    });