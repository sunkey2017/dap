package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName ElementAlarmBean
 * @Author sunke5
 * @Date 2020-5-12 10:09
 */
@Data
public class ElementAlarmBean {
    /**
     * 组件id
     **/
    private String eid;
    /**
     * 组件名称
     **/
    private String name;
    /**
     * 报警类型
     **/
    private String type;

    /**
     * 时间戳秒
     **/
    private String time;
    /**
     * 位置信息
     **/
    private String place;
    /**
     * 报警内容
     **/
    private String msg;

    /**
     * 组件唯一编号
     **/
    private String code;



}
