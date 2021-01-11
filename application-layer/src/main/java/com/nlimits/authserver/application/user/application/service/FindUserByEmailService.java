package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.FindUserByEmailInputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailCommand;
import static com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailResult;

@InputPortImpl
@RequiredArgsConstructor
 class FindUserByEmailService implements FindUserByEmailInputPort {

    private final LoadUserByEmailOutputPort loadUserByEmailOutputPort;

    @Override
    public Optional<FindUserByEmailResult> findUserByEmail(FindUserByEmailCommand command) {
        Optional<LoadUserByEmailResult> loadUserByEmailResultOptional =
                loadUserByEmailOutputPort.loadUserByEmail(
                        new LoadUserByEmailCommand(command.getEmail())
                );
        return loadUserByEmailResultOptional.map(
                loadUserByEmailResult ->
                        new FindUserByEmailResult(
                                loadUserByEmailResult.getUserId(),
                                loadUserByEmailResult.getUsername(),
                                loadUserByEmailResult.getEmail()
                        )
        );
    }
}

//todo write documentation for this class