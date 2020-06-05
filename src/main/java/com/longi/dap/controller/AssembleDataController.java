package com.longi.dap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AssembleDataController {

    @GetMapping("/index")
    String toIndex(HttpServletRequest request) { return "index"; }

    @GetMapping("/production")
    String toProduction(HttpServletRequest request) {
        return "productionView";
    }

    @GetMapping("/finance")
    String toFinance(HttpServletRequest request) {
        return "finance";
    }

    @GetMapping("/personnel")
    String toPersonnel(HttpServletRequest request) {
        return "personnel";
    }

    @GetMapping("/control")
    String toControl(HttpServletRequest request) {
        return "controlCabin";
    }

    @GetMapping("/detail")
    String toDetail(HttpServletRequest request) {
        return "detailView";
    }

    @RequestMapping("/main")
    String toMainStation(HttpServletRequest request) {
        return "mainStationView";
    }

    @RequestMapping("/alarm")
    String toalarm(HttpServletRequest request) {
        return "alarmView";
    }

    @RequestMapping("/layout")
    String tolayout(HttpServletRequest request) {
        return "layoutView";
    }


    @RequestMapping("/login")
    String tologin(HttpServletRequest request) {
        return "Login";
    }

    @RequestMapping("/accessDenied")
    String to403(HttpServletRequest request) {
        return "403";
    }

    @RequestMapping("/personal")
    String topersonal(HttpServletRequest request) {
        return "personal";
    }

    @RequestMapping("/changePwd")
    String tochangePwd(HttpServletRequest request) {
        return "changePwd";
    }

    @RequestMapping("/user")
    String touser(HttpServletRequest request) {
        return "userMain";
    }

    @RequestMapping("/addUser")
    String toadduser(HttpServletRequest request) {
        return "user";
    }


    @RequestMapping("/updateUser")
    String toupdateuser(HttpServletRequest request) {
        return "updateUser";
    }

    @RequestMapping("/role")
    String torole(HttpServletRequest request) {
        return "roleMain";
    }


    @RequestMapping("/addRole")
    String toaddrole(HttpServletRequest request) {
        return "addRole";
    }


    @RequestMapping("/addStation")
    String toaddstation(HttpServletRequest request) {
        return "addStation";
    }
}
