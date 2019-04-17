package com.example.inputpartner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trns_list_ROW")
public class ReportTransactionRowResponse {


    @XmlElement(name = "trns_code")
    private Long transactionCode;

    @XmlElement(name = "flow")
    private String flowOperation;

    @XmlElement(name = "amount")
    private Double amount;

    @XmlElement(name = "currency")
    private String currencyCode;

    @XmlElement(name = "timestamp")
    private String date;

    @XmlElement(name = "db_accnt")
    private String debitAccount;

    @XmlElement(name = "cr_accnt")
    private String creditAccount;

    @XmlElement(name = "bnk_code")
    private String bankCode;

    @XmlElement(name = "db_unp")
    private Long debitUnp;

    @XmlElement(name = "db_name")
    private String debitbName;

    @XmlElement(name = "cr_unp")
    private Long creditUnp;

    @XmlElement(name = "cr_name")
    private String creditName;

    @XmlElement(name = "pmdt")
    private String description;

}
