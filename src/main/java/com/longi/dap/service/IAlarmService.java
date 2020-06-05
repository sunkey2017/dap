package com.longi.dap.service;

import com.longi.dap.entity.AlarmInfoBean;
import com.longi.dap.vo.AlarmInfoVO;

import java.util.List;

/**
 *
 * @Author sunke5
 * @Description
 * @Date 17:00 2020-5-6
 * @Param
 * @return
 **/
public interface IAlarmService {

    List<AlarmInfoBean> getRecentlyAlarmInfo(String stationId);

    List<AlarmInfoBean> getAllAlarmInfo(String stationId);

    void updateAlarmState(AlarmInfoVO alarmInfo);
}
