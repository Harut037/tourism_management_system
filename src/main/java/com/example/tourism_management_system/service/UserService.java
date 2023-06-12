package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.pojos.Review;
import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.SignIn;

@Service
public interface UserService {

    User signup(final User user);

    User signIn(final String login, final String password, final int loginChoice);

    User editInfo(final User user);

    boolean contains(final String string, final char symbol);

    User forgotPassword(final String login, final int loginChoice);

    int loginType(final String login);

    User forgottedPasswordChange(final String login, final String password, final int loginChoice);

    User passwordChange(SignIn signIn, String password, int loginChoice);

    UserInTour bookTour(UserInTour userInTour);

    UserInTour editTour(UserInTour userInTour);

    User cancelTour(UserInTour userInTour);

    Review leaveReview(ReviewEntity reviewEntity);

}