package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.Role;
import com.example.hrapp.hrapp.Repository.RoleRepository;
import com.example.hrapp.hrapp.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllByName(String name) {

        return roleRepository.findAllByName(name);
    }
}
