package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "order_items")
@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Order order;

    private Product product;

    private Integer count;
}
