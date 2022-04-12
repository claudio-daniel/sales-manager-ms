package com.my.admin.app.commerce.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "users")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String name;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private String address;

    //@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    //private Set<UserRole> userRoles;
}
