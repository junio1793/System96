package com.abstractentity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

public class LoggingConfiguration implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Autowired
    private LoggingSystem loggingSystem;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        loggingSystem.setLogLevel("org.hibernate", org.springframework.boot.logging.LogLevel.ERROR);
        loggingSystem.setLogLevel("org.hibernate.SQL", org.springframework.boot.logging.LogLevel.ERROR);
        loggingSystem.setLogLevel("org.hibernate.type.descriptor.sql", org.springframework.boot.logging.LogLevel.ERROR);
        loggingSystem.setLogLevel("org.hibernate.Version", org.springframework.boot.logging.LogLevel.ERROR);

        loggingSystem.setLogLevel("com.abstractentity", org.springframework.boot.logging.LogLevel.ERROR);

        loggingSystem.setLogLevel("org.springframework", org.springframework.boot.logging.LogLevel.ERROR);
        loggingSystem.setLogLevel("org.apache.catalina", org.springframework.boot.logging.LogLevel.ERROR);

    }
}
