/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.domain;
import com.momentum.calculator.common.PersonType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author S2028873
 */
@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "First_Name")
    @Pattern(regexp = "^[A-Za-z]+(?:\\s+[A-Za-z]+)*$", message = "Invalid First Name format")
    private String firstName;

    @Column(name = "Last_Name")
    @Pattern(regexp = "^[A-Za-z]+(?:\\s+[A-Za-z]+)*$", message = "Invalid Last Name name format")
    private String lastName;

    @Column(name = "Identity_Number")
    @Pattern(regexp = "^[0-9]{13}$", message = "Invalid South African ID number format")
    private String identityNumber;

    @Column(name = "cellphone_number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Cellphone Number")
    private String cellphoneNumber;

    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",
            message = "Invalid email format. Please provide a valid email address.")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Person_Type")
    private PersonType personType;

}
