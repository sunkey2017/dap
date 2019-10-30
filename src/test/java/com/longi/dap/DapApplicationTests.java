package com.longi.dap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longi.dap.dao.ShipmentsMapper;
import com.longi.dap.entity.Shipments;
import com.longi.dap.service.ShipmentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DapApplicationTests {

    @Autowired
    ShipmentsService shipmentsService;
    @Autowired
    ShipmentsMapper shipmentsMapper;

    @Test
    public void contextLoads() {

        String optionData= shipmentsService.selectWipInfo();

        System.out.println(optionData);
    }

}
