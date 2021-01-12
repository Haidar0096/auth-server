package com.nlimits.authserver.application.user.application.port.input;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Contract for querying the User by Email
 */
public interface FindUserByEmailInputPort {

    Optional<FindUserByEmailInputPortResult> findUserByEmail(FindUserByEmailInputPortCommand command);

    /**
     * Input model for the FindUserByEmailInputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class FindUserByEmailInputPortCommand extends SelfValidating<FindUserByEmailInputPortCommand> {
        @Valid
        Email email;

        public FindUserByEmailInputPortCommand(Email email) {
            this.email = email;
            this.validateSelf();
        }
    }

    /**
     * Output model for the FindUserByEmailInputPort
     */
    @Value
    class FindUserByEmailInputPortResult {
        UserId userId;
        Username username;
        Email email;
    }
}

