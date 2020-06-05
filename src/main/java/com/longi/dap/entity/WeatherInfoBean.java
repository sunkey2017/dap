package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName WeatherInfoBean
 * @Author sunke5
 * @Date 2020-5-22 13:49
 */
@Data
public class WeatherInfoBean {
    /**
     * 返回状态
     **/
    private String status;
    /**
     * 返回信息
     **/
    private String message;
    /**
     * 日期
     **/
    private String date;
    /**
     * 详细时间
     **/
    private String time;

    /**
     * 地市信息
     **/
    private CityInfoBean cityInfo;

    /**
     * 天气信息数据
     **/
    private WeatherDataBean data;

}
