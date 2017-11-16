package com.YasirErkam.HavaDurumu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HavaDurumuApplication {

    private static final Logger log = LoggerFactory.getLogger(HavaDurumuApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HavaDurumuApplication.class, args);
    }

}
