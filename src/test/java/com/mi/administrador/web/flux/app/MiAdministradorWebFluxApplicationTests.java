package com.mi.administrador.web.flux.app;

import com.mi.administrador.web.flux.app.model.document.Edifice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MiAdministradorWebFluxApplicationTests {

	@Autowired
	private WebTestClient client;

	@Test
	void listEdifices(){
		client.get()
				.uri("my_administration/edifices")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Edifice.class)
				.consumeWith(response -> {
					List<Edifice> edifices = response.getResponseBody();

					assert edifices != null;
					Assertions.assertTrue( edifices.size() > 0);
				})
				//.hasSize(1)
				;
	}

}
