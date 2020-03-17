package com.mi.administrador.web.flux.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mi.administrador.web.flux.app.model.document.Inquilino;
import com.mi.administrador.web.flux.app.model.service.InquilinoService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class MiAdministradorWebFluxApplication implements CommandLineRunner{

	@Autowired
	private InquilinoService inquilinoService;
	
	private static final Logger LOG = LoggerFactory.getLogger(MiAdministradorWebFluxApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MiAdministradorWebFluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		inquilinoService.dropInquilinosCollection();
		
		Flux.just(new Inquilino("Claudio", "Carrizo", "claudio@gmail.com"),
				new Inquilino("Valeria", "Romagnoli", "vale@gmail.com"),
				new Inquilino("Luz del Cielo", "Carrizo", "luz@gmail.com"))
		.flatMap(inquilino -> inquilinoService.saveInquilino(inquilino))
		.subscribe(inquilino -> LOG.info("insert ->".concat(inquilino.toString())));
		;
	}
}
