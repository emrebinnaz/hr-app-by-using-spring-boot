package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.Domain.Role;
import com.example.hrapp.hrapp.Response.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface RoleService {

    Role findByName(String name);

    BaseResponse addRole(String roleName);
}
