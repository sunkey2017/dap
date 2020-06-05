package com.longi.dap.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.longi.dap.entity.CombinerBoxBean;
import com.longi.dap.entity.XNJCResultBaseBean;
import com.longi.dap.tookits.HttpClientUtil;
import com.longi.dap.tookits.UnicodeUtil;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @CalssName XNJCInterfaceTest
 * @Author sunke5
 * @Date 2020-4-27 14:27
 */
public class XNJCInterfaceTest {



    public static void main(String[] args) {

        //String url = "http://116.62.41.124/pv/api/getboxlist";  //uname:平台用户名	upwd:平台密码
        String url = "http://116.62.41.124/pv/api/getgrouplist"; //uname:平台用户名	upwd:平台密码 bid:汇流箱id
        //String url = http://116.62.41.124/pv/api/getelementlist; //uname:平台用户名 upwd:平台密码 gid:组串id
        //String url = http://116.62.41.124/pv/api/getelementlast; //uname:平台用户名 upwd:平台密码 code:组件唯一编号
        //String url = http://116.62.41.124/pv/api/getelementalarmlist; // uname:平台用户名 upwd:平台密码 code	:组串唯一编号 counts:条数
        //String url = http://116.62.41.124/pv/api/getelementhistorylist; // uname:平台用户名 upwd:平台密码 code:组串唯一id stime:开始时间 etime:结束时间

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("uname","test01");
        paramMap.put("upwd","123456");
        paramMap.put("bid","9");

        try {

            //加密
            String jsonp = new Gson().toJson(paramMap);
            String baseP = Base64.getUrlEncoder().encodeToString(jsonp.getBytes());
            Map<String,String> paramMap2 = new HashMap<>();
            paramMap2.put("param",baseP);

            //请求拿到返回值
           // String result = HttpClientUtil.doPostJson(url,jsonp2);
            String result = HttpClientUtil.doGet(url,paramMap2);

            //解密
            String decoderStr = new String(Base64.getDecoder().decode(result), "UTF-8");

            //解码
            String unicodeStr = UnicodeUtil.unicodeToString(decoderStr);

            XNJCResultBaseBean<CombinerBoxBean> data = JSON.parseObject(unicodeStr, new XNJCResultBaseBean<CombinerBoxBean>().getClass());

            //JSONObject.toJavaObject(unicodeStr, new XNJCResultBaseBean<CombinerBoxBean>().getClass()));
            System.out.println(unicodeStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
