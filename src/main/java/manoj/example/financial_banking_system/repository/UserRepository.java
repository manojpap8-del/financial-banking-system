package manoj.example.financial_banking_system.repository;

import manoj.example.financial_banking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check duplicate email
    boolean existsByEmail(String email);

}