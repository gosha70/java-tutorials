package com.egoge.examples.lazyfetch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.egoge.sdlc.dao")
public class JpaConfig {
}
