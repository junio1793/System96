package com.abstractentity;

import com.abstractentity.config.LoggingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;

import java.util.Set;


@SpringBootApplication
public class AppMainRun {

    private static final LoggingConfiguration loggingConfiguration;

    static {
        loggingConfiguration = new LoggingConfiguration();
    }

    private static Logger appMainLogger = LoggerFactory.getLogger(AppMainRun.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AppMainRun.class);
        Set<ApplicationListener<?>> setListeners = springApplication.getListeners();

        setListeners.forEach(applicationListener -> {
            springApplication.addListeners(loggingConfiguration);
        });

        springApplication.setListeners(setListeners);
        springApplication.run(args);
    }
}
