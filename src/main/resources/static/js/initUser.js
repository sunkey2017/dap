require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min'
    }
});

require(['jquery', 'manage/js/requestData'],
    function ($, RequestData) {
        var userName = $("#headUser").text();
        var userData = RequestData.getUserInfo(userName);
        userData.then(function (resultData) {
               var data = JSON.parse(resultData);
              $("#head-realName").text(data.realName);
        })


        // 添加用户悬停效果
        $(".main-head-user").click(
            function () {
                $("#head-menu").css('display','block');
            });
    });