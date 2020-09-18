package com.seek.stocks.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;


public class ZkQuartzTask extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("=======:"+new Date());
    }
}
