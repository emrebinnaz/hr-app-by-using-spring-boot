package com.example.hrapp.hrapp.Domain;

import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Role extends AbstractIdEntity {

    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(final String name) {
        this.name = name;
    }

}
