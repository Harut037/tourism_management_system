package com.example.tourism_management_system.model.pojos;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor ( force = true )
@AllArgsConstructor
public class SignUpUser {
    
    @NonNull
    @Pattern (regexp = "[A-Z][a-z]+", message = "Invalid First Name Pattern")
    private String    firstName;
    @NonNull
    @Pattern(regexp = "[A-Z][a-z]+", message = "Invalid Last Name Pattern")
    private String    lastName;
    //TODO: validation
    @NonNull
    private LocalDate birthDate;
    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email Pattern")
    private String    email;
    @NonNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,20}$", message = "Invalid Password Pattern")
    private String password;
    @NonNull
    @Pattern(regexp = "\\+374\\d{8}", message = "Invalid Phone Number Pattern")
    private String            phoneNumber;
}