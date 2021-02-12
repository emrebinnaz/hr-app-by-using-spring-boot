package com.example.hrapp.hrapp.Controller;

import com.example.hrapp.hrapp.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_HR_MANAGER')")
public class FileController {

    private final FileService fileService;

    @GetMapping("/getResumeBy/{jobApplicationId}")
    public ResponseEntity<Resource> getResumeOfJobApplication(
            @PathVariable("jobApplicationId") final String jobApplicationId) {

        final byte[] resume = fileService.getResumeOfJobApplication(jobApplicationId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                        jobApplicationId + "\"")
                .body(new ByteArrayResource(resume));
    }
}
