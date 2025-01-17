/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.domain;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import org.hibernate.envers.Audited;
/**
 *
 * @author S2028873
 */
@Getter
@Setter
@Entity
@Table(name = "calculation_audit")
@Audited
public class CalculationAudit extends BaseEntity {
    
   @Column(name = "expression", nullable = false)
    private String expression;
    
    @Column(name = "result", nullable = false)
    private String result;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}