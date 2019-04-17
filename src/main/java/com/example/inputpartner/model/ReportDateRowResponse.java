package com.example.inputpartner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "date_list_ROW")
public class ReportDateRowResponse {

    @XmlElement(name = "dt")
    private String date;

    @XmlElement(name = "blnc_on_begin_dt")
    private Double balanceOnBeginDate;

    @XmlElement(name = "blnc_on_end_dt")
    private Double balanceOnEndDate;

    @XmlElementWrapper(name = "trns_list")
    @XmlElement(name = "trns_list_ROW")
    private List<ReportTransactionRowResponse> transactionRows;
}
