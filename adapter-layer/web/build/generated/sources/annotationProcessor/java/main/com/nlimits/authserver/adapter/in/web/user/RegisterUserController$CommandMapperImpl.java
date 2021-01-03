package com.nlimits.authserver.adapter.in.web.user;

import com.nlimits.authserver.adapter.in.web.user.RegisterUserController.CommandMapper;
import com.nlimits.authserver.adapter.in.web.user.RegisterUserController.RegisterUserRequest;
import com.nlimits.authserver.application.user.application.port.in.RegisterUserInputPort.RegisterUserCommand;
import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.Password;
import com.nlimits.authserver.application.user.domain.User.Username;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T20:25:25+0200",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.jar, environment: Java 15 (Oracle Corporation)"
)
@Component
class RegisterUserController$CommandMapperImpl implements CommandMapper {

    @Override
    public RegisterUserCommand createRegisterUserCommand(RegisterUserRequest model) {
        if ( model == null ) {
            return null;
        }

        Username username = null;
        Password password = null;
        Email email = null;

        username = registerUserWebModelToUsername( model );
        password = registerUserWebModelToPassword( model );
        email = registerUserWebModelToEmail( model );

        RegisterUserCommand registerUserCommand = new RegisterUserCommand( username, password, email );

        return registerUserCommand;
    }

    protected Username registerUserWebModelToUsername(RegisterUserController.RegisterUserRequest registerUserRequest) {
        if (registerUserRequest == null ) {
            return null;
        }

        String value = null;

        value = registerUserRequest.getUsername();

        Username username = new Username( value );

        return username;
    }

    protected Password registerUserWebModelToPassword(RegisterUserController.RegisterUserRequest registerUserRequest) {
        if (registerUserRequest == null ) {
            return null;
        }

        String value = null;

        value = registerUserRequest.getPassword();

        Password password = new Password( value );

        return password;
    }

    protected Email registerUserWebModelToEmail(RegisterUserController.RegisterUserRequest registerUserRequest) {
        if (registerUserRequest == null ) {
            return null;
        }

        String value = null;

        value = registerUserRequest.getEmail();

        Email email = new Email( value );

        return email;
    }
}
