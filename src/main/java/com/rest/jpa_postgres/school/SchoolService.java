package com.rest.jpa_postgres.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolDTO> findAllSchools() {
        return schoolRepository
                .findAll()
                .stream()
                .map(schoolMapper::toSchoolDTO)
                .collect(Collectors.toList());
    }

    public SchoolDTO addSchool( SchoolDTO schoolDTO) {
        var school = schoolMapper.toSchool(schoolDTO);
        School savedSchool = schoolRepository.save(school);

        return schoolMapper.toSchoolDTO(savedSchool);
    }
}
