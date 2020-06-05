define(['echarts', 'common', 'manage/js/requestData'], function (echarts, common,RequestData) {

    /**
     * 地图表格
     * @param optionData 数据
     * @param divId domid
     */
    var initMapChart = function (optionData, divId) {
        let data = JSON.parse(optionData);
        data.forEach((item)=>{
            if (item.longitude !== '' && item.latitude !== '') {
                item.lonlat=new Array(item.longitude,item.latitude);
            }
        })
        let mapChart = echarts.init(document.getElementById(divId), null, {});


        var convertData = function (data) {
            var res = [];
            for (let i = 0; i < data.length; i++) {
                // var geoCoord = geoCoordMap[data[i].stationName];
                let geoCoord =data[i].lonlat;
                if (geoCoord) {
                    res.push({
                        name: data[i].stationName,
                        value: geoCoord,
                        stationId: data[i].stationId,
                        envTemperature: data[i].envTemperature,
                        installedCapacity: data[i].installedCapacity,
                        cityCode: data[i].cityCode
                    });
                }
            }
            return res;
        };

        let currentData = convertData(data);
        let option = {
            tooltip: {
                trigger: 'item',
                backgroundColor: "#eeeeee", //设置背景图片 rgba格式
                position: 'bottom',
                showDelay: 200,
                formatter(params) {
                    // 自定义工具栏，（数据提示框）
                    let text = '';
                    text = `<div style="color:rgba(0,0,0,0.74)"> 
                                 <span style="font-size: 18px; font-weight: 600;">${params.data.name}</span><br>
                                 <div style="height: 100px; margin-top: 10px;">
                                 <div style="float:left;display: inline-block;">
                                 <span style="margin:3px 0px;">容量 ：${params.data.installedCapacity}kWp</span><br>
                                 <span style="margin:3px 0px;">环境温度 ：${params.data.envTemperature}℃</span><br>
                                 <span style="margin:3px 0px;">日照 ：968W/㎡</span><br>
                                 <span style="margin:3px 0px;">功率 ：0kW</span><br>
                                 <span style="margin:3px 0px;">系统效率：0%</span>
                                 </div>
                                 <div style="float:right;display: flex;margin-left:5px; align-items: center;"><img src="../images/station.jpg" style="width:100px;height:100px;"></div>
                                 </div>
                          </div>`
                    return text;
                }
            },
            bmap: {
                // center: [110.396435, 23.585408],
                center: [116.404269, 39.912057],
                // zoom: 7,
                roam: true,
            },
            series: [

                {
                    type: 'custom',      //自定义
                    coordinateSystem: 'bmap',
                    renderItem: function (params, api) {     //具体实现自定义图标的方法
                        return {
                            type: 'image',
                            style: {
                                // image: '../images/gfdz.png',
                                image: '../images/timg.png',
                                x: api.coord([
                                    currentData[params.dataIndex].value[0], currentData[params.dataIndex].value[1]
                                ])[0],
                                y: api.coord([
                                    currentData[params.dataIndex].value[0], currentData[params.dataIndex].value[1]
                                ])[1],
                                width: 20,    // 图标大小设置
                                height: 20,
                            },
                            styleEmphasis: {}
                        }
                    },
                    animation: true,
                    animationDelay: function (idx) {       // 图片动画延迟
                        return idx * 20;
                    },
                    data: convertData(data)
                },
            ]
        };
        mapChart.setOption(option);
        mapChart.on('click', function (params) {
            toPageWithParmas('detail', "stationId=" + params.data.stationId+"&cityCode="+params.data.cityCode);
        });

        let bmap = mapChart.getModel().getComponent('bmap').getBMap()
        bmap.setMapStyle({style: 'normal'})

    };


    /**
     * 初始化页面
     * @param data 数据
     */
    var initTableAndInfo=function(data,type){
        let arr = JSON.parse(data);

        // 表格数据
        let list = "  <li class=\"tableTitle\" style='background-color: #E7EBF4; color:#5C6B7A'>\n" +
            "                    <span style=\"width:10%; margin-right:10px;\">电站编号</span>\n" +
            "                    <span style=\"width:10%; margin-right:10px;\">电站名称</span>\n" +
            "                    <span style=\"width:15%; margin-right:10px;\">投资单位</span>\n" +
            "                    <span style=\"width:6%; margin-right:10px;\">负责人</span>\n" +
            "                    <span style=\"width:10% ;margin-right:10px;\">地址</span>\n" +
            "                    <span style=\"width:10%; margin-right:10px;\">装机容量</span>\n" +
            "                    <span style=\"width:10%; margin-right:10px;\">安装时间</span>\n" +
            "                    <span style=\"width:10%;\">历史峰值功率</span>\n" +
            "                    <span style=\"width:10%;\">环境温度</span>\n" +
            "                </li>";

        // 信息
        let infoList = "";
        for (let i = 0; i < arr.length; i++) {
            if (type ==="table"  || type==="all") {
                list += " <li class=\"tableTitle\" style=\"cursor: pointer;  \"\n" +
                    "                          onclick=\"toPageWithParmas('detail', '" +"stationId="+
                    arr[i].stationId +"&cityCode="+arr[i].cityCode+
                    "')\">\n" +
                    "                    <span style='width: 10%; margin-right: 10px; font-size: 14px;'>" +
                    arr[i].stationId +
                    "</span>" +
                    "                    <span style=\"width:10%; margin-right:10px;font-size: 14px;\">" +
                    arr[i].stationName+
                    "</span>\n" +
                    "</span>" +
                    "                    <span style=\"width:15%; margin-right:10px;font-size: 14px;\">" +
                    arr[i].investor+
                    "</span>\n" +
                    "</span>" +
                    "                    <span style=\"width:6%; margin-right:10px;font-size: 14px;\">" +
                    arr[i].resbPerson+
                    "</span>\n" +
                    "                    <span style=\"width:10% ;margin-right:10px;font-size: 14px;\">" +
                    arr[i].stationAddress +
                    "</span>\n" +
                    "                    <span style=\"width:10%; margin-right:10px;font-size: 14px;\">" +
                    arr[i].installedCapacity +
                    "</span>\n" +
                    "                    <span style=\"width:10%; margin-right:10px;font-size: 14px;\">" +
                    arr[i].installationTime +
                    "</span>\n" +
                    "                    <span style=\"width:10%;font-size: 14px;\">" +
                    arr[i].histPeakPower +
                    "</span>\n" +
                    "                    <span style=\"width:10%;font-size: 14px;\">" +
                    arr[i].envTemperature +
                    "</span>\n" +
                    "                </li>";
            }


            if (type ==="info"  || type==="all") {
                infoList +="<div class=\"stationList\">\n" +
                    "                <p class=\"station-p\">" +
                    arr[i].stationName+
                    "电站监控</p>\n" +
                    "                <div>\n" +
                    "                    <div class=\"pjCon\">\n" +
                    "                        <div class=\"pjsleftL\">\n" +
                    "                            <span onclick=\"toPageWithParmas('detail', '" +"stationId="+
                    arr[i].stationId +"&cityCode="+arr[i].cityCode+
                    "'" +
                    ")\">\n" +
                    "                                <img style=\"width:100%; height:100%; cursor: pointer;\" src=\"../images/station.jpg\" class='info-img'>\n" +
                    "                            </span>\n" +
                    "                        </div>\n" +
                    "                        <dl class=\"pjsleftB\">\n" +
                    "                            <dd><span>电站编号 :  </span> <span>" +
                    arr[i].stationId +
                    "</span></dd>\n" +
                    "                            <dd><span>投资单位 :  </span> <span>" +
                    arr[i].investor +
                    "</span></dd>\n" +
                    "                            <dd><span>负责人 : </span> <span>" +
                    arr[i].resbPerson+
                    "</span></dd>\n" +
                    "                        </dl>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <ul class=\"clearfix\">\n" +
                    "                    <li><span>容量 ：</span><em>" +
                    arr[i].installedCapacity +
                    "</em><b>kWp</b></li>\n" +
                    "                    <li><span>组件温度：</span><em>" +
                    arr[i].envTemperature +
                    "</em><b>℃</b></li>\n" +
                    "                    <li><span>日照 ：</span><em>239.6</em><b>W/㎡</b></li>\n" +
                    "                    <li><span>功率 ：</span><em>0.113</em><b>kW</b></li>\n" +
                    "                    <li><span>系统效率 ：</span><em>38.513</em><b>%</b></li>\n" +
                    "                </ul>\n" +
                    "            </div>"
                ;
            }


        }
        if (type ==="table"  || type==="all") {
            document.getElementById('table-ul').innerHTML = list;
        }
        if (type ==="info"  || type==="all") {
            document.getElementById('info').innerHTML = infoList;
        }
    }

    $(function () {
        // 获取当前浏览器高度
        var windowH = $(window).height();
       // 给地图区域设置高度
        var mapH = windowH*0.78;
        $('.div_any_child_map').css("height", mapH);

        $('#table_condition').bind('keydown',function(event){
            if(event.keyCode == "13")
            {
               var  param = $('#table_condition').val();
                queryByParam(param,'table');
            }
        });

        $('#info_condition').bind('keydown',function(event){
            if(event.keyCode == "13")
            {
                var  param = $('#info_condition').val();
                queryByParam(param,'info');
            }
        });


        // $(".main-head-user").mouseout(
        //     function () {
        //         $("#head-menu").css('display','none');
        //     });
    })

    /**
     * 根据参数查询
     * @param param 查询条件
     * @param type 区分是表格还是图表
     */
    function queryByParam(param,type) {
        //地图
        var allPowerStationData = RequestData.allPowerStationData(param);
        allPowerStationData.then(function(resultData){
            initTableAndInfo(resultData,type);
        });
    }

    return {
        initMapChart: initMapChart,
        initTableAndInfo: initTableAndInfo,
    };


});