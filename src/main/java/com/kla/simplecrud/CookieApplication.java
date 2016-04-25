package com.kla.simplecrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.kla.simplecrud.model")
@EnableJpaRepositories(basePackages = {"com.kla.simplecrud"})
@EnableTransactionManagement
public class CookieApplication extends WebMvcAutoConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(CookieApplication.class);
    }
}


