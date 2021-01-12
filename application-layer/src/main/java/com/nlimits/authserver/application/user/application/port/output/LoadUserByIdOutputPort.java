package com.nlimits.authserver.application.user.application.port.output;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Contract for loading the User by Id
 */
public interface LoadUserByIdOutputPort {
    Optional<LoadUserByIdOutputPortResult> loadUserById(LoadUserByIdOutputPortCommand command);

    /**
     * Input model for the LoadUserByIdOutputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadUserByIdOutputPortCommand extends SelfValidating<LoadUserByIdOutputPortCommand> {
        @Valid
        UserId userId;

        public LoadUserByIdOutputPortCommand(UserId userId) {
            this.userId = userId;
            this.validateSelf();
        }
    }

    /**
     * Output model for the LoadUserByIdOutputPort
     */
    @Value
    class LoadUserByIdOutputPortResult {
        UserId userId;
        Username username;
        Email email;
    }
}

