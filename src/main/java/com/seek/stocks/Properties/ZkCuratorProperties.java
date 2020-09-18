package com.seek.stocks.Properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "curator")
public class ZkCuratorProperties {

    private int retryCount;

    private int elapsedTimeMs;

    private String connectString;

    private int sessionTimeOutMs;

    private int connectionTimeOutMs;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getElapsedTimeMs() {
        return elapsedTimeMs;
    }

    public void setElapsedTimeMs(int elapsedTimeMs) {
        this.elapsedTimeMs = elapsedTimeMs;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeOutMs() {
        return sessionTimeOutMs;
    }

    public void setSessionTimeOutMs(int sessionTimeOutMs) {
        this.sessionTimeOutMs = sessionTimeOutMs;
    }

    public int getConnectionTimeOutMs() {
        return connectionTimeOutMs;
    }

    public void setConnectionTimeOutMs(int connectionTimeOutMs) {
        this.connectionTimeOutMs = connectionTimeOutMs;
    }
}
