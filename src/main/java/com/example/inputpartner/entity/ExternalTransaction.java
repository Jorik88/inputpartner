package com.example.inputpartner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"transactionId"})
@Entity(name = "external_transaction")
public class ExternalTransaction extends BaseModel<String> {

    @Id
    private String transactionId;
    private String destinationAddress;
    private String destinationMemo;
    private BigInteger amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    @Override
    public String getId() {
        return this.transactionId;
    }
}
