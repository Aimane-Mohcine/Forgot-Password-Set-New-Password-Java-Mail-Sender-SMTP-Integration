package org.example.email.Repository;

import org.example.email.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

}
