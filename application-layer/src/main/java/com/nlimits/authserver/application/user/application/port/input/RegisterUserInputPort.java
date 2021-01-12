package com.nlimits.authserver.application.user.application.port.input;

import com.nlimits.authserver.application.user.domain.User;
import com.nlimits.authserver.application.user.domain.User.Password;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;

/**
 * Contract for registering the user
 */
public interface RegisterUserInputPort {

    /**
     * @param command should contain the username, password, and email
     * @return id of the newly created user
     */
    RegisterUserInputPortResult registerUser(RegisterUserInputPortCommand command);


    /**
     * Input model for the RegisterUserInputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class RegisterUserInputPortCommand extends SelfValidating<RegisterUserInputPortCommand> {

        @Valid
        Username username;

        @Valid
        Password password;

        @Valid
        User.Email email;

        public RegisterUserInputPortCommand(Username username, Password password, User.Email email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.validateSelf();
        }
    }

    /**
     * Output model for the RegisterUserInputPort
     */
    @Value
    class RegisterUserInputPortResult {
        UserId userId;
    }
}

