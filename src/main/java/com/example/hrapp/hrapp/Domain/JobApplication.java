package com.example.hrapp.hrapp.Domain;


import com.example.hrapp.hrapp.Domain.Common.AbstractIdEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class JobApplication extends AbstractIdEntity {

    @NotBlank()
    private String applicantName;

    @NotBlank()
    private String applicantSurname;

    @NotBlank()
    private String applicantAddress;

    @NotBlank()
    private String applicantPhone;

    @NotBlank()
    private String thoughtsOfApplicantOnTheJob;

    @Lob
    private byte [] applicantResume;

    @NotBlank()
    private String applicantEmail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private Job job;
}
