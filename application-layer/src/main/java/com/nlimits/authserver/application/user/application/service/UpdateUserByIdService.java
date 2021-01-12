package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.UpdateUserByIdInputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.port.output.UpdateUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.service.exception.ErrorCode;
import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdCommand;
import static com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdResult;

/**
 * This class provides an implementation of the UpdateUserByIdInputPort
 */
@InputPortImpl
@RequiredArgsConstructor
class UpdateUserByIdService implements UpdateUserByIdInputPort {

    private final UpdateUserByIdOutputPort updateUserByIdOutputPort;
    private final LoadUserByIdOutputPort loadUserByIdOutputPort;

    @Override
    public void updateUserById(UpdateUserByIdCommand command) {
        Optional<LoadUserByIdResult> loadUserByIdResultOptional =
                loadUserByIdOutputPort.loadUserById(new LoadUserByIdCommand(command.getUserId()));
        loadUserByIdResultOptional.orElseThrow(() -> new UserManagementException(ErrorCode.USER_DOES_NOT_EXIST));
        updateUserByIdOutputPort.updateUserById(
                new UpdateUserByIdOutputPort.UpdateUserByIdCommand(
                        command.getUserId(),
                        command.getUsernameOptional(),
                        command.getPasswordOptional(),
                        command.getEmailOptional()));
    }
}

//todo write documentation for this class