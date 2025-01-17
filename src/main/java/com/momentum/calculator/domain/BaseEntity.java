/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
/**
 *
 * @author S2028873
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long id;

    @Audited
    @Column(name = "Created_by", updatable = false, nullable = false, length = 65)
    private String createdBy;

    @Audited
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_Date")
    private Date createdDate;

    @Audited
    @Column(name = "Updated_by", length = 65)
    private String updatedBy;

    @Audited
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_Date")
    private Date updateDate;

}
