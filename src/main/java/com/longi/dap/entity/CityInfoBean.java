package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName CityInfoBean
 * @Author sunke5
 * @Date 2020-5-22 13:47
 */
@Data
public class CityInfoBean {
    /**
     * 地市名称
     **/
    private String city;
    /**
     * 地市编码
     **/
    private String citykey;
    /**
     * 上次地市
     **/
    private String parent;
    /**
     * 更新时间
     **/
    private String updateTime;
}

