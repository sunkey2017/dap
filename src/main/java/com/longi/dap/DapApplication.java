package com.longi.dap;

import com.github.abel533.echarts.Option;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(scanBasePackages={"com.longi.*"})
@MapperScan("com.longi.dap.dao")
@EnableEurekaServer
public class DapApplication {

    public static void main(String[] args) {
        SpringApplication.run(DapApplication.class, args);
    }

}
