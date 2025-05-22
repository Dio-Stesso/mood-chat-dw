package com.kirei.webchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.kirei.webchat.model")
@EnableJpaRepositories(basePackages = "com.kirei.webchat.repository")
@SpringBootApplication
public class MoodChatApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MoodChatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MoodChatApplication.class, args);
    }
}
