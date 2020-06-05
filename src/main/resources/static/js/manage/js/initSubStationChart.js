define(['echarts', 'common','manage/js/requestData'], function (echarts, common, RequestData) {

    // 全局定义列，用于excel导出过滤
    var excelCol={};
    /**
     * 日 图表
     * @param optionData 数据
     * @param divId domid
     */
    var initDataSetChart = function (optionData, divId) {
        // 总发电量设值
        let stationId = getUrlParam("stationId")
        let allPowerVal = "";
        if(optionData.allPower !==undefined && JSON.stringify(optionData.allPower) !== '{}') {
            allPowerVal = optionData.allPower[stationId];
        }
        setText("allPower", fomateNumber(allPowerVal));
        let dataSetChart = echarts.init(document.getElementById(divId), null, {});
        // 颜色数组
        let colorArray =['#00B050','#67BEFF','#3366FF','#63D5B2','#1e87f9', '#7EE89B','#10b7ef', '#FA9B8D','#f97672','#FAB36E','#F4E88D'];
        let legendData = optionData.arrayCodeList;
        let xAxisData = optionData.xValues;
        let mainData = optionData.mainData;
        let obj_arry=[];

        let j = 0;
        for(let i in mainData){
            let obj= {};
            obj = {
                name: i,
                // barWidth: '15%',
                type: "bar",
                data:  mainData[i]
            }
            j++;
            obj_arry.push(obj)
        }
        // 颜色数组
        if (j>colorArray.length){
            let num = j-colorArray.length;
            for (let k=0; k<num; k++){
                let color = colorRandom();
                colorArray.push(color);
            }
        }

        let option = {
            color: colorArray,
            title: {
                show: JSON.stringify(mainData) === '{}',
                text: '暂无数据',
                left: "center",
                top:"center",
                textStyle:{
                    fontSize:"30px",
                    color:"#3e3e3e"
                }
            },
            tooltip: {},
            legend: {
                data: legendData,
                top: 22,
                right: 24,
                itemGap: 15,
                itemWidth: 10,
                itemHeight: 10,
                textStyle: {
                    color: '#000000'
                }
            },
            // x軸的顏色
            xAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#000000'
                    }
                },
                // x軸的字體
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: "#323232",
                        fontSize: 12
                    },
                },
                data: xAxisData
            },
            yAxis: {
                // y軸的顏色
                axisLine: {
                    lineStyle: {
                        color: '#000000'
                    }
                },
                // y軸的分割線顏色
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: "rgba(0,0,0,0.4)",
                        width: 1,
                    }
                },
                name:'KWh'
            },
            series: obj_arry,
        };
        dataSetChart.setOption(option,true);
        dataSetChart.hideLoading();
    };


    /**
     * 日周折线图
     * @param optionData 渲染的数据
     * @param divId 渲染的dom
     * @param date 选择的日期
     * @param type 选择的类型
     */
    var initDataLineChart = function (optionData, divId, date,type) {
        if (date === null || date === "") {
            let now = new Date();
            date = dateFormat("YYYY-mm-dd", now);
        }
        // 总发电量设值
        let stationId = getUrlParam("stationId")
        let allPowerVal = "";
        if(optionData.allPower !==undefined && JSON.stringify(optionData.allPower) !== '{}') {
            allPowerVal = optionData.allPower[stationId];
        }
        setText("allPower", fomateNumber(allPowerVal));
        let dataSetChart = echarts.init(document.getElementById(divId), null, {});
        // 颜色数组
        let colorArray =['#00B050','#67BEFF','#3366FF','#63D5B2','#1e87f9', '#7EE89B','#10b7ef', '#FA9B8D','#f97672','#FAB36E','#F4E88D'];
        let legendData = optionData.arrayCodeList;
        let xAxisData = optionData.xValues;
        let mainData = optionData.mainData;
        let obj_arry=[];

        let xAxisFilter = [];
        let tooltip = {};
        if (type === "week") {
            xAxisData.forEach(item => {
                let itemstr = item.toString();
                let newVal = itemstr.substring(0,itemstr.indexOf('.'));
                let en_mon_arr = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Spt","Oct","Nov","Dec"];
                let dateArr = date.split("-");
                let monthStr = dateArr[1][1];
                let mun = monthStr-1;
                xAxisFilter.push(newVal+"."+en_mon_arr[mun]);
            })
           tooltip = {
               trigger: 'axis',
               axisPointer: {
                   animation: false
               },
               formatter(params) {
                   let template = "";
                   let i =0;
                   for (x in params) {
                       let dateArr = date.split("-");
                       let monthStr = dateArr[1];
                       let yearStr = dateArr[0];
                       let chooseDate = xAxisData[params[x].dataIndex];
                       let chooseArr = chooseDate.split(".");
                       let dayStr = chooseArr[0];
                       let hStr = chooseArr[1];
                       let head = "";
                       if (i==0){
                           head =  "<p>" + yearStr + "/" + monthStr + "/" + dayStr + " " + hStr + "</p>";
                       }
                       template += head
                           +"<p>" +params[x].marker + params[x].seriesName + ":  " + fomateNumber(params[x].data) + "</p>";
                       i++;
                   }
                   return template;
               }
           }
        } else {
            xAxisFilter = xAxisData;
            tooltip = {
                trigger: 'axis',
                axisPointer: {
                    animation: false
                },
            }
        }


        let j = 0;
        for(let i in mainData){
            let obj = {
                name: i,
                barWidth: '35%',
                type: "line",
                data:  mainData[i],
                // showSymbol: false,
                smooth:true,
                symbolSize:1,   //拐点圆的大小
                itemStyle:{
                    normal:{
                        lineStyle:{
                            width:1,
                            type:'solid'  //'dotted'虚线 'solid'实线
                        }
                    }
                },
            }
            j++;
            obj_arry.push(obj)
        }

        // 颜色数组
        if (j>colorArray.length){
            let num = j-colorArray.length;
            for (let k=0; k<num; k++){
                let color = colorRandom();
                colorArray.push(color);
            }
        }

        let option = {
            color: colorArray,
            title: {
                show: JSON.stringify(mainData) === '{}',
                text: '暂无数据',
                left: "center",
                top:"center",
                textStyle:{
                    fontSize:"30px",
                    color:"#3e3e3e"
                }
            },
            tooltip: tooltip,
            legend: {
                data: legendData,
                top: 22,
                right: 24,
                itemGap: 15,
                itemWidth: 10,
                itemHeight: 10,
                textStyle: {
                    color: '#000000'
                }
            },
            // x軸的顏色
            xAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#000000'
                    }
                },
                // x軸的字體
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: "#323232",
                        fontSize: 12
                    },
                },
                // data: xAxisData
                data: xAxisFilter
            },
            yAxis: {
                // y軸的顏色
                axisLine: {
                    lineStyle: {
                        color: '#000000'
                    }
                },
                // y軸的分割線顏色
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: "rgba(0,0,0,0.4)",
                        width: 1,
                    }
                },
                name:'KWh'
            },
            series: obj_arry,
        };
        dataSetChart.setOption(option,true);
        dataSetChart.hideLoading();
    };


    /**
     * 当天数据展示图表
     * @param optionData 数据
     * @param divId domid
     */
    var initAreaChart = function (optionData, divId) {
        let dataSetChart = echarts.init(document.getElementById(divId), null, {});


        let legendData = optionData.arrayCodeList;
        let xAxisData = optionData.xValues;
        let colorArray =['#00B050','#67BEFF','#3366FF','#63D5B2','#1e87f9', '#7EE89B','#10b7ef', '#FA9B8D','#f97672','#FAB36E','#F4E88D'];
        let mainData = optionData.mainData;
        let obj_arry=[];

        let j = 0;
        for(let i in mainData){
            let colorStr = "";
            if (j<=colorArray.length){
                colorStr = colorArray[j]
            } else {
                colorStr=colorRandom();
            }
            let obj={
                name: i,
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    color: colorStr
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: colorStr
                    }, {
                        offset: 1,
                        color: colorStr
                    }])
                },
                data: mainData[i]
            };
            j++;
            obj_arry.push(obj)
        }
        let option = {
            title: {
                show: JSON.stringify(mainData) === '{}',
                text: '暂无数据',
                left: "center",
                top:"center",
                textStyle:{
                    fontSize:"30px",
                    color:"#3e3e3e"
                }
            },
            legend: {
                data: legendData,
                icon: 'rect',
                top: 22,
                right: 24,
                itemGap: 15,
                itemWidth: 10,
                itemHeight: 10,
                textStyle: {
                    color: '#000000'
                }
            },
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: xAxisData,
                name:'min'
            },
            yAxis: {
                type: 'value',
                name:'KW'
            },
            series: obj_arry
        };

        dataSetChart.setOption(option);
    };

    /**
     * 页面初始化方法，根据需要渲染不同dom
     */
    $(function () {
        // 获取请求参数
        var stationId =  getUrlParam("stationId");
        // 设置10分钟刷新一次
        window.setInterval(function () {
            var data ={}
            //市场占有份额
            var initData = RequestData.getPowerStationById(stationId);
            initData.then(function(resultData){
                data = JSON.parse(resultData)
                renderCardAndDayChart(data);
            });

        },600000);

        /**
         * 初始化卡片数据和日统计
         */
        var initData = RequestData.getPowerStationById(stationId);
        initData.then(function(resultData){
            var  data = JSON.parse(resultData)
            renderCardAndDayChart(data);
        });
        // 初始化日期
        let date = new Date();
        let dateStr = dateFormat("YYYY-mm-dd", date);
        $('#daypicker').val(dateStr);
        //初始化当天的多源柱形统计图
         queryChartData(dateStr,'day', stationId);

        /**
         * 初始化右侧电站概况信息
         */
        var initData = RequestData.allPowerStationData(stationId);
        initData.then(function(resultData){
            let  data = JSON.parse(resultData)[0]
            // 电站名称
            setText("stationName",data.stationName !== undefined ? data.stationName : "");
            // 电站地址
            setText("stationAddress",data.stationAddress!== undefined ? data.stationAddress : "");
            // 安装时间
            setText("installationTime",data.installationTime !== undefined ? data.installationTime : "");
            // 历史峰值功率
            setText("histPeakPower",data.histPeakPower  !== undefined ? data.histPeakPower : "");
        });

        /**
         * 初始化报警信息
         */
        var initAlarmData = RequestData.getAlarmInfo(stationId);
        initAlarmData.then(function(resultData){
            let data= JSON.parse(resultData)
            let  temdata =[];
            let wdata = [];
            data.forEach(item =>{
                if (item.alarmType === "1"){
                    temdata.push(item)
                } else
                if (item.alarmType === "2"){
                    wdata.push(item);
                }
            })
            setText("wNum", wdata.length);
            setText("tNum", temdata.length);

            // 处理电压数据
            let  wtext = "";
            wdata.forEach((item,index) =>{
                let num = index+1;
                let color = "#ffffff";
                if (item.alarmLevel === "3") {
                    color = "#fd041d";
                } else
                if (item.alarmLevel === "2") {
                    color = "#f08708";
                } else
                if (item.alarmLevel === "1") {
                    color = "#616161";
                }
                wtext = wtext+"<span onclick=\"toPageWithParmas('alarm', '" +"stationId="+
                    stationId +
                    "')\" style='cursor: pointer;color: " +
                    color+
                    "'>"+num+"."+item.alarmText+"</span><br>";
            })
            document.getElementById('wMarquee').innerHTML = wtext;
            // 处理温度数据
            let  temtext = "";
            temdata.forEach((item,index) =>{
                var num = index+1;
                var color = "#ffffff";
                if (item.alarmLevel === "3") {
                    color = "#fd041d";
                } else
                if (item.alarmLevel === "2") {
                    color = "#f08708";
                } else
                if (item.alarmLevel === "1") {
                    color = "#616161";
                }
                temtext = temtext+"<span onclick=\"toPageWithParmas('alarm', '" +"stationId="+
                    stationId +
                    "')\" style='cursor: pointer;color: " +
                    color+
                    "'>"+num+"."+item.alarmText+"</span><br>";
            })
            document.getElementById('tMarquee').innerHTML = temtext;
        });

        function ScrollText(content) {
            this.Delay = 200;
            this.Amount = 1;
            this.Direction = "up";
            this.Timeout = 500;
            this.ScrollContent = this.gid(content);
            this.ScrollContent.innerHTML += this.ScrollContent.innerHTML;
            this.ScrollContent.onmouseover = this.GetFunction(this, "Stop");
            this.ScrollContent.onmouseout = this.GetFunction(this, "Start");
        }

        ScrollText.prototype.gid = function (element) {
            return document.getElementById(element);
        }
        ScrollText.prototype.Stop = function () {
            clearTimeout(this.AutoScrollTimer);
            clearTimeout(this.ScrollTimer);
        }
        ScrollText.prototype.Start = function () {
            clearTimeout(this.AutoScrollTimer);
            this.AutoScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"), this.Timeout);
        }

        ScrollText.prototype.AutoScroll = function () {
            if (this.Direction == "up") {
                if (parseInt(this.ScrollContent.scrollTop) >= parseInt(this.ScrollContent.scrollHeight) / 2) {
                    this.ScrollContent.scrollTop = 0;
                    clearTimeout(this.AutoScrollTimer);
                    this.AutoScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"), this.Timeout);
                    return;
                }
                this.ScrollContent.scrollTop += this.Amount;
            } else {
                if (parseInt(this.ScrollContent.scrollTop) <= 0) {
                    this.ScrollContent.scrollTop = parseInt(this.ScrollContent.scrollHeight) / 2;
                }
                this.ScrollContent.scrollTop -= this.Amount;
            }
            if (parseInt(this.ScrollContent.scrollTop) % this.LineHeight != 0) {
                this.ScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"), this.Delay);
            } else {
                this.AutoScrollTimer = setTimeout(this.GetFunction(this, "AutoScroll"), this.Timeout);
            }
        }

        ScrollText.prototype.GetFunction = function (variable, method) {
            return function () {
                variable[method]();
            }
        }

        var scrollup = new ScrollText("tMarquee");
        scrollup.Start();

        var wscrollup = new ScrollText("wMarquee");
        wscrollup.Start();

        /**
         * 日期组件渲染
         */
        lay('#version').html('-v' + laydate.v);

        //执行一个laydate实例
        //常规用法
        laydate.render({
            elem: '#daypicker',
            theme: '#5BC0DE',
            done: function (value, date) {
                queryChartData(value,'day', stationId);
            }
        });


        //年选择器
        laydate.render({
            elem: '#yearpicker'
            , type: 'year',
            theme: '#F2C789',
            done: function (value) {
                queryChartData(value,'year', stationId);
            }
        });
        //年月选择器
        laydate.render({
            elem: '#monthpicker'
            ,type: 'month',
            theme: '#92CE92',
            done: function (value) {
                queryChartData(value,'month', stationId);
            }
        });
        //周选择器
        laydate.render({
            elem: '#weekpicker',
            theme: '#E38D8A',
            done: function(value, date){
                queryChartData(value,'week', stationId);
            }
        })




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


    /**
     * 格式化日期
     * @param fmt 格式
     * @param date 需要格式化的日期
     * @returns {*} 按格式输出日期字符串
     */
    function dateFormat(fmt, date) {
        let ret;
        const opt = {
            "Y+": date.getFullYear().toString(),        // 年
            "m+": (date.getMonth() + 1).toString(),     // 月
            "d+": date.getDate().toString(),            // 日
            "H+": date.getHours().toString(),           // 时
            "M+": date.getMinutes().toString(),         // 分
            "S+": date.getSeconds().toString()          // 秒
        };
        for (let k in opt) {
            ret = new RegExp("(" + k + ")").exec(fmt);
            if (ret) {
                fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
            };
        };
        return fmt;
    }

    /**
     * 动态设置文字
     * @param elemId 标签Id
     * @param text 文字
     */
    function setText(elemId,text) {
        document.getElementById(elemId).innerText = text;
    }

    /**
     * 渲染卡片和日统计图表
     * @param data
     */
    function renderCardAndDayChart(data) {
        setText("installedCapacity", data.installedCapacity !== undefined ? fomateNumber(data.installedCapacity) : "0");
        setText("dailyGeneration",data.currDateElec !== undefined ? fomateNumber(data.currDateElec):"0");
        setText("realTimePower",data.realPower !== undefined ? fomateNumber(data.realPower) : "0");
        setText("summaryPower",data.accPower !==undefined ? fomateNumber(data.accPower) :"0");

        // 添加二氧化碳和植树的计算
        let all = data.accPower !==undefined ? data.accPower :0;
        let co2 = (all/1000)*0.8677*1000;
        //平均吸收二氧化碳为 约5023克/天
        let tree =co2*1000/5023;
        setText("co2", fomateNumber(co2));
        setText("tree", fomateNumber(tree));


        initAreaChart(data, 'areaChart');
    }

    /**
     * 柱状图统计数据查询
     * date 日期
     * type 类型
     * stationId 电站编码
     */
     function queryChartData(date,type,stationId) {
        var initData = RequestData.getChartData(date,type,stationId);
        var dataSetChart = echarts.init(document.getElementById('dataSetChart'), null, {});

        dataSetChart.showLoading({
            text: '拼命加载中。。。',
            color: '#4cbbff',
            textColor: '#4cbbff',
            maskColor: '#fafaff',
        });

        initData.then(function(resultData){
            var  data = JSON.parse(resultData);
            if (type === 'week' || type === 'day') {
                // 只有选日的时候会返回列信息,公共调用方法，只有在初始化天的时候初始化excel
                if (type === 'day' && JSON.stringify(excelCol) ==="{}") {
                    excelCol = data.columns;
                    initExcel(stationId);
                }
                initDataLineChart(data, 'dataSetChart', date, type)
            }
            if (type === 'month' || type==='year') {
                initDataSetChart(data, 'dataSetChart');
            }
        });
    }

    /**
     * 随机生成颜色
     * @returns {string}
     */
    function colorRandom() {
        return "#"+Math.random().toString(16).slice(-6)+"80";
    }


    /**
     * 初始化excel导出
     */
    function initExcel(stationId) {
        /**
         * 弹出框渲染，日期绑定 excel导出
         */
        let startDate="";
        let endDate="";

        let checkBoxStr = "";
        for (let item in excelCol){
            let valstr = item+"-"+excelCol[item];
            checkBoxStr+="<div class='checkbox'>"+"<input type='checkbox'  class=\"checkbox-col\" id = \"" +
                item +
                "\" name='checkbox-col' value=\"" +
                valstr +
                "\">"
                +"<label for='" +
                item +
                "' class=\"excel-span\">"+excelCol[item]+"</label></div>"
        }

        $('#excel').on('click', function(e) {
            let txt=  `<label class="excel-label">时间周期:</label>
                   <input type="text" class="excel-date-input" placeholder="开始日期" id="excel-day-start">
                   <input type="text" class="excel-date-input" placeholder="结束日期" id="excel-day-end">
                   <div style="margin: 5px 0px;"> <label class="excel-label">可选列:</label> <button class="excel-check-btn" id ="excel-all-check"><span style="font-size: 6px">全选</span></button></div> 
                   <div style="border:1px solid #eeeeee; padding: 5px;">
                      `+checkBoxStr +
                `</div>`;
            let option = {
                title: "excel导出",
                btn: parseInt("0011",2),
                onOk: function(){
                    var chk_value =[];
                    $('input[name="checkbox-col"]:checked').each(function(){
                        chk_value.push($(this).val());
                    });
                    var chkStr = chk_value.join(",");
                    exportExcel(startDate, endDate, chkStr,stationId);
                }
            }
            window.wxc.xcConfirm(txt, "custom", option);
            let date = new Date();
            // 日期组件
            var start = laydate.render({
                elem: '#excel-day-start',
                theme: '#5BC0DE',
                max:dateFormat("YYYY-mm-dd", date),
                btns: ['confirm','clear'],
                done: function (value, dates) {
                    startDate = value;
                    // 限定时间范围
                    end.config.min ={
                        year:dates.year,
                        month:dates.month-1,
                        date: dates.date,
                        hours: 0,
                        minutes: 0,
                        seconds : 0
                    };
                }
            });
            var end = laydate.render({
                elem: '#excel-day-end',
                theme: '#5BC0DE',
                btns: ['confirm','clear'],
                max:dateFormat("YYYY-mm-dd", date),
                done: function (value, date) {
                    endDate = value;
                }
            });
            //全选
            $("#excel-all-check").click(function(){
                $("[name='checkbox-col']").attr("checked",'true');
            })


        })
    }

    /**
     * excel 导出
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @param fieldStr 选择列
     * @param stationId 电站id
     */
    function exportExcel(beginDate, endDate, fieldStr,stationId) {

        let params = {
            beginDate: beginDate,
            endDate: endDate,
            fieldStr: fieldStr,
            stationId: stationId
        }

        let form = document.createElement("form");
        form.style.display = 'none';
        form.action = "/export/powerData";
        form.method = "post";
        form.enctype="multipart/form-data";
        form.acceptCharset="utf-8";
        document.body.appendChild(form);

        for(let key in params){
            let input = document.createElement("input");
            input.type = "hidden";
            input.name = key;
            input.value = params[key];
            form.appendChild(input);
        }
        form.submit();
        form.remove();

    }

    return {
        initDataSetChart: initDataSetChart,
        initAreaChart: initAreaChart,
        queryChartData: queryChartData,
        dateFormat:dateFormat
    };


});