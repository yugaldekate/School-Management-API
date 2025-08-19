package com.rest.jpa_postgres.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks // Injecting mocks into the StudentService (because it depends on StudentRepository and StudentMapper)
    private StudentService studentService;

    @Mock // Mocking the StudentRepository ( because studentService wants it in constructor)
    private StudentRepository studentRepository;

    @Mock // Mocking the StudentMapper ( because studentService wants it in constructor)
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_sucessfully_save_a_student() {
        //Given
        Student student = new Student("Yugal", "Dekate", "yugaldekate@gmail.com", 1);
        StudentDTO studentDTO = new StudentDTO("Yugal", "Dekate", "yugaldekate@gmail.com", 1);

        //Mock the calls
        when(studentMapper.toStudent(studentDTO)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponseDTO(student)).thenReturn(new StudentResponseDTO("Yugal", "Dekate", "yugaldekate@gmail.com"));

        //When
        StudentResponseDTO responseDTO = studentService.saveStudent(studentDTO);

        //Then
        assertEquals(student.getFirstName(), responseDTO.firstName());
        assertEquals(student.getLastName(), responseDTO.lastName());
        assertEquals(student.getEmail(), responseDTO.email());

        // Verify that the methods were called for the expected number of times
        verify(studentMapper, times(1)).toStudent(studentDTO);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDTO(student);
    }

    @Test
    public void should_return_all_students(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Yugal", "Dekate", "yugaldekate@gmail.com", 1));

        //Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class))).thenReturn(new StudentResponseDTO("Yugal", "Dekate", "yugaldekate@gmail.com"));

        //When
        List<StudentResponseDTO> allResponseStudents = studentService.findAllStudents();

        //Then
        assertEquals(students.size(), allResponseStudents.size());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id(){
        //Given
        Integer studentId = 1;
        Student student = new Student("Yugal", "Dekate", "yugaldekate@gmail.com", 1);

        //Mock the calls
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(any(Student.class))).thenReturn(new StudentResponseDTO("Yugal", "Dekate", "yugaldekate@gmail.com"));

        //When
        StudentResponseDTO responseDTO = studentService.findStudentById(studentId);

        //Then
        assertEquals(student.getFirstName(), responseDTO.firstName());
        assertEquals(student.getLastName(), responseDTO.lastName());
        assertEquals(student.getEmail(), responseDTO.email());

        verify(studentRepository, times(1)).findById(studentId);
        verify(studentMapper, times(1)).toStudentResponseDTO(student);
    }

    @Test
    public void should_return_students_by_first_name(){
        //Given
        String firstName = "Yugal";
        List<Student> students = List.of(new Student("Yugal", "Dekate", "yugaldekate@gmail.com", 1));

        //Mock the calls
        when(studentRepository.findAllByFirstNameContaining(firstName)).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class))).thenReturn(new StudentResponseDTO("Yugal", "Dekate", "yugaldekate@gmail.com"));

        //When
        List<StudentResponseDTO> responseDTOList = studentService.findStudentByFirstName(firstName);

        //Then
        assertEquals(students.size(), responseDTOList.size());
        assertEquals("Yugal", responseDTOList.get(0).firstName());

        verify(studentRepository, times(1)).findAllByFirstNameContaining(firstName);
        verify(studentMapper, times(1)).toStudentResponseDTO(any(Student.class));
    }

    @Test
    public void should_add_multiple_students() {
        //Given
        List<Student> students = List.of(
                new Student("Yugal", "Dekate", "yugaldekate@gmail.com", 1),
                new Student("John", "Doe", "johndoe@gmail.com", 2)
        );

        //Mock the calls
        when(studentRepository.saveAll(students)).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("Yugal", "Dekate", "yugaldekate@gmail.com"))
                .thenReturn(new StudentResponseDTO("John", "Doe", "johndoe@gmail.com"));

        //When
        List<StudentResponseDTO> responseDTOList = studentService.addStudents(students);

        //Then
        assertEquals(students.size(), responseDTOList.size());

        verify(studentRepository, times(1)).saveAll(students);
        verify(studentMapper, times(2)).toStudentResponseDTO(any(Student.class));
    }

    @Test
    public void should_delete_student_by_id() {
        //Given
        Integer studentId = 1;

        //When
        studentService.deleteStudentById(studentId);

        //Then
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}
