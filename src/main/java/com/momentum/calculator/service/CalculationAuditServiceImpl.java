/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.service;
import com.momentum.calculator.domain.CalculationAudit;
import com.momentum.calculator.persistence.CalculationAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
/**
 *
 * @author S2028873
 */
@Service
public class CalculationAuditServiceImpl implements CalculationAuditService {
    
    @Autowired
    private CalculationAuditRepository calculationAuditRepository;
    
    @Override
    public CalculationAudit save(CalculationAudit audit) {
        return calculationAuditRepository.save(audit);
    }
    
    @Override
    public CalculationAudit findById(Long id) {
        return calculationAuditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The requested id [" + id + "] does not exist"));
    }
    
    @Override
    public List<CalculationAudit> search(String username, Date fromDate, Date toDate) {
        return calculationAuditRepository.search(username, fromDate, toDate);
    }
    
    @Override
    public List<CalculationAudit> listAll() {
        return calculationAuditRepository.findAll();
    }
}