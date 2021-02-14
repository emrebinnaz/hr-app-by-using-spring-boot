package com.example.hrapp.hrapp.MapStruct;

import com.example.hrapp.hrapp.DTO.JobDTO;
import com.example.hrapp.hrapp.Domain.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobMapper extends BaseMapper<JobDTO, Job> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hrManager", ignore = true)
    @Mapping(target = "jobApplications", ignore = true)
    Job mapToEntity(JobDTO jobDTO);

    @Override
    @Mapping(target = "id", expression = "java(job.getId().toString())")
    JobDTO mapToDto(Job job);
}
