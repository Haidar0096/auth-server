package com.nlimits.authserver.configuration;

import com.nlimits.authserver.adapter.input.web.user.UserWebLayerConfiguration;
import com.nlimits.authserver.adapter.output.persistence.user.UserPersistenceLayerConfiguration;
import com.nlimits.authserver.application.user.UserApplicationLayerConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = {
        UserWebLayerConfiguration.class,
        UserPersistenceLayerConfiguration.class,
        UserApplicationLayerConfiguration.class
})
@EnableJpaRepositories(basePackageClasses = {
        UserPersistenceLayerConfiguration.class
})
@EntityScan(basePackageClasses = {
        UserPersistenceLayerConfiguration.class
})
@Configuration
public class AuthServerConfiguration {
}
