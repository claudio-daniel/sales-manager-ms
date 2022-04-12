package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "product_topping")
@Data
public class ProductTopping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long mainDishId;

    @Id
    private Long toppingId;
}
