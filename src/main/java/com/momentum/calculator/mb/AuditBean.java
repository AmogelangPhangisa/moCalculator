/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import com.momentum.calculator.domain.CalculationAudit;
import com.momentum.calculator.service.CalculationAuditService;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.annotation.PostConstruct;

@ManagedBean(name = "auditBean")
@ViewScoped
public class AuditBean extends BaseBean<CalculationAudit> {
    
    @Autowired
    private CalculationAuditService calculationAuditService;
    
    private Date fromDate;
    private Date toDate;
    
    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("Calculator Audit Records");
        loadAudits();
    }
    
    public void loadAudits() {
        try {
            List<CalculationAudit> audits = calculationAuditService.listAll();
            addCollections(audits);
        } catch (Exception e) {
            addErrorMessage("Error loading audit records: " + e.getMessage());
        }
    }
    
    public void search() {
        try {
            if (fromDate != null || toDate != null) {
                List<CalculationAudit> audits = calculationAuditService.search(null, fromDate, toDate);
                addCollections(audits);
            } else {
                loadAudits();
            }
        } catch (Exception e) {
            addErrorMessage("Error searching audit records: " + e.getMessage());
        }
    }
    
    // Getters and setters for fromDate and toDate
    public Date getFromDate() {
        return fromDate;
    }
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    
    public Date getToDate() {
        return toDate;
    }
    
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}