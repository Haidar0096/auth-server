package com.nlimits.authserver.application.user.application.port.input;

import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;
import java.util.Optional;

import static com.nlimits.authserver.application.user.domain.User.*;

/**
 * Contract for querying the User by Id
 */
public interface FindUserByIdInputPort {

    Optional<FindUserByIdInputPortResult> findUserById(FindUserByIdInputPortCommand command);

    /**
     * Input model for the FindUserByIdInputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class FindUserByIdInputPortCommand extends SelfValidating<FindUserByIdInputPortCommand> {
        @Valid
        UserId userId;

        public FindUserByIdInputPortCommand(UserId userId) {
            this.userId = userId;
            this.validateSelf();
        }
    }

    /**
     * Output model for the FindUserByIdInputPort
     */
    @Value
    class FindUserByIdInputPortResult {
        UserId userId;
        Username username;
        Email email;
    }
}

