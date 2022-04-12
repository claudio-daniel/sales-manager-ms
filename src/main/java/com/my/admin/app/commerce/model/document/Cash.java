package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Document(collection = "cashes")
@Data
public class Cash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String _id;

    private Turn turn;

    private User user;

    private CashStatus cashStatus;

    private Double startMoney;

    private Double endMoney;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;
}
