package com.somliy.anime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.somliy.anime.dao")
public class AnimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimeApplication.class, args);
    }

}
