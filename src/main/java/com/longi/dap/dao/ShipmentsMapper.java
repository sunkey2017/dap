package com.longi.dap.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.longi.dap.entity.Shipments;

@Mapper
public interface ShipmentsMapper {
    int insert(@Param("shipments") Shipments shipments);

    int insertSelective(@Param("shipments") Shipments shipments);

    int insertList(@Param("shipmentss") List<Shipments> shipmentss);

    int updateByPrimaryKeySelective(@Param("shipments") Shipments shipments);

    List<Shipments> selectAllCompany();

    List<Shipments> selectEurope(String market,String year);

    List<Shipments> selectSinglecrystal(String year,String singlecrystal);

    List<Map<String,Object>> selectordermap();

    List<Map<String,Object>> selectwip();
    List<Map<String,Object>> selectsummaryout();

    List<Map<String,Object>> selectWeeklyNg();
    List<Map<String,Object>> alarmInfo();
    List<Map<String,Object>>  selectFormalSchooling();
    List<Map<String,Object>>  selectEmpDetail();

    List<Map<String,Object>> selectTripBudget();
    List<Map<String,Object>> selectfinishproduct();
    List<Map<String,Object>> selectAggregateAmount();
    List<Map<String,Object>> selectAggregatebyMonth();
    List<Map<String,Object>> selectBomPrice();
    List<Map<String,Object>> selectMarketData();
    List<Map<String,Object>> selectIndustryInformationPrice();
    List<Map<String,Object>> selectWorkingOutput();
}
