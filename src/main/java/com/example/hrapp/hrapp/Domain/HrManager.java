package com.example.hrapp.hrapp.Domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@DiscriminatorValue("hrmanager")
public class HrManager extends User {

    @Column(name = "password")
    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hrmanager_id")
    private Set<Job> jobs;
}
