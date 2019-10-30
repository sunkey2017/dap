package com.longi.dap.tookits;

import com.longi.dap.entity.WebResult;

/**
 *
 */
public class ChartUtil {

    public static String setAxisLine(String color){
        StringBuffer bufStr = new StringBuffer();
        bufStr.append("\"axisLine\":{    \"lineStyle\":{  \"color\": \""+color+"\" } },");
        return bufStr.toString();
    }
    public static String setAxisLabel(String interval,String rotate,String fontSize,String color){
        StringBuffer bufStr = new StringBuffer();
        bufStr.append("\"axisLabel\" : {  ");
        if(null != interval){
            bufStr.append("\"interval\":\""+interval+"\",");
        }
        if(null != rotate){
            bufStr.append(" \"rotate\":\""+rotate+"\", ");
        }
        if(null != rotate){
            bufStr.append(" \"textStyle\": { \"color\": \""+color+"\", \"fontSize\":\"13\" } ");
        }else{
            bufStr.append(" \"textStyle\": { \"color\": \""+color+"\"} ");
        }
        bufStr.append(" }");
        return bufStr.toString();
    }

}
