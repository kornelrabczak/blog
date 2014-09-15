package com.thecookiezen.blog;

import com.thecookiezen.blog.controller.com.thecookiezen.blog.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@ComponentScan
@Configuration
@ImportResource("META-INF/spring/applicationContext.xml")
@Import(SecurityConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
