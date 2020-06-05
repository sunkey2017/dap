package com.longi.dap.service;

import com.longi.dap.entity.PowerStationBaseBean;

import java.util.List;
import java.util.Map;

/**
 * 获取电站基本信息数据服务接口
 * @Author sunke5
 * @Description
 * @Date 11:44 2020-4-30
 * @Param
 * @return
 **/
public interface IPowerStationBaseService {

    /**
     * @Description 获取电站基本信息
     * @Date 11:44 2020-4-30
     * @Param  searchWord
     * @return java.util.List<com.longi.dap.entity.PowerStationBaseBean>
     **/
    List<PowerStationBaseBean> getPowerStationBaseInfo(String searchWord,String userName);

    /**
     * @Description 根据电站编码获取电站信息
     * @Date 11:45 2020-4-30
     * @Param [stationId]
     * @return java.util.List<com.longi.dap.entity.PowerStationInfoBean>
     **/
    Map<String,Object> getPowerStationEInfo(String stationId);

    /**
     * 获取城市信息
     * @return
     */
    List<Map<String, String>> getCityInfo();

    /**
     * 新增电站信息
     * @param powerStationBaseBean
     * @return
     */
    Map<String, String> addBaseStation (PowerStationBaseBean powerStationBaseBean);
}
