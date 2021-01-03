package com.nlimits.authserver.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.nlimits.authserver.adapter.in.web",
        "com.nlimits.authserver.adapter.out.persistence",
        "com.nlimits.authserver.application"})
@EnableJpaRepositories(basePackages = "com.nlimits.authserver.adapter.out.persistence")
@EntityScan("com.nlimits.authserver.adapter.out.persistence")
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(ApplicationContext appContext) {
//        return args -> {
//
//            String[] beans = appContext.getBeanDefinitionNames();
//            Arrays.stream(beans).sorted().forEach(System.out::println);
//
//        };
//    }

}
