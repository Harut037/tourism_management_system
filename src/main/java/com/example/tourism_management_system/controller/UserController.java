package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( value = "/User" )
public class UserController {

    private UserService userService;
    
    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    public UserController () {}
    
    @PostMapping ( value = "/signup" )
    public @ResponseBody ResponseEntity <User> signUp (@Valid @RequestBody @NonNull User user) {
        return userService.signup(user);
    }
    
    @GetMapping ( value = "/signin" )
    public @ResponseBody ResponseEntity <User> signIn (@RequestBody @NonNull SignIn signIn) {
        return userService.signIn(signIn.getLogin(), signIn.getPassword(), userService.loginType(signIn.getLogin()));
    }
    
    @PutMapping ( value = "/editinfo" )
    public @ResponseBody ResponseEntity <User> editInfo (@Valid @RequestBody @NonNull User user) {
        return userService.editInfo(user);
    }
    
    @PutMapping ( value = "/changepassword" )
    public @ResponseBody ResponseEntity <User> changePassword (@RequestBody @NonNull PasswordChange passwordChange) {
        if (passwordChange.getNew1().equals(passwordChange.getNew2())) {
            return userService.passwordChange(passwordChange.getSignIn(), passwordChange.getNew1(), userService.loginType(passwordChange.getSignIn().getLogin()));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }
    
    @PutMapping ( value = "/changeforgottedpassword" )
    public @ResponseBody ResponseEntity <User> changeForgottedPassword (@RequestBody @NonNull ForgottedPasswordChange forgottedPasswordChange) {
        if (forgottedPasswordChange.getNew1().equals(forgottedPasswordChange.getNew2())) {
            return userService.forgottedPasswordChange(forgottedPasswordChange.getLogin(), forgottedPasswordChange.getNew1(), userService.loginType(forgottedPasswordChange.getLogin()));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }
    
    @GetMapping ( value = "/forgotpassword" )
    public @ResponseBody ResponseEntity <User> forgotPassword (@RequestBody @NonNull ForgotPassword forgotPassword) {
        return userService.forgotPassword(forgotPassword.getLogin(), userService.loginType(forgotPassword.getLogin()));
    }
}
