package com.example.inputpartner.repository;

import com.example.inputpartner.entity.WithdrawalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalOrderRepository extends JpaRepository<WithdrawalOrder, Long> {
}
