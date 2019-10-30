package com.longi.dap.controller;

import com.longi.dap.service.EChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AssembleDataController {

    @GetMapping("/index")
    String toIndex(HttpServletRequest request) {

        return "/index";
    }

    @GetMapping("/production")
    String toProduction(HttpServletRequest request) {
        return "/productionView";
    }

    @GetMapping("/finance")
    String toFinance(HttpServletRequest request) {
        return "/finance";
    }
    @GetMapping("/personnel")
    String toPersonnel(HttpServletRequest request) {
        return "/personnel";
    }

}
