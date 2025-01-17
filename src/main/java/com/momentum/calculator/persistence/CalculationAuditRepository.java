/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.persistence;

import com.momentum.calculator.domain.CalculationAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 *
 * @author S2028873
 */
@Repository
public interface CalculationAuditRepository extends JpaRepository<CalculationAudit, Long> {
    
    @Query("SELECT ca FROM CalculationAudit ca WHERE " +
           "(:username IS NULL OR ca.user.userName LIKE %:username%) AND " +
           "(:fromDate IS NULL OR ca.createdDate >= :fromDate) AND " +
           "(:toDate IS NULL OR ca.createdDate <= :toDate) " +
           "ORDER BY ca.createdDate DESC")
    List<CalculationAudit> search(@Param("username") String username,
                                 @Param("fromDate") Date fromDate,
                                 @Param("toDate") Date toDate);
}
