package com.example.hrapp.hrapp.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseResponse {

    private boolean success;

    private String message;
}
