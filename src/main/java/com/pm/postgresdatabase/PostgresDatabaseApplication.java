//package com.pm.postgresdatabase;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@SpringBootApplication
//@Slf4j
//public class PostgresDatabaseApplication implements CommandLineRunner {
//
//    private final DataSource dataSource;
//
//    public static void main(String[] args) {
//        SpringApplication.run(PostgresDatabaseApplication.class, args);
//    }
//
//    public PostgresDatabaseApplication(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public void run(String... args) {
//        log.info("DataSource: {}", dataSource);
//        JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
//        restTemplate.execute("select 1");
//    }
//}

package com.pm.postgresdatabase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PostgresDatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostgresDatabaseApplication.class, args);
    }
}