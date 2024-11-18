package org.andarworld.authenticationservice.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.andarworld.authenticationservice.persistence.repository")
@EntityScan(basePackages = "org.andarworld.authenticationservice.persistence.model")
public class PersistenceConfig {
}
