package com.rest.jpa_postgres.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository; // Repository for Student entities (interacts with the database)
    private final StudentMapper studentMapper; // Mapper to convert between Student and DTOs


    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDTO> findAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO findStudentById( Integer Id) {
        return studentRepository.findById(Id)
                .map(studentMapper::toStudentResponseDTO)
                .orElse(null);
    }

    public List<StudentResponseDTO> findStudentByFirstName( String name) {
        return studentRepository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO saveStudent(StudentDTO studentDTO) {
        var student = studentMapper.toStudent(studentDTO);
        var savedStudent = studentRepository.save(student);

        return studentMapper.toStudentResponseDTO(savedStudent);
    }

    public List<StudentResponseDTO> addStudents( List<Student> students) {
        return studentRepository
                .saveAll(students)
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteStudentById( Integer Id) {
        studentRepository.deleteById(Id);
    }
}
