package com.longi.dap.dao;

import com.longi.dap.entity.CapacityEntity;
import com.longi.dap.entity.MarketShareEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InitDaoMapper {

    public List<CapacityEntity> getCapacityData(String typeName);

    public List<MarketShareEntity> getMarketShareData();

}
