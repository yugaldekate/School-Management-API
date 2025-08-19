package com.rest.jpa_postgres.student;

import com.rest.jpa_postgres.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO studentDTO) {
        if(studentDTO == null){
            throw new NullPointerException("The studentDTO should not be null");
        }

        // Create a new Student object and map the fields from StudentDTO
        var student = new Student();
        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setEmail(studentDTO.email());
        
        // Also, handle the schoolId by creating a new School object
        var school = new School();
        school.setId(studentDTO.schoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDTO toStudentResponseDTO(Student student){
        if(student == null){
            throw new NullPointerException("The student should not be null");
        }

        return new StudentResponseDTO(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
