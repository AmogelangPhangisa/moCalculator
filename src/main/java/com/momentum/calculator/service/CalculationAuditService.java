/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.service;

import com.momentum.calculator.domain.CalculationAudit;
import java.util.Date;
import java.util.List;

public interface CalculationAuditService {
    CalculationAudit save(CalculationAudit audit);
    CalculationAudit findById(Long id);
    List<CalculationAudit> search(String username, Date fromDate, Date toDate);
    List<CalculationAudit> listAll();
}