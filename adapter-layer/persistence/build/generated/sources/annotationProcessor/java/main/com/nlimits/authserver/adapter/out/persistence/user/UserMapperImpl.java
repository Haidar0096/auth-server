package com.nlimits.authserver.adapter.out.persistence.user;

import com.nlimits.authserver.application.user.domain.User;
import com.nlimits.authserver.application.user.domain.User.Email;
import com.nlimits.authserver.application.user.domain.User.Password;
import com.nlimits.authserver.application.user.domain.User.UserId;
import com.nlimits.authserver.application.user.domain.User.Username;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T14:03:12+0200",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.jar, environment: Java 15 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public JpaUser createJpaUser(User user) {
        if ( user == null ) {
            return null;
        }

        JpaUser jpaUser = new JpaUser();

        jpaUser.setId( userUserIdValue( user ) );
        jpaUser.setUsername( userUsernameValue( user ) );
        jpaUser.setPassword( userPasswordValue( user ) );
        jpaUser.setEmail( userEmailValue( user ) );

        return jpaUser;
    }

    @Override
    public User createUser(JpaUser jpaUser) {
        if ( jpaUser == null ) {
            return null;
        }

        UserId userId = null;
        Username username = null;
        Password password = null;
        Email email = null;

        userId = jpaUserToUserId( jpaUser );
        username = jpaUserToUsername( jpaUser );
        password = jpaUserToPassword( jpaUser );
        email = jpaUserToEmail( jpaUser );

        User user = new User( userId, username, password, email );

        return user;
    }

    private Long userUserIdValue(User user) {
        if ( user == null ) {
            return null;
        }
        UserId userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        Long value = userId.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String userUsernameValue(User user) {
        if ( user == null ) {
            return null;
        }
        Username username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        String value = username.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String userPasswordValue(User user) {
        if ( user == null ) {
            return null;
        }
        Password password = user.getPassword();
        if ( password == null ) {
            return null;
        }
        String value = password.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String userEmailValue(User user) {
        if ( user == null ) {
            return null;
        }
        Email email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        String value = email.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    protected UserId jpaUserToUserId(JpaUser jpaUser) {
        if ( jpaUser == null ) {
            return null;
        }

        Long value = null;

        value = jpaUser.getId();

        UserId userId = new UserId( value );

        return userId;
    }

    protected Username jpaUserToUsername(JpaUser jpaUser) {
        if ( jpaUser == null ) {
            return null;
        }

        String value = null;

        value = jpaUser.getUsername();

        Username username = new Username( value );

        return username;
    }

    protected Password jpaUserToPassword(JpaUser jpaUser) {
        if ( jpaUser == null ) {
            return null;
        }

        String value = null;

        value = jpaUser.getPassword();

        Password password = new Password( value );

        return password;
    }

    protected Email jpaUserToEmail(JpaUser jpaUser) {
        if ( jpaUser == null ) {
            return null;
        }

        String value = null;

        value = jpaUser.getEmail();

        Email email = new Email( value );

        return email;
    }
}
