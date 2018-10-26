package com.puzhibing.trialtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling//开启定时任务
@SpringBootApplication
public class TrialTaskApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TrialTaskApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TrialTaskApplication.class);
    }

}
