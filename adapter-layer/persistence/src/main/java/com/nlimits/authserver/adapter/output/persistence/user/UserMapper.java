package com.nlimits.authserver.adapter.output.persistence.user;

import com.nlimits.authserver.application.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * A mapper between User model and JpaUser model objects
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId.value", target = "id")
    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "password.value", target = "password")
    @Mapping(source = "email.value", target = "email")
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    JpaUser createJpaUser(User user);

    @Mapping(source = "id", target = "userId.value")
    @Mapping(source = "username", target = "username.value")
    @Mapping(source = "password", target = "password.value")
    @Mapping(source = "email", target = "email.value")
    User createUser(JpaUser jpaUser);
}

