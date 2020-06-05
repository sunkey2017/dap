package com.longi.dap.service.impl;

import com.longi.dap.dao.PowerStationEMapper;
import com.longi.dap.service.IExportService;
import com.longi.dap.vo.DateTypeVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 聚合数据服务层
 * @Date 16:24 2020-5-6
 * @Author sunke5
 * @Param
 * @return
 **/
@Service
public class ExportDataImpl implements IExportService {

    private static final Logger log = Logger.getLogger (ExportDataImpl.class);

    @Autowired
    PowerStationEMapper powerEMapper;

    @Autowired
    DateTimeServiceHelper dateServiceHelper;

    @Override
    public List<Map<String,Object>> getExportData(DateTypeVO exportInfo){

        //获取某个电站日期内的数据
        List<Map<String,Object>> exportDataList = powerEMapper.getExportData(exportInfo);

        //重新设置一些属性
       // dateServiceHelper.resetAttr(exportDataList,"day");

        return exportDataList;
    }

    @Override
    public List<String> initHeadTitle(Map<String,String> fieldMap){
        List<String> head = new ArrayList<>();
        fieldMap.forEach((k,v) ->{
            head.add(v);
        });
        return head;
    }

    public void ff(){

    }



}
