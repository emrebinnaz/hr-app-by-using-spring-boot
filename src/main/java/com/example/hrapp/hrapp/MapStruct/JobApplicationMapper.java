package com.example.hrapp.hrapp.MapStruct;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.example.hrapp.hrapp.Domain.JobApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper extends BaseMapper<JobApplicationDTO, JobApplication>{

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "applicantResume", ignore = true)
    JobApplication mapToEntity(JobApplicationDTO jobApplicationDTO);

    @Override
    @Mapping(target = "id", expression = "java(jobApplication.getId().toString())")
    JobApplicationDTO mapToDto(JobApplication jobApplication);

}
