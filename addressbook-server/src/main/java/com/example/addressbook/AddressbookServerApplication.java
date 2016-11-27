package com.example.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Starter class for backend application.
 */
@EnableResourceServer
@SpringBootApplication
public class AddressbookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressbookServerApplication.class, args);
	}
}
