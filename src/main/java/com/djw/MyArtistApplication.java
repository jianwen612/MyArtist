package com.djw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = { "com" })
@SpringBootApplication

public class MyArtistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyArtistApplication.class, args);
	}

}
