package com.rest.jpa_postgres.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/") // Endpoint = http://localhost:8080/api/v1/students/
    @ResponseStatus(HttpStatus.OK)
    public String sayHello() {
        return "Hello from Spring server 1";
    }

    @GetMapping("/students") // Endpoint = http://localhost:8080/api/v1/students/students
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{student-id}") // Endpoint = http://localhost:8080/api/v1/students/students/{student-id}
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO findStudentById( @PathVariable("student-id") Integer Id) {
        return studentService.findStudentById(Id);
    }

    @GetMapping("/students/search/{student-name}") // Endpoint = http://localhost:8080/api/v1/students/students/search/{student-name}
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> findStudentByFirstName( @PathVariable("student-name") String name) {
        return studentService.findStudentByFirstName(name);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO addStudent( @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @PostMapping("/students/many/") // Endpoint = http://localhost:8080/api/v1/students/students/many/
    @ResponseStatus(HttpStatus.CREATED)
    public List<StudentResponseDTO> addStudents(@RequestBody List<Student> students) {
        return studentService.addStudents(students);
    }

    @DeleteMapping("/students/{student-id}") // Endpoint = http://localhost:8080/api/v1/students/students/{student-id}
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById( @PathVariable("student-id") Integer Id) {
        studentService.deleteStudentById(Id);
    }
    
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK) // http://localhost:8080/posts?firstName=paramName&lastName=paramLName
    public String reqParam(@RequestParam("firstName") String userName, @RequestParam("lastName") String lName){
        return "Hello from pathVariable: " + userName + " " + lName;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();

        exp.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField(); // Get the field name that caused the error with type casting
            String errorMessage = error.getDefaultMessage(); // Get the error message
            
            errors.put(fieldName, errorMessage); // Add the field and its error message to the map
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
