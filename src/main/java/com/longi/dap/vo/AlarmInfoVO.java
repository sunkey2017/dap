package com.longi.dap.vo;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName DateTypeVO
 * @Author sunke5
 * @Date 2020-5-6 16:03
 */
@Data
public class AlarmInfoVO {
    /**
     * 告警id
     **/
    private String alarmId;
    /**
     * 组件编码
     **/
    private String componentId;

    /**
     * 告警类型
     **/
    private String alarmType;

    /**
     * 告警信息
     **/
    private String alarmTxt;

}
