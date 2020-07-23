package com.seek.stocks.autoconfigure;

import com.seek.stocks.Properties.StartServiceProperties;
import com.seek.stocks.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(StartService.class)
@EnableConfigurationProperties(StartServiceProperties.class)
public class StartServiceAutoConfigure {

    @Autowired
    private StartServiceProperties startServiceProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "example.start",value = "enable",havingValue = "true")
    public StartService startService(){
        StartService temp = new StartService(startServiceProperties.getConfig());
        temp.split();
        return  temp;
    }

}
