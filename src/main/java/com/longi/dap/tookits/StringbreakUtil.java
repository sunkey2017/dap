package com.longi.dap.tookits;

/**
 * @ClassName StringbreakUtil
 * @Description
 * @Author jiangsida
 * @VERSION 1.0
 **/

public class StringbreakUtil {

    public  static  StringBuffer breakString(StringBuffer sb){

        return  sb.delete(sb.length()-1,sb.length()).append("]");
    }
}
