package com.example.hrapp.hrapp.Domain;

import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Authority")
@Data
public class Authority extends AbstractIdEntity implements GrantedAuthority {

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @Column(name = "authority", unique = true)
    @NotNull
    private String authority;

}
