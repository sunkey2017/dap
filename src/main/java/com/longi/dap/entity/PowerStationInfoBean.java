package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName PowerStationBaseBean
 * @Author sunke5
 * @Date 2020-4-29 10:09
 */
@Data
public class PowerStationInfoBean {
    /**
     * 唯一标识
     **/
    private String id;
    /**
     * 电站编号
     **/
    private String stationId;
    /**
     * 日期
     **/
    private String dataDate;
    /**
     * 时间
     **/
    private String dataTime;
    /**
     * 时间分类
     **/
    private String dateItem;
    /**
     * 阵列编码
     **/
    private String arrayCode;
    /**
     * 电压
     **/
    private Double voltage;
    /**
     * 电流
     **/
    private Double electricCurrent;
    /**
     * 发电功率
     **/
    private Double generatingPower;
    /**
     * 温度
     **/
    private Double comTemperature;
    /**
     * 备注
     **/
    private String remarks;


}
