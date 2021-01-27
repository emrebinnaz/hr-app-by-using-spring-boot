package com.example.hrapp.hrapp.Domain;

import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Table(name="users")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends AbstractIdEntity implements UserDetails {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;


    @ManyToMany(fetch = FetchType.EAGER) //TODO: It is not best practise
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getGrantedAuthorities(getRoles());
    }


    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
