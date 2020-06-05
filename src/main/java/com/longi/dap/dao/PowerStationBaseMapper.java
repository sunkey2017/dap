package com.longi.dap.dao;

import com.longi.dap.entity.PowerStationBaseBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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
public interface PowerStationBaseMapper {

   List<PowerStationBaseBean> getPowerStationBaseInfo(@Param("searchWord") String searchWord);
}
