package com.longi.dap.controller;


import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.WeatherInfoBean;
import com.longi.dap.entity.WeatherNeedBean;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.IAlarmService;
import com.longi.dap.tookits.CommonUtil;
import com.longi.dap.tookits.HttpClientUtil;
import com.longi.dap.tookits.WebResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author sunke5
 * @Description  WeatherInfoController
 * @Date 11:52 2020-5-20
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/weather")
public class WeatherInfoController {

    private static final Logger log =  LoggerFactory.getLogger(WeatherInfoController.class);

    @Value("${weather.path}")
    private String url;

    @Autowired
    IAlarmService alarmService;

    @RequestMapping(value="/getWeatherInfo")
    public WebResult getWeatherInfo(@RequestParam String cityCode){
        WebResult result = null;
        WeatherNeedBean resultInfo = new WeatherNeedBean();
        try
        {
           String weatherInfoStr = HttpClientUtil.doGet(url+cityCode);
            log.info("访问URL : "+ url+cityCode);
           WeatherInfoBean weatherInfo = JSON.parseObject(weatherInfoStr, WeatherInfoBean.class);

           resultInfo.setGanmao(weatherInfo.getData().getGanmao());
           resultInfo.setPm10(weatherInfo.getData().getPm10());
           resultInfo.setPm25(weatherInfo.getData().getPm25());
           resultInfo.setQuality(weatherInfo.getData().getQuality());
           resultInfo.setShidu(weatherInfo.getData().getShidu());
           resultInfo.setWendu(weatherInfo.getData().getWendu());

           for(int i=0; i<4;i++){
               resultInfo.getForecastList().add( weatherInfo.getData().getForecast().get(i));
           }
           result = WebResultUtil.getResult(JSON.toJSONString(resultInfo));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            log.error("getWeatherInfo error: "+e);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }


}
