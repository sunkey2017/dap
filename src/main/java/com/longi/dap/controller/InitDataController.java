package com.longi.dap.controller;

import com.github.abel533.echarts.Option;
import com.longi.dap.entity.CapacityEntity;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.EChartService;
import com.longi.dap.tookits.WebResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/init")
public class InitDataController {


    @Autowired
    private EChartService echartService;


    @RequestMapping("/getMainData")
    public WebResult getMainData(@RequestParam(value = "typeName", required = false) String typeName){
        String optionData = echartService.getMarketShareData();
        WebResult result = WebResultUtil.getResult(optionData);
        return result;
    }
}
