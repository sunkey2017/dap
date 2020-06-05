
var alarmApi = {};

define(['common', 'manage/js/requestData'], function (common, RequestData) {
    alarmApi.sendMsg = function (index,number,text, sendFlag, alarmType) {

        const spanid = "alarm-span-success-"+index;
        const imgid = "#img-"+index;
        $(imgid).attr("src", "images/loading.gif");
        document.getElementById(spanid).innerText = "发送指令中";
        $(".alarm-span").css('display','inline-block');
        // document.getElementById("alarm-span-success").style.marginLeft = "10px";
        document.getElementById(spanid).style.color = "#3aa7e1";

        // 指令转换
        if(sendFlag === "0") {
            text = "FAN_ON";
        }else if(sendFlag === "1") {
            text = "FAN_OFF";
        }
        var alarmData = RequestData.sendMessage(index, number,text, alarmType);
        alarmData.then(function (resultData) {
            if ("00" == resultData.errorCode) {

                // 根据是否发送成功控制按钮状态(FAN_ON--开 FAN_OFF--关)
                // 未发送->已发送
                if("0"=== sendFlag){
                    document.getElementById(spanid).innerText = "发送指令成功";
                    document.getElementById(spanid).style.color = "#82c982";
                    $(imgid).attr("src", "images/operate-success.png");
                    // 避免连续点击，刷新界面
                    location.reload();
                }
                // 已发送->关闭
               else if ("1" === sendFlag) {
                    document.getElementById(spanid).innerText = "发送指令成功";
                    document.getElementById(spanid).style.color = "#8a8a8a";
                    $(imgid).attr("src", "images/operate-dis.png");
                    // 操作成功的将按钮变为绿色表示已经成功操作，并去除点击事件；
                    $(imgid).attr("onclick",null);
                }

                // setTimeout(function (){
                //
                // }, 1500);
                // $.message({
                //     message:'发送成功',
                //     type:'success'
                // });

            } else{
                $.message({
                    message:resultData.errorMsg,
                    type:'error'
                });
            }

        });
    }

    /**
     * 处理分页事件
     * @param pageNo 当前页
     * @param pageSize 条数（默认10）
     * @param array 数组
     */
    alarmApi.handlePagePagination = function (pageNo, pageSize, array) {
        var offset = (pageNo - 1) * pageSize;
        var newArray = (offset + pageSize >= array.length) ? array.slice(offset, array.length) : array.slice(offset, offset + pageSize);
        renderTable(newArray);
    }

    var renderTable = function (data) {
        let infoList = " <ul class=\"th\">\n" +
            "            <li class=\"alerm-ul one\">警告内容</li>\n" +
            "            <li class=\"alerm-ul two\">告警类型</li>\n" +
            "            <li class=\"alerm-ul three\">告警时间</li>\n" +
            "            <li class=\"alerm-ul four\">告警等级</li>\n" +
            "            <li class=\"alerm-ul six\">报警组件编码</li>\n" +
            "            <li class=\"alerm-ul seven\">操作</li>\n" +
            "        </ul>";
        let closeFlag = false;
        for (let item in data){
            const msg = data[item].componentNumber+"-"+data[item].alarmText;
            var alarmType = "";
            if (data[item].alarmType==="1") {
                alarmType = "温度";
            } else if (data[item].alarmType==="2") {
                alarmType = "电压";
            }
            let alarmLevel = "";
            let imgStr =  "" ;
            if (data[item].alarmLevel==="1") {
                alarmLevel = "<span class='alarm-low'>低</span>";
                imgStr="<img src=\"images/operate-dis.png\" class=\"alarm-img\">"+"<span class='alarm-span' style='font-size: 10px;'></span>";
            } else if (data[item].alarmLevel==="2") {
                alarmLevel = "<span class='alarm-middle'>中</span>";
                imgStr="<img src=\"images/operate-dis.png\" class=\"alarm-img\">" +"<span class='alarm-span' style='font-size: 10px;'></span>"
            }else if (data[item].alarmLevel==="3") {
                let tid = data[item].alarmId;
                let imgid = "img-"+tid;
                let spanid = "alarm-span-success-"+tid;
                alarmLevel = "<span class='alarm-height'>高</span>";
                // 根据是否发送控制按钮颜色 0-未发送 1-已发送
                if("0"===data[item].sendFlag) {
                    imgStr = " <a class=\"hint--top hint--rounded hint--info\" data-hint=\"开启远程控制\" target=\"_blank\">" +"<img id ='" +
                        imgid +
                        "'src=\"images/operate.png\" class=\"alarm-img\" alt=\"\" onclick=\"alarmApi.sendMsg('" +
                        tid +
                        "','" +
                        data[item].componentNumber +"','"+data[item].alarmText+"','"+data[item].sendFlag+"','"+data[item].alarmType+
                        "')\">"+"</a><span id='" +
                        spanid +
                        "' class='alarm-span' style='font-size: 10px;'></span> ";
                } else if ("1"===data[item].sendFlag) {
                    closeFlag = true;
                    if (data[item].sendTime !== "") {
                        imgStr = " <a class=\"hint--top hint--rounded hint--info\" data-hint=\"关闭远程控制\" target=\"_blank\">" +"<img id ='" +
                            imgid +
                            "'src=\"images/operate-success.png\" class=\"alarm-img\" alt=\"\" onclick=\"alarmApi.sendMsg('" +
                            tid +
                            "','" +
                            data[item].componentNumber +"','"+data[item].alarmText+"','"+data[item].sendFlag+"','"+data[item].alarmType+
                            "')\">"+"</a><span id='" +
                            spanid +
                            "' class='alarm-span' style='font-size: 10px;display: inline-block;'>"+data[item].sendTime+"分钟前"+"</span> ";
                    } else {
                        imgStr = " <a class=\"hint--top hint--rounded hint--info\" data-hint=\"关闭远程控制\" target=\"_blank\">" +"<img id ='" +
                            imgid +
                            "'src=\"images/operate-success.png\" class=\"alarm-img\" alt=\"\" onclick=\"alarmApi.sendMsg('" +
                            tid +
                            "','" +
                            data[item].componentNumber +"','"+data[item].alarmText+"','"+data[item].sendFlag+"','"+data[item].alarmType+
                            "')\">"+"</a><span id='" +
                            spanid +
                            "' class='alarm-span' style='font-size: 10px;'></span> ";
                    }

                } else if ("2" === data[item].sendFlag) {
                    imgStr="<img src=\"images/operate-dis.png\" class=\"alarm-img\">" +"<span class='alarm-span' style='font-size: 10px;'>"+
                            "</span>"
                }

            }
            infoList +="<ul>\n" +
                "            <li class=\"alerm-ul one\">" +
                data[item].alarmText +
                "</li>\n" +
                "            <li class=\"alerm-ul two\">" +
                alarmType+
                "</li>\n" +
                "            <li class=\"alerm-ul three\">" +
                data[item].alarmTime +
                "</li>\n" +
                "            <li class=\"alerm-ul four\">" +
                alarmLevel +
                "</li>\n" +
                "            <li class=\"alerm-ul six\">" +
                data[item].componentNumber +
                "</li>\n" +
                "            <li class=\"alerm-ul seven\">" +
                imgStr+
                "</li>\n" +
                "        </ul>";
        }
        document.getElementById('alarm-ul').innerHTML = infoList;
        if (closeFlag) {
            $(".alarm-span").css('display','inline-block');
        }
    }


});