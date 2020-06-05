package com.longi.dap.service;

import com.longi.dap.vo.DateTypeVO;

import java.util.Map;

/**
 *
 * @Author sunke5
 * @Description
 * @Date 17:00 2020-5-6
 * @Param
 * @return
 **/
public interface IAggregateDataService {

    /**
     * @Description  按日聚合
     * @Date 17:01 2020-5-6
     * @Param [dateInfo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> AggregateDataDaily(DateTypeVO dateInfo);

    /**
     * @Description 按周聚合
     * @Date 17:01 2020-5-6
     * @Param [dateInfo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> AggregateDataWeek(DateTypeVO dateInfo);

    /**
     * @Description 按月聚合
     * @Date 17:01 2020-5-6
     * @Param [dateInfo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> AggregateDataMonth(DateTypeVO dateInfo);

    /**
     * @Description 按年聚合
     * @Date 17:02 2020-5-6
     * @Param [dateInfo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> AggregateDataYear(DateTypeVO dateInfo);
}
