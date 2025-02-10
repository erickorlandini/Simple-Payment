package com.paymentsimplificado.paymentsimplificado.repositories;

import com.paymentsimplificado.paymentsimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface transactionRepository extends JpaRepository<Transaction, Long> {
}
