package com.longi.dap.controller;

import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.IPQCService;
import com.longi.dap.tookits.WebResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @version 1.0
 * @CalssName IPQController
 * @Author sunke5
 * @Date 2020-1-13 8:58
 */
@RestController
@RequestMapping("/ipqc")
@Slf4j
public class IPQController {

    @Autowired(required = false)
    private IPQCService ipqcService;

    @GetMapping("queryAllIPQCInfo")
    public WebResult getAllCompanySaleProporition(){
        ipqcService.queryAllIpqcInfo("");
        Map<String, String> allCompanyOut = null;//shipmentsService.getAllCompanyOut();
        String allCompanySale = JSON.toJSONString(allCompanyOut);
        WebResult result = WebResultUtil.getResult(allCompanySale);
        String logStr = "name<%s> ,age<%d> , sex<%s>,result<%s>";
        log.info(String.format(logStr,"孙珂",32,"男","完毕"));
        return  result;
    }

}
