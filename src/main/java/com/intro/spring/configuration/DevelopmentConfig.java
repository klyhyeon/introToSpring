package com.intro.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class DevelopmentConfig {

}

@Configuration
@Profile("staging")
class StagingConfig {

}

@Configuration
@Profile("production")
class ProductionConfig {

}


