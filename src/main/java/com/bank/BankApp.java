package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EntityScan(basePackages = "com.bank.model")
@EnableJpaRepositories(basePackages = "com.bank.repository")
public class BankApp {

    public static void main(String[] args) {
        SpringApplication.run(BankApp.class, args);
    }
}
