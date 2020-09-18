package com.seek.stocks.autoconfigure;

import com.seek.stocks.task.MqMessageTask;
import com.seek.stocks.task.ZkQuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {


    /**
     * 定时任务-测试输出时间任务设置
     * @return
     */
    @Bean
    public Trigger zkQuartzTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder  = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(zkQuartzDetail()).withIdentity("zkQuartzTask")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }

    /**
     * 定时任务-测试输出时间填入具体实现
     * @return
     */
    @Bean
    public JobDetail zkQuartzDetail(){
        return JobBuilder.newJob(ZkQuartzTask.class).withIdentity("zkQuartzTask").storeDurably().build();
    }

    /**
     * 定时任务-发送mq测试信息设置
     * @return
     */
    @Bean
    public Trigger sendMqMessage(){
        SimpleScheduleBuilder simpleScheduleBuilder  = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(sendMessageDetail()).withIdentity("MqMessageTask")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }

    /**
     * 定时任务-发送mq测试信息填充具体实现
     * @return
     */
    @Bean
    public JobDetail sendMessageDetail(){
        return JobBuilder.newJob(MqMessageTask.class).withIdentity("MqMessageTask").storeDurably().build();
    }

}
