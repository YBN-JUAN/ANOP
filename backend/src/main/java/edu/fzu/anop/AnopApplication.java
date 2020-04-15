package edu.fzu.anop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("edu.fzu.anop.mapper")
@MapperScan("edu.fzu.anop.security.mapper")
@EnableTransactionManagement
public class AnopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnopApplication.class, args);
    }

}
