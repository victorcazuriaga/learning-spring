package br.com.kenzie.learningSpring.repository;

import br.com.kenzie.learningSpring.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long > {

}
