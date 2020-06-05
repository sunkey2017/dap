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
            /**
             * 提交事件
             */
            $("#role-submit").click(
                function () {
                    let roleCode=$("#roleCode").val();
                    if (roleCode.length === 0) {
                        $("#roleCode-error").css('display','block');
                        $("#role-submit").attr('disabled',true);
                        return;
                    }


                    let roleName=$("#roleName").val();
                    if (roleName.length === 0) {
                        $("#roleName-error").css('display','block');
                        $("#role-submit").attr('disabled',true);
                        return;
                    }


                    // 获取数组信息
                    var finalRes = $('#form').serializeArray().reduce(function(result, item){
                        result[item.name] = item.value;
                        return result;
                    }, {})


                    var data = RequestData.addRole(finalRes);
                    data.then(function (resultData) {
                        let res = JSON.parse(resultData);
                        if (res.success === "true") {
                            toPage("role");
                        }
                    })
                })
            /**
             * 验证用户名是否已Role_开头
             */
            $("#roleCode").blur(function () {
                let roleCode=$("#roleCode").val();
                $("#roleCode-error2").css('display','none');
                $("#role-submit").attr('disabled',false);
               if(!roleCode.match(new RegExp("^ROLE_.*$"))) {
                   $("#roleCode-error2").css('display','block');
                   $("#role-submit").attr('disabled',true);
                }

            })


            /*验证邮箱*/
            $("#new-email").blur(
                function () {
                    var email=$("#new-email").val();
                    var email_prompt=$("#email-error");
                    email_prompt.css('display','none');
                    $("#user-submit").attr('disabled',false);
                    var reg=/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
                    if(reg.test(email)==false){
                        email_prompt.css('display','block');
                        $("#user-submit").attr('disabled',true);

                    }
                }
            )

            /*验证手机号码*/
            $("#new-mobile").blur(
                function () {
                    var mobile=$("#new-mobile").val();
                    var mobileId=$("#mobile-error");
                    var regMobile=/^1\d{10}$/;
                    mobileId.css('display','none');
                    $("#user-submit").attr('disabled',false);

                    if(regMobile.test(mobile)==false){
                        mobileId.css('display','block');
                        $("#user-submit").attr('disabled',true);
                    }


                }
            )
        });

    });