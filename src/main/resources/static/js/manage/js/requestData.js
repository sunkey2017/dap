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
        initSpendData:initSpendData
    };

});