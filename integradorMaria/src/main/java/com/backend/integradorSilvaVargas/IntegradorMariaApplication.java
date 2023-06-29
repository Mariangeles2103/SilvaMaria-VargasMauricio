package com.backend.integradorSilvaVargas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegradorMariaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegradorMariaApplication.class);


	public static void main(String[] args) {

		SpringApplication.run(IntegradorMariaApplication.class, args);
		LOGGER.info("Integrador Clinica Odontologica is now running ...");

	}

}
