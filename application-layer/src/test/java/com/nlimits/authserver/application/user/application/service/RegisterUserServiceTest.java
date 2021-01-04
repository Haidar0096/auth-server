package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserCommand;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserResult;
import com.nlimits.authserver.application.user.application.port.out.LoadUserByEmailOutputPort;
import com.nlimits.authserver.application.user.application.port.out.LoadUserByEmailOutputPort.LoadUserByEmailCommand;
import com.nlimits.authserver.application.user.application.port.out.LoadUserByEmailOutputPort.LoadUserByEmailResult;
import com.nlimits.authserver.application.user.application.port.out.PersistUserOutputPort;
import com.nlimits.authserver.application.user.application.port.out.PersistUserOutputPort.PersistUserCommand;
import com.nlimits.authserver.application.user.application.port.out.PersistUserOutputPort.PersistUserResult;
import com.nlimits.authserver.application.user.application.service.exception.ErrorCode;
import com.nlimits.authserver.application.user.application.service.exception.UserManagementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static com.nlimits.authserver.application.user.application.service.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class RegisterUserServiceTest {

    @InjectMocks
    private RegisterUserService registerUserService;

    @Mock
    private LoadUserByEmailOutputPort mockLoadUserByEmailOutputPort;

    @Mock
    PersistUserOutputPort mockPersistUserOutputPort;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @DisplayName("Success Tests")
    class SuccessTests {

        @Test
        @DisplayName("should return userId when successful")
        void should_return_UserId_when_successful() {
            //arrange
            RegisterUserCommand registerUserCommand = new RegisterUserCommand(validUsername, validPassword, validEmail);
            RegisterUserResult expectedRegisterResult = new RegisterUserResult(validUserId);
            LoadUserByEmailCommand loadUserByEmailCommand = new LoadUserByEmailCommand(validEmail);
            Optional<LoadUserByEmailResult> successResult = Optional.empty();
            PersistUserCommand persistUserCommand = new PersistUserCommand(validUsername, validPassword, validEmail);
            PersistUserResult persistUserResult = new PersistUserResult(validUserId);
            when(mockLoadUserByEmailOutputPort.loadUserByEmail(loadUserByEmailCommand)).thenReturn(successResult);
            when(mockPersistUserOutputPort.persistUser(persistUserCommand)).thenReturn(persistUserResult);
            //act
            RegisterUserResult actualRegisterResult = registerUserService.registerUser(registerUserCommand);
            //assert
            assertEquals(expectedRegisterResult, actualRegisterResult);
        }
    }

    @Nested
    @DisplayName("Failure Tests")
    class FailureTests {
        @Test
        @DisplayName("should throw exception when invalid input is provided")
        void should_throw_when_invalid_input() {
            //assert
            ConstraintViolationException actualException = assertThrows(ConstraintViolationException.class,
                    () -> registerUserService.registerUser(new RegisterUserCommand(invalidUsername, invalidPassword, invalidEmail)));
            assertEquals(3, actualException.getConstraintViolations().size());
        }

        @Test
        @DisplayName("should throw exception when user already exists")
        void should_throw_when_user_already_exists() {
            //arrange
            RegisterUserCommand registerUserCommand = new RegisterUserCommand(validUsername, validPassword, validEmail);
            LoadUserByEmailCommand loadUserByEmailCommand = new LoadUserByEmailCommand(validEmail);
            UserManagementException expectedException = new UserManagementException(ErrorCode.USER_ALREADY_EXISTS);
            when(mockLoadUserByEmailOutputPort.loadUserByEmail(loadUserByEmailCommand))
                    .thenThrow(new UserManagementException(ErrorCode.USER_ALREADY_EXISTS));
            //assert
            UserManagementException actualException = assertThrows(UserManagementException.class,
                    () -> registerUserService.registerUser(registerUserCommand));
            assertEquals(expectedException, actualException);
        }
    }
}