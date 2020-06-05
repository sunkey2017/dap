
var loginApi = {};

define(['common', 'manage/js/requestData'], function (common, RequestData) {
    loginApi.login = function () {

        var userName = $("#userName").val();
        var password = $("#password").val();
        var loginData = RequestData.doLogin(userName, password);
        loginData.then(function (resultData) {
            var res = JSON.parse(resultData);
            console.info("返回结果===", res);
            if (res.success){
                var txt=  "登录成功";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success, {
                    onOk:function(v){
                        toPage('main');
                    }
                });
                // $.message({
                //     message:'登录成功',
                //     type:'success'
                // });

            } else {
                window.wxc.xcConfirm(res.msg, window.wxc.xcConfirm.typeEnum.error);
            }
        })

    }

});