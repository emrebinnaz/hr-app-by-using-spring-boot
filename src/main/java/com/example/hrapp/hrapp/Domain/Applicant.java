package com.example.hrapp.hrapp.Domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@DiscriminatorValue("applicant")
public class Applicant extends User {

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "applicant",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobApplication> jobApplications;

}
