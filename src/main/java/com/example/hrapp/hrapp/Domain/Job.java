package com.example.hrapp.hrapp.Domain;

import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "jobs")
public class Job extends AbstractIdEntity {

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "numberOfPeopleToHire")
    @NotNull
    private Integer numberOfPeopleToHire;

    @Column(name = "lastApplicationDate")
    @NotNull
    private LocalDate lastApplicationDate;

    @NotNull
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hrmanager_id")
    private HrManager hrManager;

    @OneToMany(mappedBy = "job",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobApplication> jobApplications;

}
