package com.rest.jpa_postgres.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> findAllSchools() {
        return schoolService.findAllSchools();
    }

    @PostMapping("/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDTO addSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.addSchool(schoolDTO);
    }

}
