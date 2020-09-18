package com.seek.stocks.task;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.UUID;

public class MqMessageTask extends QuartzJobBean {

    @Autowired
    public DefaultMQProducer producer;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String str = "发送测试消息";
        Message msg;
        try {
            msg = new Message("topic_family"
                    , "111"
                    , UUID.randomUUID().toString()
                    , str.getBytes("utf-8"));
            SendResult result = producer.send(msg);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                System.out.println("消息发送成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
