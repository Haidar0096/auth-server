package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.FindUserByIdInputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdCommand;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByIdOutputPort.LoadUserByIdResult;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@InputPortImpl
@RequiredArgsConstructor
class FindUserByIdService implements FindUserByIdInputPort {

    private final LoadUserByIdOutputPort loadUserByIdOutputPort;

    @Override
    public Optional<FindUserByIdResult> findUserById(FindUserByIdCommand command) {
        Optional<LoadUserByIdResult> loadedUserResultOptional = loadUserByIdOutputPort.loadUserById(
                new LoadUserByIdCommand(command.getUserId())
        );
        return loadedUserResultOptional.map(
                loadUserByIdResult ->
                        new FindUserByIdResult(
                                loadUserByIdResult.getUserId(),
                                loadUserByIdResult.getUsername(),
                                loadUserByIdResult.getEmail()
                        )
        );
    }
}

//todo write documentation for this class