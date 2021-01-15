package com.example.hrapp.hrapp.DTO.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@ToString
public class BaseSignupRequest {

    @NotBlank(message = "Name must be filled")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Surame must be filled")
    @JsonProperty("surname")
    private String surname;

    @NotBlank(message = "Username must be filled")
    @Email(message = "Please enter valid email")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "Password must be filled")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Please valid your password")
    @JsonProperty("passwordValidation")
    private String passwordValidation;

    @JsonCreator
    public BaseSignupRequest(@JsonProperty("name") final String name,
                                     @JsonProperty("surname") final String surname,
                                     @JsonProperty("username") final String username,
                                     @JsonProperty("password") final String password,
                                     @JsonProperty("passwordValidation") final String passwordValidation) {

        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.passwordValidation = passwordValidation;

    }

    @AssertTrue(message = "Passwords must be same")
    public boolean isPasswordsMatches() {

        return password.equals(passwordValidation);
    }
}
