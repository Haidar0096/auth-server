package com.nlimits.authserver.adapter.output.persistence.user;

import com.nlimits.authserver.application.user.application.port.output.*;
import com.nlimits.authserver.application.user.domain.User;
import com.nlimits.authserver.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.nlimits.authserver.application.user.domain.User.*;

/**
 * This class implements the different output ports used for CRUD operations on the User model
 */
@PersistenceAdapter
@RequiredArgsConstructor
class JpaUserPersistenceAdapter implements PersistUserOutputPort, LoadUserByEmailOutputPort,
        LoadUserByIdOutputPort, DeleteUserByIdOutputPort, UpdateUserByIdOutputPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    /**
     * This method is called with the idea in mind being that the user with the given id already exists,
     * i.e. the caller of this method must first make sure that the user exists or else unwanted results may occur.
     */
    @Override
    public PersistUserOutputPortResult persistUser(PersistUserOutputPortCommand command) {
        LocalDateTime dateCreated = LocalDateTime.now();
        LocalDateTime dateUpdated = LocalDateTime.now();
        JpaUser jpaUser = userMapper.createJpaUser(new User(null, command.getUsername(), command.getPassword(), command.getEmail()));
        jpaUser.setDateCreated(dateCreated);
        jpaUser.setDateUpdated(dateUpdated);
        JpaUser persistedUser = jpaUserRepository.save(jpaUser);
        return new PersistUserOutputPortResult(new UserId(persistedUser.getId()));
    }

    @Override
    public Optional<LoadUserByEmailOutputPortResult> loadUserByEmail(LoadUserByEmailOutputPortCommand command) {
        Optional<JpaUser> queriedUserOptional = jpaUserRepository.findByEmail(command.getEmail().getValue());
        return queriedUserOptional.map(queriedUser
                        -> new LoadUserByEmailOutputPortResult(
                        new UserId(queriedUser.getId()),
                        new Username(queriedUser.getUsername()),
                        new Email(queriedUser.getEmail())
                )
        );
    }

    @Override
    public Optional<LoadUserByIdOutputPortResult> loadUserById(LoadUserByIdOutputPortCommand command) {
        Optional<JpaUser> queriedUserOptional = jpaUserRepository.findById(command.getUserId().getValue());
        return queriedUserOptional.map(queriedUser
                        -> new LoadUserByIdOutputPortResult(
                        new UserId(queriedUser.getId()),
                        new Username(queriedUser.getUsername()),
                        new Email(queriedUser.getEmail())
                )
        );
    }

    @Override
    public void deleteUserById(DeleteUserByIdOutputPortCommand command) {
        jpaUserRepository.deleteById(command.getUserId().getValue());
    }

    /**
     * Caller of this method must make sure first that the user exists already or else an exception will be thrown
     */
    @Override
    public void updateUserById(UpdateUserByIdOutputPortCommand command) {
        Optional<JpaUser> jpaUserOptional = jpaUserRepository.findById(command.getUserId().getValue());
        JpaUser jpaUser = jpaUserOptional.get();
        command.getUsernameOptional().ifPresent(usernameOptional -> jpaUser.setUsername(usernameOptional.getValue()));
        command.getPasswordOptional().ifPresent(passwordOptional -> jpaUser.setPassword(passwordOptional.getValue()));
        command.getEmailOptional().ifPresent(emailOptional -> jpaUser.setEmail(emailOptional.getValue()));
    }
}

