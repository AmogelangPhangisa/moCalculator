/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.domain;

import com.momentum.calculator.common.SystemUserStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
/**
 *
 * @author S2028873
 */
@Getter
@Setter
@Audited
@Entity
@Table(name = "Syst_user")
public class User extends Person{

    
    
    @Column(name = "identifer")
    private String identifer;

    @Column(name = "user_name")
    private String userName;

   
    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password format. It must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@, #, $, %, ^, &, +, =).")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

 
    @Column(name = "confirm_password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password format. It must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@, #, $, %, ^, &, +, =).")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String confirmPassword;
    
    @Column(name = "change_password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password format. It must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character (@, #, $, %, ^, &, +, =).")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String changePassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "system_user_status")
    private SystemUserStatus systemUserStatus;



}
