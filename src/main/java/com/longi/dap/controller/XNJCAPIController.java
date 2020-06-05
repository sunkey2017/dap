package com.longi.dap.controller;


import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.*;
import com.longi.dap.service.IXNLCService;
import com.longi.dap.tookits.DateUtil;
import com.longi.dap.tookits.WebResultUtil;
import com.longi.dap.vo.XNJCAPIParamVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author sunke5
 * @Description  XNJCAPIController
 * @Date 11:52 2020-5-10
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/xnjc")
public class XNJCAPIController {

    private static final Logger log =  LoggerFactory.getLogger(XNJCAPIController.class);
    @Autowired
    IXNLCService xnjcService;

    /**
     * @Description 获取汇流箱信息
     * @Date 13:30 2020-5-12
     * @Param [alarmInfoVO]
     * @return com.longi.dap.entity.WebResult
     **/
    @RequestMapping(value="/getBoxList",method= RequestMethod.GET)
    public WebResult sendMessage(){
        XNJCResultBaseBean<CombinerBoxBean> combinerBean = xnjcService.getBoxList();
        String allPowerStationBaseJson = JSON.toJSONString(combinerBean);
        WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
        log.info("allPowerStationBaseJson: "+allPowerStationBaseJson);
        return  result;
    }

    @RequestMapping(value="/getGroupList",method= RequestMethod.GET)
    public WebResult getGroupList(@RequestParam("bid") String bid){
        XNJCResultBaseBean<GroupStringBean> resultBean = xnjcService.getGroupList(bid);
        String allPowerStationBaseJson = JSON.toJSONString(resultBean);
        WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
        return  result;
    }

    @RequestMapping(value="/getElementList",method= RequestMethod.GET)
    public WebResult getElementList(@RequestParam("gid") String gid){
        XNJCResultBaseBean<ElementStringBean> resultBean = xnjcService.getElementList(gid);
        String allPowerStationBaseJson = JSON.toJSONString(resultBean);
        WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
        return  result;
    }

    @RequestMapping(value="/getElementLast",method= RequestMethod.GET)
    public WebResult getElementLast(@RequestParam("code") String code){
        XNJCResultBaseBean<CodeInfoBean> resultBean = xnjcService.getElementLast(code);
        String allPowerStationBaseJson = JSON.toJSONString(resultBean);
        WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
        return  result;
    }

    @RequestMapping(value="/getElementAlarmList",method= RequestMethod.POST)
    public WebResult getElementAlarmList(@RequestBody XNJCAPIParamVO paramVO){
        XNJCResultBaseBean<ElementAlarmBean> resultBean = xnjcService.getElementAlarmList(paramVO);
        String allPowerStationBaseJson = JSON.toJSONString(resultBean);
        WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
        return  result;
    }

    @RequestMapping(value="/getElementHistoryList",method= RequestMethod.POST)
    public WebResult getElementHistoryList(@RequestBody XNJCAPIParamVO paramVO){
        paramVO.setStime(DateUtil.date2TimeStamp(paramVO.getStime(),"yyyy-MM-dd HH:mm:ss"));
        paramVO.setEtime(DateUtil.date2TimeStamp(paramVO.getEtime(),"yyyy-MM-dd HH:mm:ss"));
        XNJCResultBaseBean<CodeInfoBean> resultBean = xnjcService.getElementHistoryList(paramVO);
        String allPowerStationBaseJson = JSON.toJSONString(resultBean);
        WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
        return  result;
    }

}
