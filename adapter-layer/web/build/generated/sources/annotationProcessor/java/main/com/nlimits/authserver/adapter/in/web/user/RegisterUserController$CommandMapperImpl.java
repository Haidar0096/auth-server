package com.nlimits.authserver.adapter.in.web.user;

import com.nlimits.authserver.adapter.in.web.user.RegisterUserController.CommandMapper;
import com.nlimits.authserver.adapter.in.web.user.RegisterUserController.RegisterUserWebModel;
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
    public RegisterUserCommand createRegisterUserCommand(RegisterUserWebModel model) {
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

    protected Username registerUserWebModelToUsername(RegisterUserWebModel registerUserWebModel) {
        if ( registerUserWebModel == null ) {
            return null;
        }

        String value = null;

        value = registerUserWebModel.getUsername();

        Username username = new Username( value );

        return username;
    }

    protected Password registerUserWebModelToPassword(RegisterUserWebModel registerUserWebModel) {
        if ( registerUserWebModel == null ) {
            return null;
        }

        String value = null;

        value = registerUserWebModel.getPassword();

        Password password = new Password( value );

        return password;
    }

    protected Email registerUserWebModelToEmail(RegisterUserWebModel registerUserWebModel) {
        if ( registerUserWebModel == null ) {
            return null;
        }

        String value = null;

        value = registerUserWebModel.getEmail();

        Email email = new Email( value );

        return email;
    }
}
