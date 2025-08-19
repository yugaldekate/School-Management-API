package com.rest.jpa_postgres.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rest.jpa_postgres.school.School;
import com.rest.jpa_postgres.studentProfile.StudentProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table (schema).
@Table(name = "students_data")
public class Student {
    @Id @GeneratedValue
    private Integer id;

    @Column(length = 30)
    @NotEmpty(message = "Firstname required")
    private String firstName;

    @Column(length = 30)
    @NotEmpty(message = "Lastname required")
    private String lastName;

    @Column(length = 50, unique = true, nullable = false)
    @NotEmpty(message = "Email required")
    private String email;

    @Column(nullable = false)
    @Min(value = 5, message = "Age must be greater than or equal to 5")
    private int age;

    // Default constructor
    public Student() {
    }

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToOne( mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    @ManyToOne
    @JoinColumn( name = "school_id" )
    @JsonBackReference
    private School school;

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
