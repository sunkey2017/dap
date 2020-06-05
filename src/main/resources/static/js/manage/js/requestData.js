define(function (){

    var getMainData = function (){
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/init/getMainData",
            data:{typeName:"组件"},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };
    var getOrderData = function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/saleOrder",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    var getSummaryout= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/summary",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //在制品情况
    var getWipData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/wipInfo?time="+new Date().getTime(),
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //在职人员学历分布
    var getFromalSchool= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/fromalShchooling",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //在职人员司龄分布
    var getCompanyAgeData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/empDetail",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //质量
    var getQualityData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/weeklyInfo",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };
    //警报
    var getAlarmData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/alarmInfo",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };
    //成品率
    var getYieldData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/finishProduct",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //累计入库金额
    var getAmountData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/aggregateAmount",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //BOM单价
    var getBomPriceData= function(){
            var def = $.Deferred();
            $.ajax({
                type:"GET",
                url:"/product/bomPrice",
                data:{},
                contentType:"application/json;charset=utf-8",
                success:function(result){
                    if("00" == result.errorCode){
                        def.resolve(result.data);
                    }
                }
            });
            return def.promise();
        };

    //单晶市场占比
    var initSingleCrystalData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/marketData",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //工时产出
    var initWorkHourOutData= function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/workingOutput",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //行业价格信息
    var initIndustryInfoData = function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/industryInformationPrice",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    //人员花费
    var initSpendData = function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/tripBudget",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    // 等径生长晶棒情况
    var getCrystalRodData = function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/tripBudget",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    //def.resolve(result.data);
                    def.resolve("{}");
                }
            }
        });
        return def.promise();
    };

    // 等径生长晶棒情况
    var getBrokenLineData = function(){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/product/workingOutput",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    //def.resolve(result.data);
                    def.resolve("{}");
                }
            }
        });
        return def.promise();
    };



    //首页汇总查询
    var allPowerStationData= function(param){
        var url = "/solar/allPowerStation?searchWord="+param;
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:url,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }
            }
        });
        return def.promise();
    };

    /**
     * 获取电站实时发电信息
     * @returns {any}
     */
    var getPowerStationById = function (stationId){
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/solar/getPowerStationById?stationId="+stationId,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    var getChartData = function (date, type,stationId){
        var obj = {
                   "stationId": stationId,
                    "dateType": type,
                    "beginDate": date
                 }
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/solar/aggregateDataByDate",
            data:JSON.stringify(obj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 获取电站报警信息
     * @param stationId 电站编码
     * @returns {any}
     */
    var getAlarmInfo = function (stationId){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/solar/getAlarmInfo?stationId="+stationId,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 获取电站报警信息
     * @param stationId 电站编码
     * @returns {any}
     */
    var getAlarmInfos = function (stationId){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/solar/getAlarmInfos?stationId="+stationId,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 发送消息
     * @param index
     * @param number
     * @param text
     * @returns {any}
     */
    var sendMessage = function (index, number, text, alarmType){
        var obj = {
            "alarmId": index,
            "componentId": number,
            "alarmTxt": text,
            "alarmType": alarmType
        }
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/mqtt/sendMessage",
            data:JSON.stringify(obj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result);
            }
        });
        return def.promise();

    };

    /**
     * 用户登录接口
     * @param userName 用户名
     * @param password 密码
     * @returns {any}
     */
    var doLogin = function (userName, password) {
        var obj = {
            "userName": userName,
            "password": password
        }
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/login/validateUser",
            data:JSON.stringify(obj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }

    /**
     * 获取天气信息
     * @param cityCode 城市编码
     * @returns {any}
     */
    var getWeatherInfo = function (cityCode){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/weather/getWeatherInfo?cityCode="+cityCode,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 获取用户信息
     * @param userName 用户名
     * @returns {any}
     */
    var getUserInfo = function (userName){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/login/getUserInfoByName?userName="+userName,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 修改密码
     * @param userName 用户名
     * @param newPwd 新密码
     * @param oldPwd 旧密码
     * @returns {any}
     */
    var changePwd = function (userName, newPwd, oldPwd) {
        var obj = {
            "userName": userName,
            "newPwd": newPwd,
            "oldPwd": oldPwd,
        }
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/login/changePwd",
            data:JSON.stringify(obj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }

    /**
     * 新增用户
     * @param userObj
     * @returns {any}
     */
    var addUser = function (userObj) {
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/login/addUser",
            data:JSON.stringify(userObj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }

    /**
     * 获取系统角色信息
     * @returns {any}
     */
    var getRoles = function (){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/login/getRoles",
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 获取用户列表
     * @returns {any}
     */
    var getUsers = function (searchWord){
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/login/getUsers?searchWord="+searchWord,
            data:{},
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if("00" == result.errorCode){
                    def.resolve(result.data);
                }

            }
        });
        return def.promise();

    };

    /**
     * 删除用户
     * @param id
     * @returns {any}
     */
    var deleteUser = function (id) {
        let userObj = {
            id :id
        }
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/login/deleteUser",
            data:JSON.stringify(userObj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }

    /**
     * 查询用户是否存在
     * @param userName 用户名
     * @returns {any}
     */
    var selectUserByName = function (userName) {
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/login/selectUserByName?userName="+userName,
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }


    /**
     * 更新用户信息
     * @param userObj
     * @returns {any}
     */
    var updateUser = function (userObj) {
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/login/updateUser",
            data:JSON.stringify(userObj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }

    /**
     * 删除角色
     * @param id
     * @returns {any}
     */
    var deleteRole = function (id) {
        let roleObj = {
            id :id
        }
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/role/deleteRole",
            data:JSON.stringify(roleObj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }

    /**
     * 新增角色
     * @param roleObj
     * @returns {any}
     */
    var addRole = function (roleObj) {
        var def = $.Deferred();
        $.ajax({
            type:"POST",
            url:"/role/addRole",
            data:JSON.stringify(roleObj),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }
    /**
     * 获取角色列表
     * @returns {any}
     */
    var getRoleList = function () {
        var def = $.Deferred();
        $.ajax({
            type:"GET",
            url:"/role/getRoleList",
            data:JSON.stringify(),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                def.resolve(result.data);
            }
        });
        return def.promise();
    }




    return {
        getMainData: getMainData,
        getOrderData:getOrderData,
        getSummaryout:getSummaryout,
        getWipData:getWipData,
        getFromalSchool:getFromalSchool,
        getCompanyAgeData:getCompanyAgeData,
        getQualityData:getQualityData,
        getAlarmData:getAlarmData,
        getYieldData:getYieldData,
        getAmountData:getAmountData,
        getBomPriceData:getBomPriceData,
        initSingleCrystalData:initSingleCrystalData,
        initWorkHourOutData:initWorkHourOutData,
        initIndustryInfoData:initIndustryInfoData,
        initSpendData:initSpendData,
        getCrystalData:getCrystalRodData,
        getBrokenLineData:getBrokenLineData,
        allPowerStationData:allPowerStationData,
        getPowerStationById:getPowerStationById,
        getChartData:getChartData,
        getAlarmInfo:getAlarmInfo,
        sendMessage:sendMessage,
        doLogin: doLogin,
        getAlarmInfos: getAlarmInfos,
        getWeatherInfo: getWeatherInfo,
        getUserInfo: getUserInfo,
        changePwd: changePwd,
        addUser: addUser,
        getRoles: getRoles,
        getUsers: getUsers,
        deleteUser: deleteUser,
        selectUserByName: selectUserByName,
        updateUser: updateUser,
        addRole:addRole,
        deleteRole: deleteRole,
        getRoleList: getRoleList
    };

});