package com.hjga.crj;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CrjApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrjApplication.class, args);
	}
	
	
}
