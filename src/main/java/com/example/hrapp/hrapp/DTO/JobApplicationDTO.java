package com.example.hrapp.hrapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {


    @ReadOnlyProperty()
    @JsonProperty("id")
    private String id;

    @NotBlank(message = "Address must not be empty !")
    @JsonProperty("applicantAddress")
    private String applicantAddress;

    @NotBlank(message = "Name must not be empty !")
    @JsonProperty("applicantName")
    private String applicantName;

    @NotBlank(message = "Email must not be empty !")
    @Email(message = "Please enter valid email")
    @JsonProperty("applicantEmail")
    private String applicantEmail;

    @NotBlank(message = "Phone must not be empty !")
    @JsonProperty("applicantPhone")
    private String applicantPhone;

    @NotBlank(message = "Resume must not be empty !")
    @JsonProperty("applicantResume")
    private String applicantResume;

    @NotBlank(message = "Surname must not be empty !")
    @JsonProperty("applicantSurname")
    private String applicantSurname;

    @NotBlank(message = "Thoughts on the job must not be empty !")
    @JsonProperty("thoughtsOfApplicantOnTheJob")
    private String thoughtsOfApplicantOnTheJob;

}
