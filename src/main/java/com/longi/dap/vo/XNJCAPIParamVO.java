package com.longi.dap.vo;

import lombok.Data;

/**
 * @version 1.0
 * @CalssName XNJCAPIParamVO
 * @Author sunke5
 * @Date 2020-5-12 15:03
 */
@Data
public class XNJCAPIParamVO {
    /**
     * 组件唯一编号
     **/
    private String code;

    /**
     * 条数
     **/
    private String counts;

    /**
     * 开始时间
     **/
    private String stime;

    /**
     * 结束时间
     **/
    private String etime;
}
