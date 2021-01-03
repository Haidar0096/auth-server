package com.nlimits.authserver.adapter.in.web.user;

import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserCommand;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserResult;
import com.nlimits.authserver.common.annotation.WebAdapter;
import com.nlimits.authserver.common.web.Response;
import com.nlimits.authserver.common.web.ResponseData;
import lombok.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/api/v1/user/register")//todo centralize the url in some config file
@Value
public class RegisterUserController {

    RegisterUserInputPort registerUserInputPort;

    CommandMapper commandMapper;

    ResultMapper resultMapper;

    @PostMapping
    public ResponseEntity<Response<RegisterUserResultWebModel>> registerUser(@RequestBody RegisterUserWebModel model) {
        RegisterUserResult result = registerUserInputPort.
                registerUser(commandMapper.createRegisterUserCommand(model));
        return ResponseEntity.ok(
                new Response<>(true,
                        resultMapper.createRegisterUserResultWebModel(result),
                        null,
                        null)
        );
    }


    @Value
    static class RegisterUserWebModel {
        String username;
        String password;
        String email;
    }

    @Value
    static class RegisterUserResultWebModel implements ResponseData {
        Long userId;
    }

    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
    interface CommandMapper {

        @Mapping(source = "username", target = "username.value")
        @Mapping(source = "password", target = "password.value")
        @Mapping(source = "email", target = "email.value")
        RegisterUserCommand createRegisterUserCommand(RegisterUserWebModel model);
    }

    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
    interface ResultMapper {
        @Mapping(source = "userId.value", target = "userId")
        RegisterUserResultWebModel createRegisterUserResultWebModel(RegisterUserResult result);
    }

}
