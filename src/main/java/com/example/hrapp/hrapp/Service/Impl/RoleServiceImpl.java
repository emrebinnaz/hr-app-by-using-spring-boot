package com.example.hrapp.hrapp.Service.Impl;

import com.example.hrapp.hrapp.Domain.Role;
import com.example.hrapp.hrapp.Exception.Exceptions.NotFoundException;
import com.example.hrapp.hrapp.Exception.Exceptions.NotUniqueException;
import com.example.hrapp.hrapp.Repository.RoleRepository;
import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role findByName(String name) {

        if(!roleRepository.existsByName(name)) {

            throw new NotFoundException("Role cannot found.");
        } else{
            return roleRepository.findByName(name);
        }
    }


    @Override
    public BaseResponse addRole(final String roleName) {
            // TODO: Make it better with dto.
        if(roleRepository.existsByName(roleName)) {

            throw new NotUniqueException(roleName + " has been already added. So the role cannot be added again.");
        } else{

            final Role role = new Role();
            role.setName(roleName);

            roleRepository.save(role);

            return new BaseResponse(true, "Role was added");
        }
    }

}
