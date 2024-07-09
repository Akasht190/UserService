package com.akash.userservice.repositories;

import com.akash.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    @Override
    Session save(Session entity);

    Optional<Session> findByTokenAndUser_Id(String token, Long id);
}
