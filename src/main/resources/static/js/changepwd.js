require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min'
    }
});

require(['jquery', 'manage/js/requestData','common', 'initUser'],
    function ($, RequestData,commom, initUser) {
        $(function () {
            // 添加用户悬停效果
            $(".main-head-user").click(
                function () {
                    $("#head-menu").css('display','block');
                });
            // 修改密码
            $("#confirmpwd").keyup(
               function () {
                   let msg = $("#errmsg-span").html();
                   let newPwd = $("#newpwd").val();
                   let confirmpwd = $("#confirmpwd").val();
                   if (newPwd !== confirmpwd) {
                       $("#errMsg").css('display','block');
                       $("#personal-submit").attr('disabled',true);
                   } else {
                       if (msg == "") {
                           $("#errMsg").css('display','none');
                           $("#errmsg-span").html('');
                           $("#personal-submit").attr('disabled',false);
                       } else {
                           $("#personal-submit").attr('disabled',true);
                       }
                   }
               }
            )

            $("#newpwd").keyup(
                function () {
                    let newPwd = $("#newpwd").val();
                    let oldpwd = $("#oldpwd").val();
                    if (newPwd === oldpwd) {
                        $("#errmsg-span").html('新旧密码输入一致');
                        $("#errMsg").css('display','block');
                        $("#personal-submit").attr('disabled',true);
                    } else {
                        $("#errMsg").css('display','none');
                        $("#errmsg-span").html('');
                        $("#personal-submit").attr('disabled',false);
                    }
                }
            )

            /**
             * 提交事件
             */
            $("#personal-submit").click(
                function () {
                    var userName = $("#headUser").text();
                    let newPwd = $("#newpwd").val();
                    let oldpwd = $("#oldpwd").val();
                    let confirmpwd = $("#confirmpwd").val();

                    if (newPwd === "" || oldpwd === "" || confirmpwd === "") {
                        $("#errmsg-span").html("请填写所有带‘*’的字段");
                        $("#errMsg").css('display','block');
                        return;
                    }
                    var data = RequestData.changePwd(userName, newPwd, oldpwd);
                    data.then(function (resultData) {
                        let res = JSON.parse(resultData);
                        if (res.success === "true") {
                            toPage("logout")
                        } else {
                            $("#errmsg-span").html(res.message);
                            $("#errMsg").css('display','block');
                        }
                    })
                })
        });

    });