package com.nlimits.authserver.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AuthServerApplicationTests.TestConfig.class)
class AuthServerApplicationTests {

    //TODO: check why tests in this class are not running

    @Test
    void contextLoads() {
    }

    @ComponentScan(basePackages = {
            "com.nlimits.authserver.adapter.in.web",
            "com.nlimits.authserver.adapter.out.persistence",
            "com.nlimits.authserver.application"})
    @EnableJpaRepositories(basePackages = "com.nlimits.authserver.adapter.out.persistence")
    @EntityScan("com.nlimits.authserver.adapter.out.persistence")
    public static class TestConfig {
    }

}
