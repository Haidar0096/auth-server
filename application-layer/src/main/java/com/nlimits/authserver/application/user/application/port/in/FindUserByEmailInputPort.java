package com.nlimits.authserver.application.user.application.port.in;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;
import java.util.Optional;

public interface FindUserByEmailInputPort {

    Optional<FindUserByEmailResult> findUserByEmail(FindUserByEmailCommand command);

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

    @Value
    class FindUserByEmailResult {
        UserId userId;
        Username username;
        Email email;
    }
}

//todo write documentation for this class