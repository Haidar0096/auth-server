package com.nlimits.authserver.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * This is the main app class
 */
//@SpringBootApplication(scanBasePackages = {"com.nlimits.authserver.adapter.input.web",
//        "com.nlimits.authserver.adapter.output.persistence",
//        "com.nlimits.authserver.application"})
//@EnableJpaRepositories(basePackages = "com.nlimits.authserver.adapter.output.persistence")
//@EntityScan("com.nlimits.authserver.adapter.output.persistence")
@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}