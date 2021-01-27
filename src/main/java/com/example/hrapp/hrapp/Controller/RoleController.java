package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.Response.BaseResponse;
import com.example.hrapp.hrapp.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/addRole/{roleName}")
    public ResponseEntity<BaseResponse> addRole(@PathVariable final String roleName) {

        return ResponseEntity.ok(roleService.addRole(roleName));
    }
}
