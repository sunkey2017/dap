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
             * 渲染角色
             */
            var roleData = RequestData.getRoles();
            roleData.then(function (resultData) {
                    let res= JSON.parse(resultData);
                    let rolesinfo="";
                    for (let i=0; i<res.length; i++) {
                        rolesinfo+="<input type=\"checkbox\" name=\"role\" value=\"" +
                            res[i].id +
                            "\"/>" +
                            res[i].roleName +"";
                    }

                    $("#role-div").append(rolesinfo);
            })

            /**
             * 提交事件
             */
            $("#user-submit").click(
                function () {
                    let userName=$("#new-username").val();
                    if (userName.length === 0) {
                        $("#userName-error").css('display','block');
                        $("#user-submit").attr('disabled',true);
                        return;
                    }


                    let realName=$("#new-realname").val();
                    if (realName.length === 0) {
                        $("#realName-error").css('display','block');
                        $("#user-submit").attr('disabled',true);
                        return;
                    }


                    let password=$("#new-pwd").val();
                    if (password.length === 0) {
                        $("#passsword-error").css('display','block');
                        $("#user-submit").attr('disabled',true);
                        return;
                    }

                    let roleList = [];
                    $.each($("input:checkbox[name='role']"),function(){
                        if(this.checked){
                            roleList.push($(this).val());
                        }
                    });

                    if (roleList.length === 0) {
                        $("#role-error").css('display','block');
                        $("#user-submit").attr('disabled',true);
                        return;
                    }



                    // 获取数组信息
                    var finalRes = $('#form').serializeArray().reduce(function(result, item){
                        result[item.name] = item.value;
                        return result;
                    }, {})

                    let sex = $("input:radio[name='sex']:checked").val();
                    finalRes["sex"]=sex;
                    finalRes["roleList"]=roleList;

                    var data = RequestData.addUser(finalRes);
                    data.then(function (resultData) {
                        let res = JSON.parse(resultData);
                        if (res.success === "true") {
                            toPage("user");
                        }
                    })
                })
            /**
             * 验证用户名是否已经存在
             */
            $("#new-username").blur(function () {
                let userName=$("#new-username").val();
                var res = RequestData.selectUserByName(userName);
                res.then(function (resdata) {
                     let map = JSON.parse(resdata);
                    if (map.success === "true") {
                        $("#userName-error2").css('display','block');
                        $("#user-submit").attr('disabled',true);
                    } else {
                        $("#userName-error2").css('display','none');
                        $("#user-submit").attr('disabled',false);
                    }
                })


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