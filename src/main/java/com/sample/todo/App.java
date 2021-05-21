package com.sample.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Java Spring Bootを起動するためのクラス
 */
@SpringBootApplication //ここから始まるということを表している
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}