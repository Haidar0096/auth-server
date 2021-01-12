package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.UpdateUserByIdInputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.port.output.UpdateUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.service.exception.ErrorCode;
import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdOutputPortCommand;
import static com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdOutputPortResult;

/**
 * This class provides an implementation of the UpdateUserByIdInputPort
 */
@InputPortImpl
@RequiredArgsConstructor
class UpdateUserByIdService implements UpdateUserByIdInputPort {

    private final UpdateUserByIdOutputPort updateUserByIdOutputPort;
    private final LoadUserByIdOutputPort loadUserByIdOutputPort;

    @Override
    public void updateUserById(UpdateUserByIdInputPortCommand command) {
        Optional<LoadUserByIdOutputPortResult> loadUserByIdResultOptional =
                loadUserByIdOutputPort.loadUserById(new LoadUserByIdOutputPortCommand(command.getUserId()));
        loadUserByIdResultOptional.orElseThrow(() -> new UserManagementException(ErrorCode.USER_DOES_NOT_EXIST));
        updateUserByIdOutputPort.updateUserById(
                new UpdateUserByIdOutputPort.UpdateUserByIdOutputPortCommand(
                        command.getUserId(),
                        command.getUsernameOptional(),
                        command.getPasswordOptional(),
                        command.getEmailOptional()));
    }
}

//todo write documentation for this class