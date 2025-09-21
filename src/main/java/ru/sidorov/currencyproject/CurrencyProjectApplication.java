package ru.sidorov.currencyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyProjectApplication.class, args);
    }

}
