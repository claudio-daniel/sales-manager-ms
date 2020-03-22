package com.mi.administrador.web.flux.app.model.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "renters")
public class Renter {

	@Id
	private String id;
	
	private String name;
	
	private String lastName;
	
	private String email;
	
	private LocalDate createAt;
	
	public Renter() {}
	
	public Renter(String nombre, String apellido, String email) {
		this.name = nombre;
		this.lastName = apellido;
		this.email = email;
	}

	public String getId() {return id;}

	public void setId(String id) {this.id = id;}

	public String getName() {	return name;}

	public void setName(String name) {this.name = name;}

	public String getLastName() {return lastName;}

	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public LocalDate getCreateAt() {return createAt;}

	public void setCreateAt(LocalDate createAt) {this.createAt = createAt;}

	@Override
	public String toString() {
		return "name:".concat(this.name).concat(" lastName:").concat(this.lastName);
	}
	
	
}
