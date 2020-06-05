package com.longi.dap.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @CalssName WeatherDataBean
 * @Author sunke5
 * @Date 2020-5-22 13:42
 */
@Data
public class WeatherNeedBean extends RealTimeBean {
    /**
     * 未来3天预报
     **/
    private List<ForecastBean> forecastList = new ArrayList<>();

}
