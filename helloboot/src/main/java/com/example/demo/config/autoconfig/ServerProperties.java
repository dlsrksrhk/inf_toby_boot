package com.example.demo.config.autoconfig;

import com.example.demo.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "server")
public class ServerProperties {

    private String contextPath;

    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
