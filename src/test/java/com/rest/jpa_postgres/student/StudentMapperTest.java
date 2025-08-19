package com.rest.jpa_postgres.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach // This method will run before each test
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDTOToStudent() {

        StudentDTO studentDTO = new StudentDTO("Yugal", "Dekate", "yugaldekate@gmail.com", 1);

        Student student = studentMapper.toStudent(studentDTO);

        assertEquals(studentDTO.firstName(), student.getFirstName());
        assertEquals(studentDTO.lastName(), student.getLastName());
        assertEquals(studentDTO.email(), student.getEmail());

        assertNotNull(student.getSchool());
        assertEquals(studentDTO.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDTO(){
        Student student = new Student("Yugal", "Dekate", "yugaldekate@gmail.com", 1);

        StudentResponseDTO studentResponseDTO = studentMapper.toStudentResponseDTO(student);

        assertEquals(student.getFirstName(), studentResponseDTO.firstName());
        assertEquals(student.getLastName(), studentResponseDTO.lastName());
        assertEquals(student.getEmail(), studentResponseDTO.email());
    }

    @Test
    public void should_throw_Null_Pointer_Exception_when_StudentDTO_is_null(){
        var exp = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));

        assertEquals("The studentDTO should not be null", exp.getMessage());
    }

    @Test
    public void should_throw_Null_Pointer_Exception_when_Student_is_null(){
        var exp = assertThrows(NullPointerException.class, () -> studentMapper.toStudentResponseDTO(null));

        assertEquals("The student should not be null", exp.getMessage());
    }

}