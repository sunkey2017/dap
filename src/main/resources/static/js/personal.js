require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min'
    }
});

require(['jquery', 'manage/js/requestData','common', 'initUser'],
    function ($, RequestData,commom, initUser) {
        $(function () {

            //报警信息
            var userName = $("#headUser").text();
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
                let rolesinfo="";
                let res = data.roleBeanList;
                for (let i=0; i<res.length; i++) {
                    rolesinfo+="<input type=\"checkbox\" disabled = \"disabled\" name=\"role\" value=\"" +
                        res[i].id +
                        "\"/>" +
                        res[i].roleName +"";
                }

                $("#role-div").append(rolesinfo);
            });

            // 添加用户悬停效果
            $(".main-head-user").click(
                function () {
                    $("#head-menu").css('display','block');
                });
            
        });
    });