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

    Optional<FindUserByEmailResult> findUserByEmail(FindUserByEmailCommand command);

    /**
     * Input model for the FindUserByEmailInputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class FindUserByEmailCommand extends SelfValidating<FindUserByEmailCommand> {
        @Valid
        Email email;

        public FindUserByEmailCommand(Email email) {
            this.email = email;
            this.validateSelf();
        }
    }

    /**
     * Output model for the FindUserByEmailInputPort
     */
    @Value
    class FindUserByEmailResult {
        UserId userId;
        Username username;
        Email email;
    }
}

