package com.example.hrapp.hrapp.Util;

import com.example.hrapp.hrapp.Domain.Privilege;
import com.example.hrapp.hrapp.Domain.Role;
import com.example.hrapp.hrapp.Repository.PrivilegeRepository;
import com.example.hrapp.hrapp.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        //TODO: Make a good solution.

        final Privilege readJobPrivilege = createPrivilegeIfNotFound("READ_JOB_PRIVILEGE");
        final Privilege writeJobPrivilege = createPrivilegeIfNotFound("WRITE_JOB_PRIVILEGE");
        final Privilege updateJobPrivilege = createPrivilegeIfNotFound("UPDATE_JOB_PRIVILEGE");
        final Privilege deleteJobPrivilege = createPrivilegeIfNotFound("DELETE_JOB_PRIVILEGE");
        final Privilege applyJobPrivilege = createPrivilegeIfNotFound(("APPLY_JOB_PRIVILEGE")); // TODO: It might not be neccessary


        final List<Privilege> hrManagerPrivileges = new ArrayList<>(Arrays.asList(readJobPrivilege,
                writeJobPrivilege, updateJobPrivilege,deleteJobPrivilege));

        final List<Privilege> applicantPrivileges = new ArrayList<>(Arrays.asList(readJobPrivilege,applyJobPrivilege));

        createRoleIfNotFound("ROLE_HR_MANAGER", hrManagerPrivileges);
        createRoleIfNotFound("ROLE_APPLICANT", applicantPrivileges);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }


}
