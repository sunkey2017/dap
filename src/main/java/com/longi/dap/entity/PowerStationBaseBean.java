package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName PowerStationBaseBean
 * @Author sunke5
 * @Date 2020-4-29 10:09
 */
@Data
public class PowerStationBaseBean {
    /**
     * 电站编码
     **/
    private String stationId;
    /**
     * 电站名称
     **/
    private String stationName;

    /**
     * 地市编码
     **/
    private Integer cityCode;

    /**
     * 投资单位
     **/
    private String investor;
    /**
     * 负责人
     **/
    private String resbPerson;
    /**
     * 电站图片地址1
     **/
    private String stationImg1;
    /**
     * 电站图片地址2
     **/
    private String stationImg2;
    /**
     * 地址
     **/
    private String stationAddress;
    /**
     * 电站经度
     **/
    private String longitude;
    /**
     * 电站纬度
     **/
    private String latitude;
    /**
     * 装机容量
     **/
    private String installedCapacity;
    /**
     * 安装时间
     **/
    private String installationTime;
    /**
     * 历史峰值功率
     **/
    private String histPeakPower;
    /**
     * 环境温度
     **/
    private String envTemperature;
    /**
     * 备注
     **/
    private String remarks;


}
