package com.nlimits.authserver.application.user.application.port.out;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;
import java.util.Optional;

public interface LoadUserByIdOutputPort {
    Optional<LoadUserByIdResult> loadUserById(LoadUserByIdCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadUserByIdCommand extends SelfValidating<LoadUserByIdCommand> {
        @Valid
        UserId userId;

        public LoadUserByIdCommand(UserId userId) {
            this.userId = userId;
            this.validateSelf();
        }
    }

    @Value
    class LoadUserByIdResult {
        UserId userId;
        Username username;
        Email email;
    }
}
