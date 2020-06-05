package com.longi.dap.controller;


import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.IAlarmService;
import com.longi.dap.tookits.CommonUtil;
import com.longi.dap.tookits.WebResultUtil;
import com.longi.dap.vo.AlarmInfoVO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

/**
 * @Author sunke5
 * @Description  PowerStationMQTTController
 * @Date 11:52 2020-5-10
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/mqtt")
public class PowerStationMQTTController {

    private static final Logger log =  LoggerFactory.getLogger(PowerStationMQTTController.class);

    @Value("${mqtt.path}")
    private String url;
    //private static final String url="failover:(tcp://10.0.10.106:61616,tcp://10.0.10.107:61616,tcp://10.0.10.108:61616)?randomize=true";
    //private static final String topicName="client_component";

    @Autowired
    IAlarmService alarmService;

    @RequestMapping(value="/sendMessage",method= RequestMethod.POST)
    public WebResult sendMessage(@RequestBody AlarmInfoVO alarmInfoVO){
        String resultStr = "send msg success";
        WebResult result = null;
        try
        {
            //1.创建connectionfactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin",url);

            //2.创建Connection
            Connection connection = connectionFactory.createConnection();

            //3.建立连接
            connection.start();

            //4.创建会话
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);  //false:是否用事务中心处理，AUTO_ACKNOWLEDGE:自动应答

            //5.创建一个目标
            String topicName = "";
            if("1".equals(alarmInfoVO.getAlarmType())){
                topicName = "client_component";
            }else if("2".equals(alarmInfoVO.getAlarmType())){
                topicName = "outTopic_TEP";
            }else if("3".equals(alarmInfoVO.getAlarmType())){
                topicName = "outTopic_HUM";
            }
            Topic destination = session.createTopic(topicName);

            //6.创建生产者
            MessageProducer producer = session.createProducer(destination);

            //7.创建消息
            TextMessage textMessage = session.createTextMessage(alarmInfoVO.getAlarmTxt());

            //8.发送消息
            producer.send(textMessage);

           log.info("发送消息:"+textMessage.getText());

            //更新警告信息状态
            alarmService.updateAlarmState(alarmInfoVO);
            //9.关闭连接
            connection.close();

            result = WebResultUtil.getResult( JSON.toJSONString(resultStr));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            log.error("mqtt send message error: "+e);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }


}
