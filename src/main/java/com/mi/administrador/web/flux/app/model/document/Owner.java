package com.mi.administrador.web.flux.app.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Document(collection = "owners")
public class Owner {

	@Id
	private String id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String lastName;


	private String email;

	private LocalDate createAt;

	public Owner() {}

	public Owner(String nombre, String apellido, String email) {
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
