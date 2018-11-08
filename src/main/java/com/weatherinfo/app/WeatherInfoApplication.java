package com.weatherinfo.app;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.weatherinfo.app.config.WeatherAppProperties;
import com.weatherinfo.app.service.WeatherService;

@SpringBootApplication
@EnableConfigurationProperties(WeatherAppProperties.class)
public class WeatherInfoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherInfoApplication.class);
	
	@PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        logger.info("Spring boot application running in UTC timezone :"+new Date());
    }

	public static void main(String[] args) {
		SpringApplication.run(WeatherInfoApplication.class, args);
	}
}
