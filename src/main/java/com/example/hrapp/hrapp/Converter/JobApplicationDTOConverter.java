package com.example.hrapp.hrapp.Converter;

import com.example.hrapp.hrapp.DTO.JobApplicationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobApplicationDTOConverter implements Converter<String, JobApplicationDTO> {

    private final ObjectMapper objectMapper;

    @SneakyThrows()
    @Override
    public JobApplicationDTO convert(final String source) {
        System.out.println(source);
        return objectMapper.readValue(source,JobApplicationDTO.class);
    }
}
