package com.mi.administrador.web.flux.app.model.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inquilinos")
public class Inquilino {

	@Id
	private String id;
	
	private String nombre;
	
	private String apellido;
	
	private String email;
	
	private LocalDate createAt;
	
	public Inquilino() {}
	
	public Inquilino(String nombre, String apellido, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public String getId() {return id;}

	public void setId(String id) {this.id = id;}

	public String getNombre() {	return nombre;}

	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getApellido() {return apellido;}

	public void setApellido(String apellido) {this.apellido = apellido;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public LocalDate getCreateAt() {return createAt;}

	public void setCreateAt(LocalDate createAt) {this.createAt = createAt;}

	@Override
	public String toString() {
		return "nombre:".concat(this.nombre).concat(" apellido:").concat(this.apellido);
	}
	
	
}
