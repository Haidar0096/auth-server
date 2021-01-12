package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.FindUserByEmailInputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailOutputPortCommand;
import static com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailOutputPortResult;

/**
 * This class provides an implementation of the FindUserByEmailInputPort
 */
@InputPortImpl
@RequiredArgsConstructor
 class FindUserByEmailService implements FindUserByEmailInputPort {

    private final LoadUserByEmailOutputPort loadUserByEmailOutputPort;

    @Override
    public Optional<FindUserByEmailInputPortResult> findUserByEmail(FindUserByEmailInputPortCommand command) {
        Optional<LoadUserByEmailOutputPortResult> loadUserByEmailResultOptional =
                loadUserByEmailOutputPort.loadUserByEmail(
                        new LoadUserByEmailOutputPortCommand(command.getEmail())
                );
        return loadUserByEmailResultOptional.map(
                loadUserByEmailResult ->
                        new FindUserByEmailInputPortResult(
                                loadUserByEmailResult.getUserId(),
                                loadUserByEmailResult.getUsername(),
                                loadUserByEmailResult.getEmail()
                        )
        );
    }
}

