package com.deinersoft.checkwordster.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CheckWordsterConfiguration {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(CheckWordsterConfiguration.class, args);
	}

	public static void initiateShutdown() {
		SpringApplication.exit(applicationContext);
	}
}