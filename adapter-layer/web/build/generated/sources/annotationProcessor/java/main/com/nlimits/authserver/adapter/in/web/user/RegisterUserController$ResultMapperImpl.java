package com.nlimits.authserver.adapter.in.web.user;

import com.nlimits.authserver.adapter.in.web.user.RegisterUserController.RegisterUserResultWebModel;
import com.nlimits.authserver.adapter.in.web.user.RegisterUserController.ResultMapper;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserResult;
import com.nlimits.authserver.application.user.domain.User.UserId;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T20:25:25+0200",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.jar, environment: Java 15 (Oracle Corporation)"
)
@Component
class RegisterUserController$ResultMapperImpl implements ResultMapper {

    @Override
    public RegisterUserResultWebModel createRegisterUserResultWebModel(RegisterUserResult result) {
        if ( result == null ) {
            return null;
        }

        Long userId = null;

        userId = resultUserIdValue( result );

        RegisterUserResultWebModel registerUserResultWebModel = new RegisterUserResultWebModel( userId );

        return registerUserResultWebModel;
    }

    private Long resultUserIdValue(RegisterUserResult registerUserResult) {
        if ( registerUserResult == null ) {
            return null;
        }
        UserId userId = registerUserResult.getUserId();
        if ( userId == null ) {
            return null;
        }
        Long value = userId.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }
}
