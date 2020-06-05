package com.longi.dap.controller;


import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.ShipmentsService;
import com.longi.dap.tookits.WebResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName ShipmentsController
 * @Description
 * @Author jiangsida
 * @VERSION 1.0
 **/
@RestController
@RequestMapping("/product")
public class ShipmentsController {


    @Autowired
    ShipmentsService shipmentsService;

    /**
     * 柱状图根据josn数据展示多个维度
     * @return
     */
     @GetMapping("allCompanySale")
    public  WebResult getAllCompanySaleProporition(){
         Map<String, String> allCompanyOut = shipmentsService.getAllCompanyOut();
         String allCompanySale = JSON.toJSONString(allCompanyOut);
         WebResult result = WebResultUtil.getResult(allCompanySale);
         return  result;
    }

    /**
     * 销售订单 已经完成
     * @return
     */
    @GetMapping("/saleOrder")
    public WebResult getSaleOrder(){
        String optionData = shipmentsService.selectOrderMap();
        WebResult result = WebResultUtil.getResult(optionData);
        return result;


    }

    /**
     * 月成本产出 已经完成
     * @return
     */
    @GetMapping("/summary")
    public WebResult getSummary(){
        String optionData = shipmentsService.selectSummaryOut();
        WebResult result = WebResultUtil.getResult(optionData);
        return result;

    }

    /**
     * 工序wip 已完成
     * @return
     */
    @GetMapping("/wipInfo")
    public  WebResult getWipinfo(){
       String optionData= shipmentsService.selectWipInfo();
       WebResult result = WebResultUtil.getResult(optionData);
        return result;

    }

    /**
     * 设备报警气泡 json数据前台加载
     * @return
     */
            @GetMapping("/alarmInfo")
    public WebResult getAlarmInfo(){
        String optionData=shipmentsService.alarmInfo();
        WebResult result = WebResultUtil.getResult(optionData);
        return  result;
    }

    /**
     * 质量不良气泡 json数据前台加载
     * @return
     */
    @GetMapping("/weeklyInfo")
    public WebResult getWeeklyInfo(){
        String optionData=shipmentsService.weeklyngInfo();
        WebResult result = WebResultUtil.getResult(optionData);
        return  result;
    }
    /**
     * 学历占以完成
     */
    @GetMapping("/fromalShchooling")
    public  WebResult getFormalSchooling(){
        String optionData=shipmentsService.selectFormalSchooling();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;
    }

    /**
     * 司龄分布 待完成
     */
    @GetMapping("/empDetail")
    public  WebResult getEmpDetail(){
        String optionData=shipmentsService.selectEmpDetail();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;
    }

    /**
     * 成品率
     * @return
     */
    @GetMapping("/finishProduct")
    public  WebResult getFinishProduct(){

        String optionData = shipmentsService.selectfinishproduct();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;
    }

    /**
     * 物料累计金额
     */
    @GetMapping("/aggregateAmount")
    public  WebResult getAggregateAmount(){

        String optionData = shipmentsService.selectAggregateAmount();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;
    }
    /**
     * 月物料单价
     */
    @GetMapping("/bomPrice")
    public  WebResult getBomPrice(){
        String optionData = shipmentsService.selectBomPrice();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;
    }

    /**
     * 人员差旅花费情况
     */
     @GetMapping("/tripBudget")
    public  WebResult getTripBudget(){

        String optionData= shipmentsService.tripBudget();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;
    }

    @GetMapping("/marketData")
   public WebResult getMarketData(){
       String optionData = shipmentsService.selectMarketData();
       WebResult result=WebResultUtil.getResult(optionData);
       return  result;
   }
    @GetMapping("/industryInformationPrice")
   public  WebResult getIndustryInformationPrice(){
       String optionData = shipmentsService.selectIndustryInformationPrice();
       WebResult result=WebResultUtil.getResult(optionData);
       return  result;

   }

    @GetMapping("/workingOutput")
    public  WebResult getWorkingOutput(){
        String optionData = shipmentsService.selectWorkingOutput();
        WebResult result=WebResultUtil.getResult(optionData);
        return  result;

    }


}
