package org.example.email.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private Long idCart;
    private String firstname;
    private String lastname;

    private String email;
    private String country;
    private String password;

}
