package com.example.tourism_management_system.model.pojos;

public class ForgotPassword {
    
    private String login;
    
    public ForgotPassword () {}
    
    public ForgotPassword (String login) {
        this.login = login;
    }
    
    public String getLogin () {
        return login;
    }
    
    public void setLogin (String login) {
        this.login = login;
    }
}