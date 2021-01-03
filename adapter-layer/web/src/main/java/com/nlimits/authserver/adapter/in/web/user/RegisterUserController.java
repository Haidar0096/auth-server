package com.nlimits.authserver.adapter.in.web.user;

import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserCommand;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserResult;
import com.nlimits.authserver.common.WebAdapter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserInputPort registerUserInputPort;

    private final CommandMapper commandMapper;

    @PostMapping
    public RegisterUserResult registerUser(@RequestBody RegisterUserWebModel model) {
        return registerUserInputPort.
                registerUser(commandMapper.createRegisterUserCommand(model));
    }


    @Data
    static class RegisterUserWebModel {
        private String username;
        private String password;
        private String email;
    }

    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
    interface CommandMapper {

        @Mapping(source = "username", target = "username.value")
        @Mapping(source = "password", target = "password.value")
        @Mapping(source = "email", target = "email.value")
        RegisterUserCommand createRegisterUserCommand(RegisterUserWebModel model);
    }

}
