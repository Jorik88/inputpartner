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
@XmlRootElement(name = "ROW")
public class ReportRowResponse {

    @XmlElement(name = "account")
    private String account;

    @XmlElement(name = "currency")
    private String currency;

    @XmlElement(name = "dt_from")
    private String startDate;

    @XmlElement(name = "dt_till")
    private String endDate;

    @XmlElementWrapper(name = "date_list")
    @XmlElement(name = "date_list_ROW")
    private List<ReportDateRowResponse> dateRows;

}
