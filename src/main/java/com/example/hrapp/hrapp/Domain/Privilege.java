package com.example.hrapp.hrapp.Domain;

import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Privilege extends AbstractIdEntity {

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    private String name;

    public Privilege(final String name) {
        this.name = name;
    }

}
