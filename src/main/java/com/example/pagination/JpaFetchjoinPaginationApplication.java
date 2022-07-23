package com.example.pagination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaFetchjoinPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaFetchjoinPaginationApplication.class, args);
    }

}
