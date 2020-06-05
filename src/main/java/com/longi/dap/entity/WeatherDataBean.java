package com.longi.dap.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @CalssName WeatherDataBean
 * @Author sunke5
 * @Date 2020-5-22 13:42
 */
@Data
public class WeatherDataBean extends RealTimeBean {


    /**
     * 15天预报
     **/
    private List<ForecastBean> forecast;

    /**
     * 昨天天气
     **/
    private ForecastBean yesterday;
}
