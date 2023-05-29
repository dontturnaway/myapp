package com.test.myapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MyApp {

	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
		log.info("Myapp has runned");
	}

}
