package com.mi.administrador.web.flux.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mi.administrador.web.flux.app.model.document.Renter;
import com.mi.administrador.web.flux.app.model.service.RenterService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class MiAdministradorWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiAdministradorWebFluxApplication.class, args);
	}
}
