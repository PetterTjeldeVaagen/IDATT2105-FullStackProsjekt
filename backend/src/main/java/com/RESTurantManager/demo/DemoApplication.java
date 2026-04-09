package com.RESTurantManager.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the RESTurant Manager application. This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * The main method that serves as the entry point for the application. It uses SpringApplication.run() to launch the application.
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
