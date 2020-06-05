package com.longi.dap.service;

import com.longi.dap.vo.DateTypeVO;

import java.util.List;
import java.util.Map;

/**
 *
 * @Author sunke5
 * @Description
 * @Date 17:00 2020-5-6
 * @Param
 * @return
 **/
public interface IExportService {

    List<Map<String,Object>> getExportData(DateTypeVO exportInfo);

    public List<String> initHeadTitle(Map<String,String> fieldMap);
}
