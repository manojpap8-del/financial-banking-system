package manoj.example.financial_banking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import manoj.example.financial_banking_system.entity.Account;
import manoj.example.financial_banking_system.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);

}