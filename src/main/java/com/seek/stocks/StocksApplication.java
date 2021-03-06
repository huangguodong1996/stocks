package com.seek.stocks;

import com.seek.stocks.Properties.ZkCuratorProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocksApplication {
    public static Logger log = LoggerFactory.getLogger(StocksApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class,args);
    }
}
