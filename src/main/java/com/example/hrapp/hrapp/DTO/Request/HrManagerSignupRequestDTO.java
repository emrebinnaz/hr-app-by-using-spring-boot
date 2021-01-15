package com.example.hrapp.hrapp.DTO.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HrManagerSignupRequestDTO extends BaseSignupRequest{

    public HrManagerSignupRequestDTO(@JsonProperty("name") final String name,
                                     @JsonProperty("surname") final String surname,
                                     @JsonProperty("username") final String username,
                                     @JsonProperty("password") final String password,
                                     @JsonProperty("passwordValidation") final String passwordValidation) {

        super(name,surname,username,password,passwordValidation);
    }
}
