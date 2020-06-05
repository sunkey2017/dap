package com.longi.dap.dao;

import com.longi.dap.entity.PowerStationInfoBean;
import com.longi.dap.vo.DateTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  电站基本信息mapper
 * @Author sunke5
 * @Description
 * @Date 11:17 2020-4-29
 * @Param
 * @return
 **/
@Component
@Mapper
public interface PowerStationEMapper {

   List<PowerStationInfoBean> getPowerStationEInfo(@Param("stationId") String stationId,@Param("currDate") String currDate);

   List<PowerStationInfoBean> getDataDaily(@Param("dateInfo") DateTypeVO dateInfo);

   List<PowerStationInfoBean> getDataWeekMonth(@Param("dateInfo") DateTypeVO dateInfo);

   List<PowerStationInfoBean> getDataYear(@Param("dateInfo") DateTypeVO dateInfo);

   List<Map<String,String>> getTableColumns(@Param("tableName") String tableName);

   List<Map<String,Object>> getExportData(@Param("exportInfo") DateTypeVO exportInfo);

}
