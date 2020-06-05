package com.longi.dap.service.impl;

import com.longi.dap.dao.PowerStationBaseMapper;
import com.longi.dap.dao.PowerStationEMapper;
import com.longi.dap.entity.PowerStationBaseBean;
import com.longi.dap.entity.PowerStationInfoBean;
import com.longi.dap.service.IPowerStationBaseService;
import com.longi.dap.tookits.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.summingDouble;

/**
 * @author jiangsida
 */
@Service
public class PowerStationBaseServiceImpl implements IPowerStationBaseService {

    private static final Logger log = Logger.getLogger (PowerStationBaseServiceImpl.class);

    @Autowired
    DateTimeServiceHelper dateServiceHelper;
    
   @Autowired
   PowerStationBaseMapper powerBaseMapper;

    @Autowired
    PowerStationEMapper powerEMapper;

    @Override
    public List<PowerStationBaseBean> getPowerStationBaseInfo(String searchWord,String userName){
        return powerBaseMapper.getPowerStationBaseInfo(searchWord,userName);
    }

    @Override
    public Map<String,Object> getPowerStationEInfo(String stationId){
       Map<String,Object> powerStationMap = new HashMap<>();
       try{

           //当前时间
           String currDate = DateUtil.getDateBefore(0);
           //根据站点编码获取电站数据
           List<PowerStationInfoBean> powerStationInfoList = powerEMapper.getPowerStationEInfo(stationId,currDate);

           //重新设置一些属性
           dateServiceHelper.resetAttr(powerStationInfoList,"power");

           //获取当天发电量数据
           Map<String,Double> currElectricity = powerStationInfoList.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getDataDate,summingDouble(PowerStationInfoBean::getGeneratingPower)));
           powerStationMap.put("currDateElec",currElectricity.get(currDate));

           //实时功率（最近10分钟）
           String currTime = DateUtil.getTimeByMinute(0);
           String beforeTime = DateUtil.getTimeByMinute(-600);
           Map<String,Double> realPower = powerStationInfoList.stream().filter(sx -> DateUtil.dateCompare(sx.getDataTime(),beforeTime,"HH:mm") >= 0
                   && DateUtil.dateCompare(sx.getDataTime(),currTime,"HH:mm") == -1)
                   .collect(Collectors.groupingBy(PowerStationInfoBean::getDataDate,averagingDouble(PowerStationInfoBean::getGeneratingPower)));
           powerStationMap.put("realPower",realPower.get(currDate));

           //累计发电量
           Map<String,Double>  accPower = powerStationInfoList.stream().collect(Collectors.groupingBy(PowerStationInfoBean::getStationId,summingDouble(PowerStationInfoBean::getGeneratingPower)));
           powerStationMap.put("accPower",accPower.get(stationId));

           //按照阵列分组
           Map<String,List<PowerStationInfoBean>> powerInfo = powerStationInfoList.stream().filter(sx -> currDate.equals(sx.getDataDate())).collect(Collectors.groupingBy(PowerStationInfoBean::getArrayCode));

           dateServiceHelper.assembleDataForArray(powerInfo,powerStationMap);
       }catch(Exception e){
           e.printStackTrace();
           log.info("method getPowerStationEInfo() error: " + e);
       }
       return powerStationMap;
    }

    @Override
    public List<Map<String, String>> getCityInfo() {
       List<Map<String, String>> cityList = new ArrayList<>();
        cityList = powerBaseMapper.getCityInfo();
        return cityList;
    }

    @Override
    public Map<String, String> addBaseStation(PowerStationBaseBean powerStationBaseBean) {
        Map<String, String> res = new HashMap<>();
        try{
            String stationId = generateStationCode(powerStationBaseBean.getCityShortCode());
            powerStationBaseBean.setStationId(stationId);
            if (StringUtils.isEmpty(powerStationBaseBean.getStationImg1())) {
                powerStationBaseBean.setStationImg1("");
            }
            if (StringUtils.isEmpty(powerStationBaseBean.getStationImg2())) {
                powerStationBaseBean.setStationImg2("");
            }
            if (StringUtils.isEmpty(powerStationBaseBean.getStationImg3())) {
                powerStationBaseBean.setStationImg3("");
            }
            if (StringUtils.isEmpty(powerStationBaseBean.getStationImg4())) {
                powerStationBaseBean.setStationImg4("");
            }

            powerBaseMapper.addStation(powerStationBaseBean);
            res.put("success","true");
            res.put("message","新增电站成功");
        } catch (Exception e) {
            res.put("success","false");
            res.put("message", e.getMessage());
            //新增失败的话删除已经上传的图片
            if (!StringUtils.isEmpty(powerStationBaseBean.getStationImg1())) {
                this.delete(powerStationBaseBean.getStationImg1());
            }
            if (!StringUtils.isEmpty(powerStationBaseBean.getStationImg2())) {
                this.delete(powerStationBaseBean.getStationImg2());
            }
            if (!StringUtils.isEmpty(powerStationBaseBean.getStationImg3())) {
                this.delete(powerStationBaseBean.getStationImg3());
            }
            if (!StringUtils.isEmpty(powerStationBaseBean.getStationImg4())) {
                this.delete(powerStationBaseBean.getStationImg4());
            }
        }
        return res;
    }

    /**
     * 自动生成电站编码：城市简称+"STATION"+四位随机数
     * @param cityShortName
     * @return
     */
    private String generateStationCode (String cityShortName) {
        String code = cityShortName+"_STATION_"+getStringRandom(4).toUpperCase();
        return code;
    }

    /**
     * 生成随机数
     * @param length 几位
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 删除图片
     * @param img 图片请求地址
     */
    public  void delete(String img)
    {
        int id1 = img.lastIndexOf("/");
        String img1 = img.substring(id1 + 1, img.length());;
        String path ="Station/images/upload/"+img1;
        File f=new File(path);
        f.delete();
    }
}
