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

    Optional<LoadUserByEmailResult> loadUserByEmail(LoadUserByEmailCommand command);


    /**
     * Input model for the LoadUserByEmailOutputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadUserByEmailCommand extends SelfValidating<LoadUserByEmailCommand> {
        @Valid
        Email email;

        public LoadUserByEmailCommand(Email email) {
            this.email = email;
            this.validateSelf();
        }
    }

    /**
     * Output model for the LoadUserByEmailOutputPort
     */
    @Value
    class LoadUserByEmailResult {
        UserId userId;
        Username username;
        Email email;
    }
}

