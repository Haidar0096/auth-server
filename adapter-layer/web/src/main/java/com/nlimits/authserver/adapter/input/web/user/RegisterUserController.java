package com.nlimits.authserver.adapter.input.web.user;

import com.nlimits.authserver.application.user.application.port.input.RegisterUserInputPort;
import com.nlimits.authserver.application.user.application.port.input.RegisterUserInputPort.RegisterUserCommand;
import com.nlimits.authserver.application.user.application.port.input.RegisterUserInputPort.RegisterUserResult;
import com.nlimits.authserver.common.annotation.WebAdapter;
import com.nlimits.authserver.common.web.Response;
import com.nlimits.authserver.common.web.ResponseData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for registering user requests
 */
@WebAdapter
@RestController
@RequestMapping("/api/v1/user/register")//todo centralize the url in some config file
@Value
public class RegisterUserController {

    RegisterUserInputPort registerUserInputPort;

    CommandMapper commandMapper;

    ResultMapper resultMapper;

    @PostMapping
    public ResponseEntity<Response<RegisterUserResponse>> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        RegisterUserResult registerUserResult = registerUserInputPort.
                registerUser(commandMapper.createRegisterUserCommand(registerUserRequest));
        return ResponseEntity.ok(
                new Response<>(true,
                        resultMapper.createRegisterUserResponse(registerUserResult),
                        null,
                        null)
        );
    }


    /**
     * web model for the register user request
     */
    @Getter
    @NoArgsConstructor
    static class RegisterUserRequest {
        String username;
        String password;
        String email;
    }

    /**
     * web model for the register user response
     */
    @Value
    static class RegisterUserResponse implements ResponseData {
        Long userId;
    }

    /**
     * Mapper between web model and domain model of the register user command object
     */
    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
    interface CommandMapper {

        @Mapping(source = "username", target = "username.value")
        @Mapping(source = "password", target = "password.value")
        @Mapping(source = "email", target = "email.value")
        RegisterUserCommand createRegisterUserCommand(RegisterUserRequest model);
    }

    /**
     * Mapper between web model and domain model of the register user result object
     */
    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
    interface ResultMapper {
        @Mapping(source = "userId.value", target = "userId")
        RegisterUserResponse createRegisterUserResponse(RegisterUserResult result);
    }

}

