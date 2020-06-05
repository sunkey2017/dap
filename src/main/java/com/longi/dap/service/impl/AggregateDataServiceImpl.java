package com.longi.dap.service.impl;

import com.longi.dap.dao.PowerStationEMapper;
import com.longi.dap.entity.PowerStationInfoBean;
import com.longi.dap.service.IAggregateDataService;
import com.longi.dap.tookits.DateUtil;
import com.longi.dap.vo.DateTypeVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 聚合数据服务层
 * @Date 16:24 2020-5-6
 * @Author sunke5
 * @Param
 * @return
 **/
@Service
public class AggregateDataServiceImpl implements IAggregateDataService {

    private static final Logger log = Logger.getLogger (AggregateDataServiceImpl.class);


    @Autowired
    PowerStationEMapper powerEMapper;

    @Autowired
    DateTimeServiceHelper dateServiceHelper;

    @Override
    public Map<String,Object> AggregateDataDaily(DateTypeVO dateInfo){
        Map<String,Object> resultMap = new HashMap<>();

        //补充日期数据，防止不传日期，默认取 当天数据
        if(null == dateInfo.getBeginDate()){
            log.info("日期参数为空。");
            dateInfo.setBeginDate(DateUtil.getDateBefore(0));
        }
        if(null != dateInfo.getBeginDate() && null == dateInfo.getEndDate()){
            dateInfo.setEndDate(dateInfo.getBeginDate());
        }

        //获取某个电站日期内的数据
        List<PowerStationInfoBean> dateDataList = powerEMapper.getDataDaily(dateInfo);

        //重新设置一些属性
        dateServiceHelper.resetAttr(dateDataList,"day");

        //获取时间段内发电总和
        dateServiceHelper.setAllPower(dateDataList,resultMap);

        //组装数据
        dateServiceHelper.assembleDataForDate(dateDataList,resultMap,"day");

        //组装表字段数据
        List<Map<String,String>>  columnsList = powerEMapper.getTableColumns("com_power_station_e_info");
        dateServiceHelper.assembleColumns(columnsList,resultMap);

     return resultMap;
    }

    @Override
    public Map<String, Object> AggregateDataWeek(DateTypeVO dateInfo) {
        Map<String,Object> resultMap = new HashMap<>();

        //补充日期数据，防止不传日期，默认取 当天数据
        if(null == dateInfo.getBeginDate()){
            log.info("日期参数为空。");
            dateInfo.setBeginDate(DateUtil.getDateBefore(7));
        }
        dateInfo.setEndDate(DateUtil.getDateAfterDays(dateInfo.getBeginDate(),7));

        //获取某个电站日期内的数据
        List<PowerStationInfoBean> dateDataList = powerEMapper.getDataWeekMonth(dateInfo);

        //重新设置一些属性
        dateServiceHelper.resetAttr(dateDataList,"week");

        //获取时间段内发电总和
        dateServiceHelper.setAllPower(dateDataList,resultMap);

        //组装数据
        dateServiceHelper.assembleDataForDate(dateDataList,resultMap,"week");

        return resultMap;
    }

    @Override
    public Map<String, Object> AggregateDataMonth(DateTypeVO dateInfo) {
        Map<String,Object> resultMap = new HashMap<>();

        //补充日期数据，防止不传日期，默认取 当天数据
        if(null == dateInfo.getBeginDate()){
            log.info("日期参数为空。");
            dateInfo.setBeginDate(DateUtil.getCurrMonth()+"-01");
        }else{
            dateInfo.setBeginDate(dateInfo.getBeginDate()+"-01");
        }

        dateInfo.setEndDate(DateUtil.getDateAfterDays(DateUtil.getDateAfterMonth(dateInfo.getBeginDate(),1)+"-01",-1));

        //获取某个电站日期内的数据
        List<PowerStationInfoBean> dateDataList = powerEMapper.getDataWeekMonth(dateInfo);

        //重新设置一些属性
        dateServiceHelper.resetAttr(dateDataList,"mon");

        //获取时间段内发电总和
        dateServiceHelper.setAllPower(dateDataList,resultMap);

        //组装数据
        dateServiceHelper.assembleDataForDate(dateDataList,resultMap,"mon");

        return resultMap;
    }

    @Override
    public Map<String, Object> AggregateDataYear(DateTypeVO dateInfo) {
        Map<String,Object> resultMap = new HashMap<>();

        //补充日期数据，防止不传日期，默认取 当天数据
        if(null == dateInfo.getBeginDate()){
            log.info("日期参数为空。");
            dateInfo.setBeginDate(DateUtil.getCurrYear()+"-01-01");
        }else{
            dateInfo.setBeginDate(dateInfo.getBeginDate()+"-01-01");
        }

        dateInfo.setEndDate(DateUtil.getCurrYear()+"-12-31");

        //获取某个电站日期内的数据
        List<PowerStationInfoBean> dateDataList = powerEMapper.getDataYear(dateInfo);

        //重新设置一些属性
        dateServiceHelper.resetAttr(dateDataList,"year");

        //获取时间段内发电总和
        dateServiceHelper.setAllPower(dateDataList,resultMap);

        //组装数据
        dateServiceHelper.assembleDataForDate(dateDataList,resultMap,"year");

        return resultMap;
    }


}
