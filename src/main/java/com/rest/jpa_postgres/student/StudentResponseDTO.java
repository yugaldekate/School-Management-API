package com.rest.jpa_postgres.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentResponseDTO(
    @NotEmpty(message = "Firstname required")
    String firstName,

    @NotEmpty(message = "Lastname required")
    String lastName, 

    @NotEmpty(message = "Email required") @Email
    String email
) {

}
