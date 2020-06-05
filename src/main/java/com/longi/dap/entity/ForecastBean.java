package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName ForecastBean
 * @Author sunke5
 * @Date 2020-4-29 10:09
 */
@Data
public class ForecastBean {
    /**
     * 日期
     **/
    private String date;
    /**
     * 最高温度
     **/
    private String high;
    /**
     * 最低温度
     **/
    private String low;
    /**
     * 日期时间
     **/
    private String ymd;
    /**
     * 星期几
     **/
    private String week;
    /**
     * 日出时间
     **/
    private String sunrise;

    /**
     * 日落时间
     **/
    private String sunset;
    /**
     * 空气质量
     **/
    private String aqi;

    /**
     * 风向
     **/
    private String fx;

    /**
     * 风力
     **/
    private String fl;

    /**
     * 天气类型
     **/
    private String type;

    /**
     * 注意
     **/
    private String notice;


}
