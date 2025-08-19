# 🏩 School Management API (Spring Boot + PostgreSQL + JPA)

A backend mini project demonstrating **Spring Boot + JPA + DTO patterns** for managing schools, students, and student profiles with relational mapping. Designed with a **layered architecture**:
**Controller → Service → Mapper → DTO → Entity → Repository**

---

## 🚀 Features

* Manage **Schools, Students, and Student Profiles**
* **Entity Relationships:**

  * One-to-Many: A school can have many students
  * One-to-One: A student has a single profile
* CRUD operations for all entities
* **DTO & Mapper Layer** for clean request/response handling
* PostgreSQL for persistence

---

## 🛠 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Data JPA (Hibernate)**
* **PostgreSQL**
* **Maven**
* **Lombok**

---

## 📌 Database Schema

* **School** (`id`, `name`, `address`, …)
* **Student** (`id`, `name`, `email`, `school_id`)
* **StudentProfile** (`id`, `dob`, `address`, `phone`, `student_id`)

---

## 📌 API Endpoints (Examples)

### Schools

* `POST /schools` → Create new school
* `GET /schools/{id}` → Get school by ID (with students)
* `GET /schools` → List all schools

### Students

* `POST /students` → Add student to a school
* `GET /students/{id}` → Get student details (with profile)
* `PUT /students/{id}` → Update student info
* `DELETE /students/{id}` → Delete student

### Student Profiles

* `POST /profiles` → Add profile for a student
* `GET /profiles/{id}` → Get profile by ID
* `PUT /profiles/{id}` → Update student profile

---

## ⚡ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/yugaldekate/School-Management-API.git
   cd School-Management-API
   ```

2. Configure **PostgreSQL** in `application.yml`:

   ```yaml
    spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/my-database
      username: postgres
      password: password
      driver-class-name: org.postgresql.Driver
    jpa:
      hibernate:
        ddl-auto: update
      show-sql: true
      properties:
        hibernate:
          format_sql: true
      database: postgresql
      database-platform: org.hibernate.dialect.PostgreSQLDialect
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. Test APIs using **Postman** or **cURL**.

---

## 📖 Project Structure

```
src/main/java/com/rest/jpa_postgres
 ├── school
 │   ├── School.java
 │   ├── SchoolController.java
 │   ├── SchoolDTO.java
 │   ├── SchoolMapper.java
 │
 ├── student
 │   ├── StudentController.java
 │   ├── StudentDTO.java
 │   ├── StudentMapper.java
 │   ├── StudentRepository.java
 │   ├── StudentResponseDTO.java
 │   ├── StudentService.java
 │
 ├── studentProfile
 │   ├── StudentProfile.java
```

