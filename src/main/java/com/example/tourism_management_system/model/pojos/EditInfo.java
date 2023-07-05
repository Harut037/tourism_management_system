package com.example.tourism_management_system.model.pojos;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditInfo {

    @Pattern(regexp = "[A-Z][a-z]+")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String lastName;
    //TODO validation
    private LocalDate birthDate;
}