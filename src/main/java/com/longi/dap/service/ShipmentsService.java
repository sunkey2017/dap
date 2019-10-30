package com.longi.dap.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longi.dap.entity.Shipments;
public interface ShipmentsService{

    int insert(Shipments shipments);

    int insertSelective(Shipments shipments);

    int insertList(List<Shipments> shipmentss);

    int updateByPrimaryKeySelective(Shipments shipments);

    String selectAllCompany();

    String selectEurope(String company);

    String selectSinglecrystal(String singlecrystal);

    String selectOrderMap();

    Map<String,String> getAllCompanyOut();

    /**
     * wip问题
     * @return
     */
    String selectWipInfo();


    /**
     * 月产出
     * @return
     */
    String selectSummaryOut();

    /**
     * 质量问题TOP
     * @return
     */
    String weeklyngInfo();

    /**
     * 设备报警BI
     * @return
     */
    String alarmInfo();
    /**
     * 学历占比
     */
    String selectFormalSchooling();

    /**
     * 司龄分布
     * @return
     */

    String selectEmpDetail();

    /**
     * 柱状图人员花费情况
     * @return
     */

    String tripBudget();

    /**
     * 成品率
     * @return
     */
    String selectfinishproduct();

    /**
     * 累计金额
     */

    String  selectAggregateAmount();

    /**
     * 物料单价
     * @return
     */
     String selectBomPrice();

    /**\
     * 市场数据
     */

    String selectMarketData();

    String selectIndustryInformationPrice();

    /**
     * 每小时工时产出
     * @return
     */
    String selectWorkingOutput();
}
