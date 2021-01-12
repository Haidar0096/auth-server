package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.RegisterUserInputPort;
import com.nlimits.authserver.application.user.application.service.exception.ErrorCode;
import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort;
import com.nlimits.authserver.application.user.application.port.output.PersistUserOutputPort;
import com.nlimits.authserver.common.annotation.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailOutputPortCommand;
import static com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailOutputPortResult;
import static com.nlimits.authserver.application.user.application.port.output.PersistUserOutputPort.PersistUserOutputPortCommand;
import static com.nlimits.authserver.application.user.application.port.output.PersistUserOutputPort.PersistUserOutputPortResult;

/**
 * This class provides an implementation of the RegisterUserInputPort
 */
@InputPortImpl
@RequiredArgsConstructor
class RegisterUserService implements RegisterUserInputPort {

    private final PersistUserOutputPort persistUserOutputPort;
    private final LoadUserByEmailOutputPort loadUserByEmailOutputPort;

    @Override
    public RegisterUserInputPortResult registerUser(RegisterUserInputPortCommand registerUserInputPortCommand) {
        Optional<LoadUserByEmailOutputPortResult> possibleExistingUserOptional = loadUserByEmailOutputPort.loadUserByEmail(
                new LoadUserByEmailOutputPortCommand(
                        registerUserInputPortCommand.getEmail()
                )
        );

        possibleExistingUserOptional.ifPresent((user) -> {
            throw new UserManagementException(ErrorCode.USER_ALREADY_EXISTS);
        });

        PersistUserOutputPortResult persistUserOutputPortResult = persistUserOutputPort.persistUser(
                new PersistUserOutputPortCommand(
                        registerUserInputPortCommand.getUsername(),
                        registerUserInputPortCommand.getPassword(),
                        registerUserInputPortCommand.getEmail()
                )
        );
        return new RegisterUserInputPortResult(
                persistUserOutputPortResult.getUserId()
        );
    }
}

