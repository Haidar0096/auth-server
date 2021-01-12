package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.FindUserByIdInputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdOutputPortCommand;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdOutputPortResult;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * This class provides an implementation of the FindUserByIdInputPort
 */
@InputPortImpl
@RequiredArgsConstructor
class FindUserByIdService implements FindUserByIdInputPort {

    private final LoadUserByIdOutputPort loadUserByIdOutputPort;

    @Override
    public Optional<FindUserByIdInputPortResult> findUserById(FindUserByIdInputPortCommand command) {
        Optional<LoadUserByIdOutputPortResult> loadedUserResultOptional = loadUserByIdOutputPort.loadUserById(
                new LoadUserByIdOutputPortCommand(command.getUserId())
        );
        return loadedUserResultOptional.map(
                loadUserByIdResult ->
                        new FindUserByIdInputPortResult(
                                loadUserByIdResult.getUserId(),
                                loadUserByIdResult.getUsername(),
                                loadUserByIdResult.getEmail()
                        )
        );
    }
}

