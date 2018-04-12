package com.hm.numbersorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;


/**
 * Spring Boot web application
 * @author tempprmad
 *
 */
@SpringBootApplication
    public class WebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
        }
    }

