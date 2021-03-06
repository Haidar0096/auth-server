package com.nlimits.authserver.application.user.application.port.output;

import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.Valid;

/**
 * Contract for deleting the User by Id
 */
public interface DeleteUserByIdOutputPort {

    void deleteUserById(DeleteUserByIdOutputPortCommand command);

    /**
     * Input model of the DeleteUserByIdOutputPort
     */
    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeleteUserByIdOutputPortCommand extends SelfValidating<DeleteUserByIdOutputPortCommand> {

        @Valid UserId userId;

        public DeleteUserByIdOutputPortCommand(UserId userId) {
            this.userId = userId;
            this.validateSelf();
        }
    }

}

