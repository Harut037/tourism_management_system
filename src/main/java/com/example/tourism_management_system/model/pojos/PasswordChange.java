package com.example.tourism_management_system.model.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChange {
    
    private SignIn signIn;
    private String new1;
    private String new2;

}