package com.longi.dap.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName Shipments
 * @Description TODO
 * @Author jiangsida
 * @VERSION 1.0
 **/
@Component
@Data
public class Shipments {

    String  company;

    String  market;

    String mount;

    String year;


}
