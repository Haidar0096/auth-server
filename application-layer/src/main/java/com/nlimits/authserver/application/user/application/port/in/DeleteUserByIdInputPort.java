package com.nlimits.authserver.application.user.application.port.in;

import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;

public interface DeleteUserByIdInputPort {

    void deleteUserById(DeleteUserByIdCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeleteUserByIdCommand extends SelfValidating<DeleteUserByIdCommand> {
        @Valid
        UserId userId;

        public DeleteUserByIdCommand(UserId userId) {
            this.userId = userId;
            this.validateSelf();
        }
    }
}
