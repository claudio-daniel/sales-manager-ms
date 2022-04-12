package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Document(collection = "sales")
@Data
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Order order;

    private Client client;

    private ZonedDateTime date;

    private PaymentType paymentType;

    private Cash cash;

    private Double payment;

    private Double returned;

    private Double total;
}
