package com.nlimits.authserver.application.user.domain;

import com.nlimits.authserver.application.user.domain.annotation.EmailString;
import com.nlimits.authserver.application.user.domain.annotation.NumericId;
import com.nlimits.authserver.application.user.domain.annotation.PasswordString;
import com.nlimits.authserver.application.user.domain.annotation.UsernameString;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * This class models a user.
 * It is the responsibility of the one who creates an object of this class to invoke the validation logic to ensure a valid state for the corresponding object.
 * After creation, thus, the object is guaranteed to keep its initial (valid) state.
 */
@Value
public class User {

    @Valid
    UserId userId;

    @Valid
    Username username;

    @Valid
    Password password;

    @Valid
    Email email;

    /**
     * This class models a numeric user id.
     * Consumers of this class must take care of invoking the validation logic to guarantee a valid state of the object.
     * Objects of this class are immutable. Once created, they are guaranteed to keep their initial state.
     */
    @Value
    public static class UserId {

        @NotNull
        @NumericId
        Long value;
    }


    /**
     * This class models a password.
     * Consumers of this class must take care of invoking the validation logic to guarantee a valid state of the object.
     * Objects of this class are immutable. Once created, they are guaranteed to keep their initial state.
     */
    @Value
    public static class Password {

        @NotNull
        @PasswordString
        String value;
    }

    /**
     * This class models an email address.
     * Consumers of this class must take care of invoking the validation logic to guarantee a valid state of the object.
     * Objects of this class are immutable. Once created, they are guaranteed to keep their initial state.
     */
    @Value
    public static class Email {

        @NotNull
        @EmailString
        String value;
    }

    /**
     * This class models a username.
     * Consumers of this class must take care of invoking the validation logic to guarantee a valid state of the object.
     * Objects of this class are immutable. Once created, they are guaranteed to keep their initial state.
     */
    @Value
    public static class Username {

        @NotNull
        @UsernameString
        String value;
    }
}
