package com.example.inputpartner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class ReportResponse {

    @XmlAttribute(name = "Scheme")
    private String scheme;

    @XmlAttribute(name = "RsId")
    private String rsId;

    @XmlAttribute(name = "System")
    private String system;

    @XmlElement(name = "ROWSET")
    private ReportRowsetResponse rowSet;

}
