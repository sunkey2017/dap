package com.longi.dap.controller;


import com.longi.dap.service.IExportService;
import com.longi.dap.tookits.ExcelUtil;
import com.longi.dap.vo.DateTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author sunke5
 * @Description  PowerStationBaseController
 * @Date 11:52 2020-4-29
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/export")
public class PSDataExportController {

    private static final Logger log = LoggerFactory.getLogger(PSDataExportController.class);

    @Autowired
    IExportService exportService;

    /**
     * @Description  根据时间和时间类型组装数据
     * @Date 16:09 2020-5-6
     * @Param [dateInfo]
     * @return com.longi.dap.entity.WebResult
     **/
    @RequestMapping(value="/powerData")
    public void powerData(DateTypeVO exportDataVO, HttpServletResponse response){
        List<List<String>> excelData = new ArrayList<>();
        if(null != exportDataVO){
            //获取用户选择的字段
            String[] needColumnsArray = exportDataVO.getFieldStr().split(",");
            StringBuffer fields = new StringBuffer();
            Map<String,String> fieldMap = new LinkedHashMap<>();
            for(int i=0;i<needColumnsArray.length;i++){
                fields.append(needColumnsArray[i].split("-")[0]);
                fields.append(",");
                fieldMap.put(needColumnsArray[i].split("-")[0],needColumnsArray[i].split("-")[1]);
            }

            exportDataVO.setFieldStr(fields.toString().substring(0,fields.length()-1));

            //获取用户选择的时间段内的数据
            List<Map<String,Object>> queryDataList = exportService.getExportData(exportDataVO);

            List<String> head = exportService.initHeadTitle(fieldMap);
            excelData.add(head);

            queryDataList.forEach(sx -> {
                List<String> data = new ArrayList<>();
                fieldMap.forEach((k,v) ->{
                    data.add(null == sx.get(k)? "":sx.get(k).toString());
                });
                excelData.add(data);
            });

            String sheetName = "data_sheet";
            String fileName = "export_data.xls";

            try {
                ExcelUtil.exportExcel(response, excelData, sheetName, fileName, 15);
            } catch (IOException e) {
                log.error("export data Error: "+e);
            }

        }

    }




}
