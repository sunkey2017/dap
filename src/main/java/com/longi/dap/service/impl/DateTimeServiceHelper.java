package com.longi.dap.service.impl;

import com.longi.dap.entity.PowerStationInfoBean;
import com.longi.dap.tookits.SetList;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;

/**
 *  帮助类
 * @version 1.0
 * @CalssName DateTimeServiceHelper
 * @Author sunke5
 * @Date 2020-1-13 10:36
 */
@Service
public class DateTimeServiceHelper {

    public void resetAttr(List<PowerStationInfoBean> dateDataList,String dateType){
        DecimalFormat df = new DecimalFormat("#.##");
        //把每条数据功率转化为发电量（KWh）
        dateDataList.forEach(xs -> {
            if(null != xs.getGeneratingPower() && !"power".equals(dateType)){
                xs.setGeneratingPower(Double.parseDouble(df.format(xs.getGeneratingPower()/60)));
            }
            if("day".equals(dateType)){
                if(null != xs.getDataTime()){
                    xs.setDateItem(xs.getDateItem()+":"+ groupMinByQuarter(xs.getDataTime().split(":")[1]));
                }
            } else if("week".equals(dateType)){
                xs.setDateItem(xs.getDataDate().split("-")[2]+"."+xs.getDataTime().split(":")[0]);
            }else if("mon".equals(dateType)){
                xs.setDateItem(xs.getDataDate().split("-")[2]);
            }else if("year".equals(dateType)){
                xs.setDateItem(xs.getDataDate().split("-")[1]);
            }
        });
    }

    /**
     * @Description
     * @Date 11:05 2020-5-7
     * @Param [dateDataList, resultMap]
     * @return void
     **/
    public void setAllPower(List<PowerStationInfoBean> dateDataList,Map<String,Object> resultMap){
        //获取时间段内发电总和
        Map<String,Double> allPower = dateDataList.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getStationId,summingDouble(PowerStationInfoBean::getGeneratingPower)));
        resultMap.put("allPower",allPower);
    }

    public void assembleDataForArray(Map<String,List<PowerStationInfoBean>> dataMap,Map<String,Object> resultMap){
        //阵列信息
        List<String> arrayCodeList = new ArrayList<>();
        Map<String,List<Double>> mainDataMap = new HashMap<>();
        List<String> xValues = new ArrayList<>();

        dataMap.forEach((k,v) ->{
            arrayCodeList.add(k);
            List<Double> mainData = new ArrayList<>();
            v.forEach(xv -> {
                mainData.add(xv.getGeneratingPower());
            });
            mainDataMap.put(k,mainData);
        });

        //拼X轴数据
        if(!arrayCodeList.isEmpty()){
            dataMap.get(arrayCodeList.get(0)).forEach(sx -> {
                xValues.add(sx.getDataTime());
            });
        }
        resultMap.put("xValues",xValues);
        resultMap.put("arrayCodeList",arrayCodeList);
        resultMap.put("mainData",mainDataMap);
    }

    public void assembleDataForDate( List<PowerStationInfoBean> dateDataList,Map<String,Object> resultMap,String dateType){

        //按照阵列分组
        Map<String,List<PowerStationInfoBean>> dataMap = dateDataList.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getArrayCode));

        //阵列信息
        List<String> arrayCodeList = new ArrayList<>();
        Map<String,List<Double>> mainDataMap = new HashMap<>();
        SetList<String> xValues = new SetList<>();
         if("year".equals(dateType)){
             assembleForYear(mainDataMap,dataMap,arrayCodeList,xValues);
         }else{
             assembleForDaily(mainDataMap,dataMap,arrayCodeList,xValues);
         }

        resultMap.put("xValues",xValues);
        resultMap.put("arrayCodeList",arrayCodeList);
        resultMap.put("mainData",mainDataMap);
    }

    /**
     * @Description  组装表字段
     * @Date 11:42 2020-5-20
     * @Param [columnsList, resultMap]
     * @return void
     **/
    public void  assembleColumns(List<Map<String,String>> columnsList,Map<String,Object> resultMap){
        Map<String,String> columnMap = new HashMap<>();
        columnsList.forEach(sx ->{
            columnMap.put(sx.get("column_name"),sx.get("column_comment"));
        });
        resultMap.put("columns",columnMap);
    }

    private void assembleForDaily( Map<String,List<Double>> mainDataMap,Map<String,List<PowerStationInfoBean>> dataMap, List<String> arrayCodeList ,SetList<String> xValues){
        dataMap.forEach((k,v) ->{
            arrayCodeList.add(k);
            List<Double> mainData = new ArrayList<>();
            LinkedHashMap<String,Double> polyHourMap = v.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getDateItem,LinkedHashMap::new,summingDouble(PowerStationInfoBean::getGeneratingPower)));
            polyHourMap.forEach((key,val)->{
                mainData.add(val);
                xValues.add(key);
            });
            mainDataMap.put(k,mainData);
        });
    }

    private void assembleForYear( Map<String,List<Double>> mainDataMap,Map<String,List<PowerStationInfoBean>> dataMap, List<String> arrayCodeList ,SetList<String> xValues){
        List<String> allMonths = assembleMonth();
        for (Map.Entry<String, List<PowerStationInfoBean>> entry : dataMap.entrySet()) {
            String k = entry.getKey();
            List<PowerStationInfoBean> v = entry.getValue();
            List<Double> mainData = new ArrayList<>();
            arrayCodeList.add(k);
            LinkedHashMap<String, Double> polyHourMap = v.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getDateItem, LinkedHashMap::new, summingDouble(PowerStationInfoBean::getGeneratingPower)));
            allMonths.forEach(x -> {
                xValues.add(x);
                mainData.add(polyHourMap.get(x) == null ? 0.0 : polyHourMap.get(x));
            });
            mainDataMap.put(k, mainData);
        }
    }

    private List<String> assembleMonth(){
        List<String> allMonths = new ArrayList<>();
        allMonths.add("01");
        allMonths.add("02");
        allMonths.add("03");
        allMonths.add("04");
        allMonths.add("05");
        allMonths.add("06");
        allMonths.add("07");
        allMonths.add("08");
        allMonths.add("09");
        allMonths.add("10");
        allMonths.add("11");
        allMonths.add("12");
        return allMonths;
    }

    public String groupMinByQuarter(String min){
        String returnStr = "";
        switch (Integer.parseInt(min)/15) {
            case 0:
                returnStr = "00";
                break;
            case 1:
                returnStr = "15";
                break;
            case 2:
                returnStr = "30";
                break;
            case 3:
                returnStr = "45";
                break;
            default:
                break;
        }
        return returnStr;
    }


}
