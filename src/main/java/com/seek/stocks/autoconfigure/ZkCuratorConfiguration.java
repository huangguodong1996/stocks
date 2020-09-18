package com.seek.stocks.autoconfigure;

import com.seek.stocks.Properties.RocketMqProperties;
import com.seek.stocks.Properties.ZkCuratorProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkCuratorConfiguration {

    @Autowired
    ZkCuratorProperties zkCuratorProperties;


    @Bean(initMethod = "start")
    @ConditionalOnBean(RocketMqProperties.class)
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                zkCuratorProperties.getConnectString(),
                zkCuratorProperties.getSessionTimeOutMs(),
                zkCuratorProperties.getConnectionTimeOutMs(),
                new RetryNTimes(zkCuratorProperties.getRetryCount(), zkCuratorProperties.getElapsedTimeMs()));
    }
}

