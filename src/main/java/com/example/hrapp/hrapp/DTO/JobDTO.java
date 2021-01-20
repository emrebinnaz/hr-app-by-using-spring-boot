package com.example.hrapp.hrapp.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
public class JobDTO implements Serializable {

    @NotBlank(message = "Description must be filled")
    @JsonProperty("description")
    private String description;

    @JsonProperty("lastApplicationDate")
    @FutureOrPresent(message = "Last application date is invalid")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate lastAppliationDate;

    @Min(value = 1, message = "Number of people to hire must be bigger than 0")
    @JsonProperty("numberOfPeopleToHire")
    private int numberOfPeopleToHire;

    @NotBlank(message = "Title must be filled")
    @JsonProperty("title")
    private String title;

    //TODO: find a solution for is_deleted ?
    //TODO: lastAppliationDate is always null at table ?

    @JsonCreator
    public JobDTO(@JsonProperty("description") final String description,
                             @JsonProperty("lastApplicationDate") final LocalDate lastAppliationDate,
                             @JsonProperty("numberOfPeopleToHire") final int numberOfPeopleToHire,
                             @JsonProperty("title") final String title) {

        this.description = description;
        this.lastAppliationDate = lastAppliationDate;
        this.numberOfPeopleToHire = numberOfPeopleToHire;
        this.title = title;

    }
}
