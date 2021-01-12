package com.nlimits.authserver.adapter.output.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Jpa Repository for performing different database operations on the JpaUser model
 */
interface JpaUserRepository extends JpaRepository<JpaUser, Long> {

    Optional<JpaUser> findByEmail(String emailAddress);

}

