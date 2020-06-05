package com.longi.dap.controller;


import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.AlarmInfoBean;
import com.longi.dap.entity.PowerStationBaseBean;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.IAggregateDataService;
import com.longi.dap.service.IAlarmService;
import com.longi.dap.service.IPowerStationBaseService;
import com.longi.dap.tookits.WebResultUtil;
import com.longi.dap.vo.DateTypeVO;
import com.netflix.client.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author sunke5
 * @Description  PowerStationBaseController
 * @Date 11:52 2020-4-29
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/solar")
public class PowerStationBaseController {

    private static final Logger log = LoggerFactory.getLogger(PowerStationBaseController.class);

    @Autowired
    IPowerStationBaseService BaseService;

    @Autowired
    IAggregateDataService aggregateDataService;

    @Autowired
    IAlarmService alarmService;

    /**
     * 获取电站基本信息
     * @return
     */
     @RequestMapping("/allPowerStation")
    public  WebResult getAllPowerStationBaseInfo(@RequestParam("searchWord") String searchWord,HttpServletRequest httpServletRequest){
         //登录用户名称
         String userName = httpServletRequest.getSession().getAttribute("username").toString();

         List<PowerStationBaseBean> allPowerStationBaseInfo = BaseService.getPowerStationBaseInfo(searchWord,userName);
         String allPowerStationBaseJson = JSON.toJSONString(allPowerStationBaseInfo);
         WebResult result = WebResultUtil.getResult(allPowerStationBaseJson);
         return  result;
    }

    /**
     *
     * @Description  根据电站信息获取电站的实时发电信息
     * @Date 11:39 2020-4-30
     * @Param [stationId]
     * @return com.longi.dap.entity.WebResult
     **/
    @RequestMapping(value="/getPowerStationById",method= RequestMethod.POST)
    public WebResult getPowerStationInfo(@RequestParam("stationId") String stationId,HttpServletRequest httpServletRequest){
       //登录用户名称
        String userName = httpServletRequest.getSession().getAttribute("username").toString();
        //装机容量
        List<PowerStationBaseBean> allPowerStationBaseInfo = BaseService.getPowerStationBaseInfo(stationId,userName);
        List<PowerStationBaseBean> currStationInfo = allPowerStationBaseInfo.stream().filter(sx -> stationId.equals(sx.getStationId())).collect(Collectors.toList());
        String installedCapacity = currStationInfo.get(0).getInstalledCapacity();
        //日发电量,实时功率,累计发电量
        Map<String,Object> resultMap = BaseService.getPowerStationEInfo(stationId);
        resultMap.put("installedCapacity",installedCapacity);

        resultMap.put("stationImg1",allPowerStationBaseInfo.get(0).getStationImg1());
        resultMap.put("stationImg2",allPowerStationBaseInfo.get(0).getStationImg2());
        resultMap.put("stationImg3",allPowerStationBaseInfo.get(0).getStationImg3());
        resultMap.put("stationImg4",allPowerStationBaseInfo.get(0).getStationImg4());

        WebResult result = WebResultUtil.getResult( JSON.toJSONString(resultMap));
        return result;
    }

    /**
     * @Description  根据时间和时间类型组装数据
     * @Date 16:09 2020-5-6
     * @Param [dateInfo]
     * @return com.longi.dap.entity.WebResult
     **/
    @RequestMapping(value="/aggregateDataByDate",method= RequestMethod.POST)
    public WebResult aggregateDataByDate(@RequestBody DateTypeVO dateInfo){
        Map<String,Object> resultMap = new HashMap<>();
        if(null != dateInfo){
            switch (dateInfo.getDateType()) {
                case "day":
                    resultMap = aggregateDataService.AggregateDataDaily(dateInfo);
                    break;
                case "week":
                    resultMap = aggregateDataService.AggregateDataWeek(dateInfo);
                    break;
                case "month":
                    resultMap = aggregateDataService.AggregateDataMonth(dateInfo);
                    break;
                case "year":
                    resultMap = aggregateDataService.AggregateDataYear(dateInfo);
                    break;
                default:
                    log.info("不正确的时间类型。");
                    break;
            }
        }
        WebResult result = WebResultUtil.getResult( JSON.toJSONString(resultMap));
        return result;

    }

    /**
     * @Description  查询前10条告警数据
     * @Date 16:09 2020-5-6
     * @Param [dateInfo]
     * @return com.longi.dap.entity.WebResult
     **/
    @RequestMapping(value="/getAlarmInfo",method= RequestMethod.GET)
    public WebResult getAlarmInfo(@RequestParam("stationId") String stationId){
        List<AlarmInfoBean> recentlyAlarmList = alarmService.getRecentlyAlarmInfo(stationId);
        WebResult result = WebResultUtil.getResult( JSON.toJSONString(recentlyAlarmList));
        return result;
    }

    /**
     * @Description  查询所有告警数据
     * @Date 16:09 2020-5-6
     * @Param [dateInfo]
     * @return com.longi.dap.entity.WebResult
     **/
    @RequestMapping(value="/getAlarmInfos",method= RequestMethod.GET)
    public WebResult getAlarmInfos(@RequestParam("stationId") String stationId){
        List<AlarmInfoBean> allAlarmList = alarmService.getAllAlarmInfo(stationId);
        log.info("all alarm count: "+ allAlarmList.size());
        WebResult result = WebResultUtil.getResult( JSON.toJSONString(allAlarmList));
        return result;
    }



    @RequestMapping(value="/getCityInfo",method= RequestMethod.GET)
    public WebResult getCityInfo(HttpRequest httpRequest){
        List<Map<String,String>> cityInfoList = BaseService.getCityInfo();
        WebResult result = WebResultUtil.getResult( JSON.toJSONString(cityInfoList));
        return result;
    }

    @RequestMapping(value = "/addBaseStation",method = RequestMethod.POST)
    public WebResult addBaseStation(@RequestBody PowerStationBaseBean powerStationBaseBean){
        Map<String, String> map = BaseService.addBaseStation(powerStationBaseBean);
        WebResult result = WebResultUtil.getResult( JSON.toJSONString(map));
        return result;
    }
}
