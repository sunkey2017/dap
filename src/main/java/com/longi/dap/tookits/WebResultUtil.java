package com.longi.dap.tookits;

import com.longi.dap.entity.WebResult;

/**
 * @ClassName WebResultUtil
 * @Description
 * @Author jiangsida
 * @VERSION 1.0
 **/

public class   WebResultUtil {


 public  static WebResult getResult(String optionData){
        WebResult webResult=new WebResult();
        if(null != optionData){
            webResult.setErrorCode("00");
            webResult.setErrorMsg("success");
            webResult.setData(optionData);
        }else{
            webResult.setErrorCode("01");
            webResult.setErrorMsg("get data error.");
        }
        return   webResult;
    }
}
