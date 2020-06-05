package com.longi.dap.entity;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName AlarmInfoBean
 * @Author sunke5
 * @Date 2020-4-29 10:09
 */
@Data
public class AlarmInfoBean {
    /**
     * 唯一标识
     **/
    private String alarmId;
    /**
     * 告警内容
     **/
    private String alarmText;
    /**
     * 告警类型
     **/
    private String alarmType;
    /**
     * 告警时间
     **/
    private String alarmTime;
    /**
     * 警告级别
     **/
    private String alarmLevel;
    /**
     * 警告来源
     **/
    private String alarmSourceId;

    /**
     * 组件编码
     **/
    private String componentNumber;
    /**
     * 发送标记
     **/
    private String sendFlag;

    /**
     * 发送时间
     **/
    private String sendTime="0";

    /**
     * 备注
     **/
    private String remarks;


}
