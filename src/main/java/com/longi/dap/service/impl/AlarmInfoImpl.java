package com.longi.dap.service.impl;

import com.longi.dap.dao.AlarmInfoMapper;
import com.longi.dap.entity.AlarmInfoBean;
import com.longi.dap.service.IAlarmService;
import com.longi.dap.vo.AlarmInfoVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 聚合数据服务层
 * @Date 16:24 2020-5-6
 * @Author sunke5
 * @Param
 * @return
 **/
@Service
public class AlarmInfoImpl implements IAlarmService {

    private static final Logger log = Logger.getLogger (AlarmInfoImpl.class);

    @Autowired
    AlarmInfoMapper alarmDao;

    @Override
    public List<AlarmInfoBean> getRecentlyAlarmInfo(String stationId){
        log.info("method getRecentlyAlarmInfo stationId:"+stationId);
        return alarmDao.getAlarmInfo(stationId);
    }

    @Override
    public List<AlarmInfoBean> getAllAlarmInfo(String stationId){
        return alarmDao.getAlarmInfos(stationId);
    }

    @Override
    public void updateAlarmState(AlarmInfoVO alarmInfo){
        alarmDao.updateAlarmInfo(alarmInfo.getAlarmId());
    }

}
