package edu.fzu.anop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("edu.fzu.anop.mapper")
@MapperScan("edu.fzu.anop.security.mapper")
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class AnopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnopApplication.class, args);
    }

}
