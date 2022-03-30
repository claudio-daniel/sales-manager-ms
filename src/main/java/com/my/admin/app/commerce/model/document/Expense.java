package com.my.admin.app.commerce.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;

    @NotEmpty
    private String departmentId;

    @NotNull
    private Double precio;

    public Expense() {
    }

    public Expense(@NotEmpty String departmentId, @NotNull Double precio) {
        this.departmentId = departmentId;
        this.precio = precio;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDepartmentId() { return departmentId; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public Double getPrecio() { return precio; }

    public void setPrecio(Double precio) { this.precio = precio; }
}
