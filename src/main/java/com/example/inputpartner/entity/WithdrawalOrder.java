package com.example.inputpartner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"id"})
@Entity(name = "withdrawal_order")
public class WithdrawalOrder extends BaseModel<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger amount;
    private String destinationAddress;
    private String destinationMemo;
    private String userId;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private int externalTransactionAttemptCount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "withdrawal_order_external_transactions",
            inverseJoinColumns = @JoinColumn(name = "withdrawal_id"),
            joinColumns = @JoinColumn(name = "external_transaction_id"))
    private Set<ExternalTransaction> externalTransactions;

}
