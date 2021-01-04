package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;

import static com.nlimits.authserver.application.user.domain.User.Password;

public class TestUtils {
    static final UserId validUserId = new UserId(1L);
    static final UserId invalidUserId = new UserId(-1L);

    static final Username validUsername = new Username("user");
    static final Username invalidUsername = new Username("");

    static final Password validPassword = new Password("123456");
    static final Password invalidPassword = new Password("");

    static final Email validEmail = new Email("email@domain.com");
    static final Email invalidEmail = new Email("");
}
