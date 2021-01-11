package com.nlimits.authserver.application.user.application.port.output;

import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.Password;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;

public interface PersistUserOutputPort {

    PersistUserResult persistUser(PersistUserCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class PersistUserCommand extends SelfValidating<PersistUserCommand> {
        @Valid
        Username username;

        @Valid
        Password password;

        @Valid
        Email email;

        public PersistUserCommand(Username username, Password password, Email email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.validateSelf();
        }
    }

    @Value
    class PersistUserResult {
        UserId userId;
    }
}

//todo write documentation for this class
