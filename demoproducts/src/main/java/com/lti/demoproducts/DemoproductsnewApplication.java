package com.lti.demoproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lti.demoproducts.controller"})
public class DemoproductsnewApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoproductsnewApplication.class, args);
	}
}
