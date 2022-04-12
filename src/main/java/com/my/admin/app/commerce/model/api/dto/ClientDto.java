package com.my.admin.app.commerce.model.api.dto;

public class ClientDto {

    private Long id;

    private String name;

    private String lastName;

    private String dni;

    private String phone;

    private String address;

    private String email;

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(final String lastName) { this.lastName = lastName; }

    public String getDni() { return dni; }

    public void setDni(final String dni) { this.dni = dni; }

    public String getPhone() { return phone; }

    public void setPhone(final String phone) { this.phone = phone; }

    public String getAddress() { return address; }

    public void setAddress(final String address) { this.address = address; }

    public String getEmail() { return email; }

    public void setEmail(final String email) { this.email = email; }
}