package com.example.tourism_management_system.model.pojos;

public class SignIn {

    private String login;
    private String password;

    public SignIn() {
    }

    public SignIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}