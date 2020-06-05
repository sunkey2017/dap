package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName WeatherDataBean
 * @Author sunke5
 * @Date 2020-5-22 13:42
 */
@Data
public class RealTimeBean {
    /**
     * 湿度
     **/
    private String shidu;
    /**
     * PM2.5
     **/
    private String pm25;
    /**
     * PM10
     **/
    private String pm10;
    /**
     * 空气质量
     **/
    private String quality;
    /**
     * 温度
     **/
    private String wendu;
    /**
     * 感冒指数
     **/
    private String ganmao;

}
