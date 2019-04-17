package com.example.inputpartner.controller;

import com.example.inputpartner.model.ReportTransactionRowResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ReportTransactionRowResponse getTransaction() {
        ReportTransactionRowResponse transactionRow = new ReportTransactionRowResponse();
        transactionRow.setAmount(23.3);
        transactionRow.setBankCode("MTB");
        transactionRow.setCreditAccount("credit account");
        transactionRow.setCreditName("credit name");
        transactionRow.setCreditUnp(22L);
        transactionRow.setDate("some date");
        transactionRow.setDebitbName("debit name");
        transactionRow.setDebitAccount("debit account");
        transactionRow.setTransactionCode(34534L);
        transactionRow.setDebitUnp(33L);
        return transactionRow;
    }
}
