package com.example.hrapp.hrapp.Service;

import com.example.hrapp.hrapp.Domain.Role;

import java.util.List;


public interface RoleService {

    List<Role> findAllByName(String name);
}
