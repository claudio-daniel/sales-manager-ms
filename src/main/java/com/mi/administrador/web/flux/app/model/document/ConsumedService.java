package com.mi.administrador.web.flux.app.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "consumed_services")
public class ConsumedService {

    @Id
    private String id;

    @NotEmpty
    private String name;

    private String description;

    @NotNull
    private Double price;

    public ConsumedService() { }

    public ConsumedService(@NotEmpty String name, @NotNull Double price) {
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}
