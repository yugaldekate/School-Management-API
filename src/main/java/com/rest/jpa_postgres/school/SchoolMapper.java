package com.rest.jpa_postgres.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public SchoolDTO toSchoolDTO(School school) {
        if (school == null) {
            throw new NullPointerException("The school should not be null");
        }

        return new SchoolDTO(school.getName());
    }

    public School toSchool(SchoolDTO schoolDTO) {
        if (schoolDTO == null) {
            throw new NullPointerException("The schoolDTO should not be null");
        }

        return new School(schoolDTO.schoolName());
    }
}
