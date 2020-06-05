package com.longi.dap.tookits;

import com.longi.dap.entity.Shipments;

import java.util.List;

/**
 * @ClassName UtilJson
 * @Description
 * @Author jiangsida
 * @VERSION 1.0
 **/

public class UtilJson {



    public static String getJson(List<Shipments> list,String  str,String companyname){

        StringBuffer sb=new StringBuffer();

        sb.append(str);
        for (int i=0; i<list.size();i++){
            if (companyname.contains(list.get(i).getCompany())){
                sb.append(list.get(i).getMount()+",");
            }else {
                sb.append(0);
            }

        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return  sb.toString();

    }
}
