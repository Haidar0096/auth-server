package com.nlimits.authserver.application.user.application.service;

import com.nlimits.authserver.application.user.application.port.input.RegisterUserInputPort.RegisterUserInputPortCommand;
import com.nlimits.authserver.application.user.application.port.input.RegisterUserInputPort.RegisterUserInputPortResult;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailOutputPortCommand;
import com.nlimits.authserver.application.user.application.port.output.LoadUserByEmailOutputPort.LoadUserByEmailOutputPortResult;
import com.nlimits.authserver.application.user.application.port.output.PersistUserOutputPort;
import com.nlimits.authserver.application.user.application.port.output.PersistUserOutputPort.PersistUserOutputPortCommand;
import com.nlimits.authserver.application.user.application.port.output.PersistUserOutputPort.PersistUserOutputPortResult;
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
            RegisterUserInputPortCommand registerUserInputPortCommand = new RegisterUserInputPortCommand(validUsername, validPassword, validEmail);
            RegisterUserInputPortResult expectedRegisterResult = new RegisterUserInputPortResult(validUserId);
            LoadUserByEmailOutputPortCommand loadUserByEmailOutputPortCommand = new LoadUserByEmailOutputPortCommand(validEmail);
            Optional<LoadUserByEmailOutputPortResult> successResult = Optional.empty();
            PersistUserOutputPortCommand persistUserOutputPortCommand = new PersistUserOutputPortCommand(validUsername, validPassword, validEmail);
            PersistUserOutputPortResult persistUserOutputPortResult = new PersistUserOutputPortResult(validUserId);
            when(mockLoadUserByEmailOutputPort.loadUserByEmail(loadUserByEmailOutputPortCommand)).thenReturn(successResult);
            when(mockPersistUserOutputPort.persistUser(persistUserOutputPortCommand)).thenReturn(persistUserOutputPortResult);
            //act
            RegisterUserInputPortResult actualRegisterResult = registerUserService.registerUser(registerUserInputPortCommand);
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
                    () -> registerUserService.registerUser(new RegisterUserInputPortCommand(invalidUsername, invalidPassword, invalidEmail)));
            assertEquals(3, actualException.getConstraintViolations().size());
        }

        @Test
        @DisplayName("should throw exception when user already exists")
        void should_throw_when_user_already_exists() {
            //arrange
            RegisterUserInputPortCommand registerUserInputPortCommand = new RegisterUserInputPortCommand(validUsername, validPassword, validEmail);
            LoadUserByEmailOutputPortCommand loadUserByEmailOutputPortCommand = new LoadUserByEmailOutputPortCommand(validEmail);
            UserManagementException expectedException = new UserManagementException(ErrorCode.USER_ALREADY_EXISTS);
            when(mockLoadUserByEmailOutputPort.loadUserByEmail(loadUserByEmailOutputPortCommand))
                    .thenThrow(new UserManagementException(ErrorCode.USER_ALREADY_EXISTS));
            //assert
            UserManagementException actualException = assertThrows(UserManagementException.class,
                    () -> registerUserService.registerUser(registerUserInputPortCommand));
            assertEquals(expectedException, actualException);
        }
    }
}

//todo MAYBE write documentation for test methods in this class

//todo see why tests are failing ( gradle problem, as they were not failing earlier)