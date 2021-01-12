package com.nlimits.authserver.application.user.application.port.input;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.Password;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Contract for updating the User by Id
 */
public interface UpdateUserByIdInputPort {

    void updateUserById(UpdateUserByIdInputPortCommand command);

    /**
     * Input model for the UpdateUserByIdInputPort
     * Username, password, and email can be null, but id can't be.
     * However, if the 3 of them are null, the user will not be updated and you will receive success = true, as you can't update the user id, obviously.
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class UpdateUserByIdInputPortCommand extends SelfValidating<UpdateUserByIdInputPortCommand> {

        @Valid
        UserId userId;

        Optional<@Valid Username> usernameOptional;

        Optional<@Valid Password> passwordOptional;

        Optional<@Valid Email> emailOptional;

        public UpdateUserByIdInputPortCommand(UserId userId, Optional<Username> usernameOptional, Optional<Password> passwordOptional, Optional<Email> emailOptional) {
            this.userId = userId;
            this.usernameOptional = usernameOptional;
            this.passwordOptional = passwordOptional;
            this.emailOptional = emailOptional;
            this.validateSelf();
        }
    }
}

//todo check warnings on this class fields
