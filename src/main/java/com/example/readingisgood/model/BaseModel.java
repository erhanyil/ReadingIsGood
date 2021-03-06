package com.example.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {

    @CreatedBy
    @JsonIgnore
    protected Long createdBy;

    @CreatedDate
    @JsonIgnore
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @JsonIgnore
    protected Long lastModifiedBy;

    @LastModifiedDate
    @JsonIgnore
    protected LocalDateTime lastModifiedDate;


}