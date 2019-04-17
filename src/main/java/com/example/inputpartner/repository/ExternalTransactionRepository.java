package com.example.inputpartner.repository;

import com.example.inputpartner.entity.ExternalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalTransactionRepository extends JpaRepository<ExternalTransaction,String> {
}
