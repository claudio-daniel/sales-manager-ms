package com.my.admin.app.commerce.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "department")
public class Department {
    @Id
    private String id;

    @NotEmpty
    private String name;

    //private Edificio edificio;

    //private Propietario propietario;

    @NotNull
    private Renter renter;

    //private List<Servicio> servicios;

    //private List<Expensa> expensas;

    //private DepartamentoEstado estado;

    private Integer quantityRooms;

    private LocalDate createAt;

    public Department() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Integer getQuantityRooms() {
        return quantityRooms;
    }

    public void setQuantityRooms(Integer quantityRooms) {
        this.quantityRooms = quantityRooms;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
