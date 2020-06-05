package com.longi.dap.service.impl;

import com.longi.dap.dao.PowerStationBaseMapper;
import com.longi.dap.dao.PowerStationEMapper;
import com.longi.dap.entity.PowerStationBaseBean;
import com.longi.dap.entity.PowerStationInfoBean;
import com.longi.dap.service.IPowerStationBaseService;
import com.longi.dap.tookits.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.summingDouble;

/**
 * @author jiangsida
 */
@Service
public class PowerStationBaseServiceImpl implements IPowerStationBaseService {

    private static final Logger log = Logger.getLogger (PowerStationBaseServiceImpl.class);

    @Autowired
    DateTimeServiceHelper dateServiceHelper;
    
   @Autowired
   PowerStationBaseMapper powerBaseMapper;

    @Autowired
    PowerStationEMapper powerEMapper;


   @Override
    public List<PowerStationBaseBean> getPowerStationBaseInfo(String searchWord){
        return powerBaseMapper.getPowerStationBaseInfo(searchWord);
    }

    @Override
    public Map<String,Object> getPowerStationEInfo(String stationId){
       Map<String,Object> powerStationMap = new HashMap<>();
       try{

           //当前时间
           String currDate = DateUtil.getDateBefore(0);
           //根据站点编码获取电站数据
           List<PowerStationInfoBean> powerStationInfoList = powerEMapper.getPowerStationEInfo(stationId,currDate);

           //重新设置一些属性
           dateServiceHelper.resetAttr(powerStationInfoList,"power");

           //获取当天发电量数据
           Map<String,Double> currElectricity = powerStationInfoList.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getDataDate,summingDouble(PowerStationInfoBean::getGeneratingPower)));
           powerStationMap.put("currDateElec",currElectricity.get(currDate));

           //实时功率（最近10分钟）
           String currTime = DateUtil.getTimeByMinute(0);
           String beforeTime = DateUtil.getTimeByMinute(-600);
           Map<String,Double> realPower = powerStationInfoList.stream().filter(sx -> DateUtil.dateCompare(sx.getDataTime(),beforeTime,"HH:mm") >= 0
                   && DateUtil.dateCompare(sx.getDataTime(),currTime,"HH:mm") == -1)
                   .collect(Collectors.groupingBy(PowerStationInfoBean::getDataDate,averagingDouble(PowerStationInfoBean::getGeneratingPower)));
           powerStationMap.put("realPower",realPower.get(currDate));

           //累计发电量
           Map<String,Double>  accPower = powerStationInfoList.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getStationId,summingDouble(PowerStationInfoBean::getGeneratingPower)));
           powerStationMap.put("accPower",accPower.get(stationId));

           //按照阵列分组
           Map<String,List<PowerStationInfoBean>> powerInfo = powerStationInfoList.stream().filter(sx -> currDate.equals(sx.getDataDate())).collect(Collectors.groupingBy(PowerStationInfoBean::getArrayCode));

           dateServiceHelper.assembleDataForArray(powerInfo,powerStationMap);
       }catch(Exception e){
           e.printStackTrace();
           log.info("method getPowerStationEInfo() error: " + e);
       }
       return powerStationMap;
    }

}
