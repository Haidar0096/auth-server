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
 * Contract for loading the User By email
 */
public interface LoadUserByEmailOutputPort {

    Optional<LoadUserByEmailOutputPortResult> loadUserByEmail(LoadUserByEmailOutputPortCommand command);


    /**
     * Input model for the LoadUserByEmailOutputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadUserByEmailOutputPortCommand extends SelfValidating<LoadUserByEmailOutputPortCommand> {
        @Valid
        Email email;

        public LoadUserByEmailOutputPortCommand(Email email) {
            this.email = email;
            this.validateSelf();
        }
    }

    /**
     * Output model for the LoadUserByEmailOutputPort
     */
    @Value
    class LoadUserByEmailOutputPortResult {
        UserId userId;
        Username username;
        Email email;
    }
}

