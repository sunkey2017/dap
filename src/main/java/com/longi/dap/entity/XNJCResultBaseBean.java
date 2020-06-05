package com.longi.dap.entity;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @CalssName XNJCResultBaseBean
 * @Author sunke5
 * @Date 2020-4-29 10:09
 */
@Data
public class XNJCResultBaseBean<T> {
    /**
     * 返回编码
     **/
    private String err;
    /**
     * 返回信息
     **/
    private String info;

    /**
     * 返回数据
     **/
    private List<T> data;

}
