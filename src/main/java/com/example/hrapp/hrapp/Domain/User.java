package com.example.hrapp.hrapp.Domain;

import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="users")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends AbstractIdEntity{

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true)
    private String email;

}
