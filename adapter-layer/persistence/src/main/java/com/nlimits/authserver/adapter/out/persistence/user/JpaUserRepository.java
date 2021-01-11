package com.nlimits.authserver.adapter.out.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface JpaUserRepository extends JpaRepository<JpaUser, Long> {

    Optional<JpaUser> findByEmail(String emailAddress);

}

//todo write documentation for this class