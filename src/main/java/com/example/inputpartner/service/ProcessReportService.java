package com.example.inputpartner.service;

import com.example.inputpartner.model.ReportDateRowResponse;
import com.example.inputpartner.model.ReportFisResponse;
import com.example.inputpartner.model.ReportTransactionRowResponse;
import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ProcessReportService {

    private String keiId = "9C3733455D071900CFF7BC04DC569E2C";
    private String account = "BY39MTBK30120001093300090355";
    private SendReportRequestService sendReportRequestService = new SendReportRequestService();

    public void processReport() {
//        String dateFrom = getDate(LocalDateTime.now(), false);
//        String dateTill = getDate(LocalDateTime.now(), true);
        String dateFrom = "201901010000";
        String dateTill = "20190110235959";
        String bankReportResponse = sendReportRequestService.getBankReport(keiId, account, dateFrom, dateTill);
        ReportFisResponse response = JAXB.unmarshal(new StringReader(bankReportResponse), ReportFisResponse.class);
        if (response != null) {
            List<ReportDateRowResponse> dateRows = response.getReportMessageResponse().getReportResponse().getRowSet().getRow().getDateRows();
            if (dateRows == null || dateRows.size() == 0){
                return;
            }else{
               processDateRows(dateRows);
            }
        }
    }

    private void processDateRows(List<ReportDateRowResponse> dateRows) {
        for (ReportDateRowResponse rowResponse : dateRows) {
            List<ReportTransactionRowResponse> transactionRows = rowResponse.getTransactionRows();
            if (transactionRows == null || transactionRows.size() == 0) {
                return;
            }else{
                processTransactionRows(transactionRows);
            }
        }
    }

    private void processTransactionRows(List<ReportTransactionRowResponse> transactionRows) {
        for (ReportTransactionRowResponse rowResponse : transactionRows) {
            if (rowResponse.getFlowOperation().equals("C")) {
                continue;
            }else {
                System.out.println(rowResponse);
            }
        }
    }

    private String getDate(LocalDateTime time, boolean max) {
        if (max) {
            return replaceDelimiters(time.toLocalDate().atTime(LocalTime.MAX).withNano(0).toString());
        }
        return replaceDelimiters(time.toLocalDate().atTime(LocalTime.MIN).withNano(0).toString());
    }

    private String replaceDelimiters(String date) {
        return date.replace("-", "").replace(":", "").replace("T", "");
    }
}
