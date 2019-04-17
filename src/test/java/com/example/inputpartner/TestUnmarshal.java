package com.example.inputpartner;

import com.example.inputpartner.model.ReportFisResponse;
import com.example.inputpartner.model.ReportTransactionRowResponse;
import com.example.inputpartner.service.ProcessReportService;
import com.example.inputpartner.service.SendReportRequestService;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TestUnmarshal {

    private SendReportRequestService requestService = new SendReportRequestService();
    private ProcessReportService processReportService = new ProcessReportService();


    @Test
    public void testProcessReport() {
        processReportService.processReport();
    }

    @Test
    public void testUnmarshal() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("/home/jorik/ExamplesApp/inputpartner/src/main/resources/static/exampleResponse.xml")));
        ReportFisResponse unmarshal = JAXB.unmarshal(new StringReader(content), ReportFisResponse.class);
        System.out.println(unmarshal);
    }

    @Test
    public void testSendRequest() {
        String keiId = "9C3733455D071900CFF7BC04DC569E2C";
        String account = "BY39MTBK30120001093300090355";
        String dateFrom = "201901010000";
        String dateTill = "20190110235959";
        String response = requestService.getBankReport(keiId, account, dateFrom, dateTill);
        ReportFisResponse unmarshal = JAXB.unmarshal(new StringReader(response), ReportFisResponse.class);
        List<ReportTransactionRowResponse> transactionRows = unmarshal.getReportMessageResponse().getReportResponse().getRowSet().getRow().getDateRows().get(0).getTransactionRows();
        for (ReportTransactionRowResponse rowResponse : transactionRows) {
            if (rowResponse.getFlowOperation().equals("D")) {
                System.out.println(rowResponse);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        }
    }

    @Test
    public void testFinal() {
        String keiId = "9C3733455D071900CFF7BC04DC569E2C";
        String account = "BY39MTBK30120001093300090355";
        String dateFrom = getMinMaxDate(LocalDateTime.now(), false);
        String dateTill = getMinMaxDate(LocalDateTime.now(), true);

        System.out.println(dateFrom + " >>>>>>>>> " + dateTill);
        String response = requestService.getBankReport(keiId, account, dateFrom, dateTill);
        ReportFisResponse unmarshal = JAXB.unmarshal(new StringReader(response), ReportFisResponse.class);
        List<ReportTransactionRowResponse> transactionRows = unmarshal.getReportMessageResponse().getReportResponse().getRowSet().getRow().getDateRows().get(0).getTransactionRows();
        for (ReportTransactionRowResponse rowResponse : transactionRows) {
            if (rowResponse.getFlowOperation().equals("D")) {
                System.out.println(rowResponse);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        }
    }

    @Test
    public void testDate() {
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        String time = localDateTime.toString().replace("-", "").replace(":", "").replace("T", "");
        System.out.println(time);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testCreateDate() {
        System.out.println(LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX).withNano(0).toString().replace("-", "").replace(":", "").replace("T", ""));
        System.out.println(LocalDateTime.now().toLocalDate().atTime(LocalTime.MIN).withNano(0).toString().replace("-", "").replace(":", "").replace("T", ""));
    }

    private String getMinMaxDate(LocalDateTime time, boolean max) {
        if (max) {
            return replaceDelimiters(time.toLocalDate().atTime(LocalTime.MAX).withNano(0).toString());
        }
        return replaceDelimiters(time.toLocalDate().atTime(LocalTime.MIN).withNano(0).toString());
    }

    private String replaceDelimiters(String date) {
        return date.replace("-", "").replace(":", "").replace("T", "");
    }
}
