package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "department")
@Data
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
}
