package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private List<OrderItem> orderItems;

    private OrderType orderType;
}
