package com.nlimits.authserver.application.user.application.port.input;

import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;

/**
 * Contract for deleting the User by id
 */
public interface DeleteUserByIdInputPort {

    void deleteUserById(DeleteUserByIdInputPortCommand command);

    /**
     * Input Model of the DeleteUserByIdInputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeleteUserByIdInputPortCommand extends SelfValidating<DeleteUserByIdInputPortCommand> {
        @Valid
        UserId userId;

        public DeleteUserByIdInputPortCommand(UserId userId) {
            this.userId = userId;
            this.validateSelf();
        }
    }
}

