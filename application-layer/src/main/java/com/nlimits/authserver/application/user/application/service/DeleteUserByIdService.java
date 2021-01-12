package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.DeleteUserByIdInputPort;
import com.nlimits.authserver.application.user.application.port.output.DeleteUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdOutputPortCommand;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdOutputPortResult;
import com.nlimits.authserver.application.user.application.service.exception.ErrorCode;
import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * This class provides an implementation of the DeleteUserByIdInputPort
 */
@InputPortImpl
@RequiredArgsConstructor
class DeleteUserByIdService implements DeleteUserByIdInputPort {

    private final DeleteUserByIdOutputPort deleteUserByIdOutputPort;
    private final LoadUserByIdOutputPort loadUserByIdOutputPort;

    @Override
    public void deleteUserById(DeleteUserByIdInputPortCommand command) {
        Optional<LoadUserByIdOutputPortResult> loadUserByIdResultOptional =
                loadUserByIdOutputPort.loadUserById(new LoadUserByIdOutputPortCommand(command.getUserId()));
        loadUserByIdResultOptional.orElseThrow(
                () -> new UserManagementException(ErrorCode.USER_DOES_NOT_EXIST)
        );
        deleteUserByIdOutputPort.deleteUserById(new DeleteUserByIdOutputPort.DeleteUserByIdOutputPortCommand(command.getUserId()));
    }
}

