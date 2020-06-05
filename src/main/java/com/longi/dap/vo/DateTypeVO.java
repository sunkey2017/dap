package com.longi.dap.vo;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName DateTypeVO
 * @Author sunke5
 * @Date 2020-5-6 16:03
 */
@Data
public class DateTypeVO {
    /**
     * 电站编码
     **/
    private String stationId;
    /**
     * 时间类型
     **/
    private String dateType;

    /**
     * 查询字段字符串
     **/
    private String fieldStr;

    /**
     * 开始时间
     **/
    private String beginDate;

    /**
     * 结束
     **/
    private String endDate;

}
