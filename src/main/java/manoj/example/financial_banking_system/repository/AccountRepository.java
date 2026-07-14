package manoj.example.financial_banking_system.repository;

import manoj.example.financial_banking_system.entity.Account;
import manoj.example.financial_banking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUser(User user);
    Optional<Account> findByUser(User user);// for deposit

    Optional<Account> findByAccountNumber(String accountNumber);

}