package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users_roles")
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long userId;

    @Id
    private Long roleId;
}
