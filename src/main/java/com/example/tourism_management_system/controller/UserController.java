package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( value = "/User" )
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

//    @GetMapping ( value = "/signIn" )
//    public @ResponseBody ResponseEntity <User> signIn (@RequestBody @NonNull SignIn signIn) {
//        return userService.signIn(signIn.getLogin(), signIn.getPassword(), userService.loginType(signIn.getLogin()));
//    }

    @GetMapping ( value = "/profile" )
    public String getProfile () {
        //return userService.signIn(signIn.getLogin(), signIn.getPassword(), userService.loginType(signIn.getLogin()));
        return "Hiiiii ";
    }

    @PutMapping ( value = "/editInfo" )
    public void editInfo (@Valid @RequestBody @NonNull User user) {
        return; //userService.editInfo(user);
    }

    @PutMapping ( value = "/changePassword" )
    public void changePassword (@RequestBody @NonNull PasswordChange passwordChange) {
        if (passwordChange.getNew1().equals(passwordChange.getNew2())) {
            return; //userService.passwordChange(passwordChange.getSignIn(), passwordChange.getNew1(), userService.loginType(passwordChange.getSignIn().getLogin()));
        } else {
            return; //new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }

    @PutMapping ( value = "/changeForgotPassword" )
    public void changeForgottedPassword (@RequestBody @NonNull ForgottedPasswordChange forgottedPasswordChange) {
        if (forgottedPasswordChange.getNew1().equals(forgottedPasswordChange.getNew2())) {
            return; //userService.forgottedPasswordChange(forgottedPasswordChange.getLogin(), forgottedPasswordChange.getNew1(), userService.loginType(forgottedPasswordChange.getLogin()));
        } else {
            return; //new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }

    @GetMapping ( value = "/forgotPassword" )
    public void forgotPassword (@RequestBody @NonNull ForgotPassword forgotPassword) {
        return; //userService.forgotPassword(forgotPassword.getLogin(), userService.loginType(forgotPassword.getLogin()));
    }
}