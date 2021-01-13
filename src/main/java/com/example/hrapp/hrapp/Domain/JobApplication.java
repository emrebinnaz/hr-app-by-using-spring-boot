package com.example.hrapp.hrapp.Domain;


import com.example.hrapp.hrapp.Domain.Common.JobApplicationPK;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(JobApplicationPK.class)
public class JobApplication implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private Applicant applicant;

    private String thoughtsOnTheJob;

    @NotNull()
    private String resume;

}
