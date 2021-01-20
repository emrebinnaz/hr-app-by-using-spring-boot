package com.example.hrapp.hrapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO implements Serializable {

    @ReadOnlyProperty()
    @JsonProperty("id")
    private String id;

    @NotBlank(message = "Description must be filled")
    @JsonProperty("description")
    private String description;

    @JsonProperty("lastApplicationDate")
    @FutureOrPresent(message = "Last application date is invalid")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastApplicationDate;

    @Min(value = 1, message = "Number of people to hire must be bigger than 0")
    @JsonProperty("numberOfPeopleToHire")
    private int numberOfPeopleToHire;

    @NotBlank(message = "Title must be filled")
    @JsonProperty("title")
    private String title;

}
