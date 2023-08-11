package com.asc.rest;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestGradleSupporApplication {
	
	@Autowired
    ProducerTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(RestGradleSupporApplication.class, args);
	}

}
