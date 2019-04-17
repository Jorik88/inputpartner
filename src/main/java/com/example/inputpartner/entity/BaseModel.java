package com.example.inputpartner.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
@EqualsAndHashCode(of = {"createdTimestamp"})
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel<ID extends Serializable> {

    @CreatedDate
    @Column(updatable = false)
    private Long createdTimestamp;

    @LastModifiedDate
    private Long lastEditedTimestamp;

    public abstract ID getId();
}
