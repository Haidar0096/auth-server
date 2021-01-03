package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort;
import com.nlimits.authserver.application.user.application.service.exception.ErrorCode;
import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import com.nlimits.authserver.application.user.application.port.out.LoadUserByEmailOutputPort;
import com.nlimits.authserver.application.user.application.port.out.PersistUserOutputPort;
import com.nlimits.authserver.common.InputPortImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.nlimits.authserver.application.user.application.port.out.LoadUserByEmailOutputPort.LoadUserByEmailCommand;
import static com.nlimits.authserver.application.user.application.port.out.LoadUserByEmailOutputPort.LoadUserByEmailResult;
import static com.nlimits.authserver.application.user.application.port.out.PersistUserOutputPort.PersistUserCommand;
import static com.nlimits.authserver.application.user.application.port.out.PersistUserOutputPort.PersistUserResult;

@InputPortImpl
@RequiredArgsConstructor
class RegisterUserService implements RegisterUserInputPort {

    private final PersistUserOutputPort persistUserOutputPort;
    private final LoadUserByEmailOutputPort loadUserByEmailOutputPort;

    @Override
    public RegisterUserResult registerUser(RegisterUserCommand registerUserCommand) {
        Optional<LoadUserByEmailResult> possibleExistingUserOptional = loadUserByEmailOutputPort.loadUserByEmail(
                new LoadUserByEmailCommand(
                        registerUserCommand.getEmail()
                )
        );

        possibleExistingUserOptional.ifPresent((user) -> {
            throw new UserManagementException(ErrorCode.USER_ALREADY_EXISTS);
        });

        PersistUserResult persistUserResult = persistUserOutputPort.persistUser(
                new PersistUserCommand(
                        registerUserCommand.getUsername(),
                        registerUserCommand.getPassword(),
                        registerUserCommand.getEmail()
                )
        );
        return new RegisterUserResult(
                persistUserResult.getUserId()
        );
    }
}
