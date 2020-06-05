package com.longi.dap.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.longi.dap.entity.*;
import com.longi.dap.service.IXNLCService;
import com.longi.dap.tookits.CommonUtil;
import com.longi.dap.tookits.HttpClientUtil;
import com.longi.dap.tookits.UnicodeUtil;
import com.longi.dap.vo.XNJCAPIParamVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 西南集成接口API数据服务层
 * @Date 16:24 2020-5-12
 * @Author sunke5
 * @Param
 * @return
 **/
@Service
public class XNJCServiceImpl implements IXNLCService {

    private static final Logger log = Logger.getLogger (XNJCServiceImpl.class);

    @Value("${xnjc.uname}")
    private String userName;

    @Value("${xnjc.upwd}")
    private String passWord;

    @Value("${xnjc.boxlist}")
    private String getBoxUrl;

    @Value("${xnjc.grouplist}")
    private String getGroupListUrl;

    @Value("${xnjc.elementlist}")
    private String getElementListUrl;

    @Value("${xnjc.elementlast}")
    private String getElementLastUrl;

    @Value("${xnjc.elementalarmlist}")
    private String getElementAlarmListUrl;

    @Value("${xnjc.elementhistorylist}")
    private String getElementHistoryListUrl;

    @Override
   public XNJCResultBaseBean<CombinerBoxBean> getBoxList(){

        XNJCResultBaseBean<CombinerBoxBean> resultBean = new XNJCResultBaseBean<CombinerBoxBean>();
        try
        {
            Map<String,String> paramMap = initParamMap("","","","","","");
            //加密，解密，解码
            resultBean = JSON.parseObject(paramDecry(getBoxUrl,paramEncry(paramMap)), new XNJCResultBaseBean<CombinerBoxBean>().getClass());

        } catch (Exception e) {
            log.info("XNJC API getBoxList Error:"+e);
            CommonUtil.getExceptionDetail(e);
        }
        return resultBean;
    }

    @Override
    public XNJCResultBaseBean<GroupStringBean> getGroupList(String bid){

        XNJCResultBaseBean<GroupStringBean> resultBean = new XNJCResultBaseBean<GroupStringBean>();
        try
        {
            Map<String,String> paramMap = initParamMap("bid",bid,"","","","");
            //加密，解密，解码
            resultBean = JSON.parseObject(paramDecry(getGroupListUrl,paramEncry(paramMap)), new XNJCResultBaseBean<GroupStringBean>().getClass());

        } catch (Exception e) {
            log.info("XNJC API getGroupList Error:"+e);
            CommonUtil.getExceptionDetail(e);
        }
        return resultBean;
    }

    @Override
    public XNJCResultBaseBean<ElementStringBean> getElementList(String gid){

        XNJCResultBaseBean<ElementStringBean> resultBean = null;
        try
        {
            Map<String,String> paramMap = initParamMap("gid",gid,"","","","");
            //加密，解密，解码
            resultBean = JSON.parseObject(paramDecry(getElementListUrl,paramEncry(paramMap)), new XNJCResultBaseBean<ElementStringBean>().getClass());

        } catch (Exception e) {
            log.info("XNJC API getElementList Error:"+e);
            CommonUtil.getExceptionDetail(e);
        }
        return resultBean;
    }

    @Override
    public XNJCResultBaseBean<CodeInfoBean> getElementLast(String code){

        XNJCResultBaseBean<CodeInfoBean> resultBean = null;
        try
        {
            Map<String,String> paramMap = initParamMap("code",code,"","","","");

            //加密，解密，解码
            resultBean = JSON.parseObject(paramDecry(getElementLastUrl,paramEncry(paramMap)), new XNJCResultBaseBean<CodeInfoBean>().getClass());

        } catch (Exception e) {
            log.info("XNJC API getElementList Error:"+e);
            CommonUtil.getExceptionDetail(e);
        }
        return resultBean;
    }

    @Override
    public XNJCResultBaseBean<ElementAlarmBean> getElementAlarmList(XNJCAPIParamVO paramVO){

        XNJCResultBaseBean<ElementAlarmBean> resultBean = null;
        try
        {
            Map<String,String> paramMap = initParamMap("code",paramVO.getCode(),"counts",paramVO.getCounts(),"","");

            //加密，解密，解码
            resultBean = JSON.parseObject(paramDecry(getElementAlarmListUrl,paramEncry(paramMap)), new XNJCResultBaseBean<ElementAlarmBean>().getClass());

        } catch (Exception e) {
            log.info("XNJC API getElementAlarmList Error:"+e);
            CommonUtil.getExceptionDetail(e);
        }
        return resultBean;
    }

    @Override
    public XNJCResultBaseBean<CodeInfoBean> getElementHistoryList(XNJCAPIParamVO paramVO){

        XNJCResultBaseBean<CodeInfoBean> resultBean = null;
        try
        {
            Map<String,String> paramMap = initParamMap("code",paramVO.getCode(),"stime",paramVO.getStime(),"etime",paramVO.getEtime());

            //加密，解密，解码
            resultBean = JSON.parseObject(paramDecry(getElementHistoryListUrl,paramEncry(paramMap)), new XNJCResultBaseBean<CodeInfoBean>().getClass());

        } catch (Exception e) {
            log.info("XNJC API getElementAlarmList Error:"+e);
            CommonUtil.getExceptionDetail(e);
        }
        return resultBean;
    }


    private Map<String,String> initParamMap(String key1,String value1,String key2,String value2,String key3,String value3){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("uname",userName);
        paramMap.put("upwd",passWord);
        if(!"".equals(key1)){
            paramMap.put(key1,value1);
        }
        if(!"".equals(key2)){
            paramMap.put(key2,value2);
        }
        if(!"".equals(key3)){
            paramMap.put(key3,value3);
        }
        return paramMap;
    }

    private Map<String,String> paramEncry(Map<String,String> paramMap){
        Map<String,String> resultMap = new HashMap<>();
        //加密
        String jsonp = new Gson().toJson(paramMap);
        String baseP = Base64.getUrlEncoder().encodeToString(jsonp.getBytes());

        resultMap.put("param",baseP);
        return resultMap;
    }

    private String paramDecry(String url,Map<String,String> paramMap2) throws UnsupportedEncodingException {
        //请求拿到返回值
        String result = HttpClientUtil.doGet(url,paramMap2);

        //解密
        String decoderStr = new String(Base64.getDecoder().decode(result), "UTF-8");

        //解码
        String unicodeStr = UnicodeUtil.unicodeToString(decoderStr);

        return unicodeStr;
    }

}
