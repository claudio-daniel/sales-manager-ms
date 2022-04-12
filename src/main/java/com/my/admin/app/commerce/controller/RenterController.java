package com.my.admin.app.commerce.controller;

import com.my.admin.app.commerce.model.document.Renter;
import com.my.admin.app.commerce.service.RenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/my_administration/renters")
@RestController
public class RenterController {

	private static final Logger log = LoggerFactory.getLogger(RenterController.class);

	@Autowired
	private RenterService renterService;
	
	@GetMapping
	public Flux<?> getAllRenters() {
		Flux<?> response = null;

		try{
			response = renterService.findAll();
		} catch (Exception e){
			Map<String, Object> res = new HashMap<>();
			
			res.put("mensaje", "Ha ocurrido un error al consultar la lista de inquilinos");
			res.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMessage()));
			
			return Flux.just(res);		
		}
		log.info("get all renters");

		return response;
	}

	@GetMapping(value = "/{id}")
	public Mono<?> getRenter(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();

		Mono<Renter> renterView = null;

		if (! StringUtils.isEmpty(id)) {
			try {
				renterView = renterService.findRenterById(id);
			}
			catch(DataAccessException e) {
				response.put("mensaje", "error al reaizar la consulta a la base de datos");
				response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

				return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
			}
		}

		if(renterView == null){
			response.put("mensaje", "El inquilino con el id " + id + " no se encuentra registrado");

			return Mono.just(new ResponseEntity<>(response, HttpStatus.NOT_FOUND));
		}

		return renterView;
	}

	@PostMapping
	public Mono<?> create(@Valid @RequestBody Renter renter) {
		Mono<Renter> renterNew;

		try {
			renterNew = renterService.saveRenter(renter);
		}
		catch(ConstraintViolationException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("mensaje", "error al reaizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getLocalizedMessage()));

			return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
		}
		return renterNew;
	}

	@PutMapping(value = "/{id}")
	public Mono<?> update(@Valid @RequestBody Renter renterView, @PathVariable String id) {

		Mono<Renter> renterCurrent = renterService.findRenterById(id);
		Mono<Renter> renterEdit;

		Map<String, Object> response = new HashMap<>();

		if(renterCurrent == null){
			response.put("mensaje", "Error : no es posible editar, El inquilino con el id " + id + " no se encuentra registrado");

			return Mono.just(new ResponseEntity<>(response, HttpStatus.NOT_FOUND));
		}

		try {
			 renterEdit = renterCurrent.flatMap( renter -> {
			 	renter.setName(renterView.getName());
			 	renter.setLastName(renterView.getLastName());
			 	renter.setEmail(renterView.getEmail());

			 	return renterService.saveRenter(renter);
			});

		}

		catch(DataAccessException e) {
			response.put("mensaje", "error al editar el inquilino en la base de datos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

			return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
		}
		renterCurrent.subscribe();
		return renterEdit;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();

		try {
			renterService.deleteRenterById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "error al eliminar inquilino en la base de datos");
			response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje ", "operación exitosa : el inquilino ha sido eliminado de la base de datos");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
