require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min'
    }
});

require(['jquery', 'manage/js/requestData','common', 'initUser'],
    function ($, RequestData,commom, initUser) {
        $(function () {

            //用户名
            var userName = getUrlParam("userName") ;
            console.info("fghyujk", userName)
            var personalData = RequestData.getUserInfo(userName);
            personalData.then(function(resultData){
                var data = JSON.parse(resultData);
                console.info("data===", data);
                $("#username").val(data.userName);
                $("#phone").val(data.mobile);
                $("#email").val(data.email);
                $("#realname").val(data.realName);
                $("#age").val(data.age);
                if (data.sex === "1"){
                    $("#sex").val("男");
                } else if (data.sex === "0") {
                    $("#sex").val("女");
                }
                /**
                 * 渲染角色
                 */
                var roleData = RequestData.getRoles();
                // 根据已经选择的角色设置选中状态
                let checkList = data.roleBeanList;
                roleData.then(function (resultData) {
                    let res= JSON.parse(resultData);
                    let rolesinfo="";
                    console.info("asdfghjksdfghj",res)
                    res.forEach((item)=>{
                        item.checked=false;
                        checkList.forEach((check)=>{
                            if (item.id === check.id){
                                item.checked = true;
                                return;
                            }
                        })
                    })

                    for (let i=0; i<res.length; i++) {
                       if (res[i].checked){
                           rolesinfo+="<input type=\"checkbox\" checked='checked' name=\"role\" value=\"" +
                               res[i].id +
                               "\"/>" +
                               res[i].roleName +"";
                       } else {
                           rolesinfo+="<input type=\"checkbox\" name=\"role\" value=\"" +
                               res[i].id +
                               "\"/>" +
                               res[i].roleName +"";
                       }

                    }

                    $("#role-div").append(rolesinfo);
                })


                $("#update-submit").click(
                    function () {
                        let roleList = [];
                        $.each($("input:checkbox[name='role']"),function(){
                            if(this.checked){
                                roleList.push($(this).val());
                            }
                        });
                        let userObj = {};
                        userObj["roleList"] = roleList;
                        userObj["userName"] = getUrlParam("userName");

                        var updateRes = RequestData.updateUser(userObj);
                        updateRes.then(function (resultData) {
                            let res = JSON.parse(resultData);
                            if (res.success==="true"){
                                toPage("user");
                            }
                        })
                    }
                )
            });

            // 添加用户悬停效果
            $(".main-head-user").click(
                function () {
                    $("#head-menu").css('display','block');
                });
            
        });
    });