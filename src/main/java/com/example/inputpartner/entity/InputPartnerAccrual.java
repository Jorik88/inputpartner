package com.example.inputpartner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"id"})
@Entity(name = "input_partner_accrual")
public class InputPartnerAccrual extends  BaseModel<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger amount;
    private Long userId;
    private Long partnerId;
    private String partnerName;

}
