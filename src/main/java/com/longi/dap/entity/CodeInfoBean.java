package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName CodeInfoBean 组件
 * @Author sunke5
 * @Date 2020-5-12 10:09
 */
@Data
public class CodeInfoBean {
    /**
     * 组件id
     **/
    private String eid;
    /**
     * 组件名称
     **/
    private String name;
    /**
     * 电压
     **/
    private String vol;
    /**
     * 温度
     **/
    private String tem;
    /**
     * 时间
     **/
    private String time;
    /**
     * 状态
     **/
    private String state;
    /**
     * 组件唯一编号
     **/
    private String code;


}
