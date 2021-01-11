package com.nlimits.authserver.application.user.application.port.in;

import com.nlimits.authserver.application.user.domain.User;
import com.nlimits.authserver.application.user.domain.User.Password;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;

public interface RegisterUserInputPort {

    /**
     * @param command should contain the username, password, and email
     * @return id of the newly created user
     */
    RegisterUserResult registerUser(RegisterUserCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class RegisterUserCommand extends SelfValidating<RegisterUserCommand> {

        @Valid
        Username username;

        @Valid
        Password password;

        @Valid
        User.Email email;

        public RegisterUserCommand(Username username, Password password, User.Email email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.validateSelf();
        }
    }

    @Value
    class RegisterUserResult {
        UserId userId;
    }
}

//todo write documentation for this class
