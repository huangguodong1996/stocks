package com.seek.stocks.autoconfigure;

import com.seek.stocks.Properties.RocketMqProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class RocketmqConfiguration {

    Logger log = LoggerFactory.getLogger(RocketmqConfiguration.class);


    @Autowired
    RocketMqProperties rocketMqProperties;


    //消费者组
    public static final String CONSUMER_GROUP = "test_consumer";
    private static final String producerGroup = "test_producer";


    //通过构造函数 实例化对象
    @Bean
    public DefaultMQPushConsumer Consumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(rocketMqProperties.getNameServer());
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(rocketMqProperties.getTopic(), "*");
        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            try {
                for (Message msg : msgs) {

                    //消费者获取消息 这里只输出 不做后面逻辑处理
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", msg.getTopic(), body);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("消费者 启动成功=======");
        return consumer;
    }

    @Bean
    public DefaultMQProducer Producter() throws MQClientException {
        //示例生产者
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr(rocketMqProperties.getNameServer());
        producer.start();
        return producer;
    }

}
