package com.mi.administrador.web.flux.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi.administrador.web.flux.app.model.service.InquilinoService;

import reactor.core.publisher.Flux;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("inquilino")
@RestController
public class InquilinoController {
	
	@Autowired
	private InquilinoService inquilinoService;
	
	@RequestMapping(value = "/listarInquilinos")
	public Flux<?> listar() {
		Flux<?> response = null;

		try{
			response = inquilinoService.findAll();
		} catch (Exception e){
			Map<String, Object> res = new HashMap<>();
			
			res.put("mensaje", "Ha ocurrido un error al consultar la lista de inquilinos");
			res.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMessage()));
			
			return Flux.just(res);		
		}
		return response;
	}

}
