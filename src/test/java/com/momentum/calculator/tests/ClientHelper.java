/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.tests;

import com.momentum.calculator.common.PersonType;
import com.momentum.calculator.common.SystemUserStatus;
import com.momentum.calculator.domain.Client;
import com.momentum.calculator.domain.User;
import java.util.Date;

/**
 *
 * @author S2028873
 */
public class ClientHelper {

    public static Client addClient(String clientId) {
        Client client = new Client();
        client.setCreatedBy("Test");
        client.setCreatedDate(new Date());
        client.setPersonType(PersonType.CLIENT);
        client.setClientId(clientId);
        client.setFirstName("Amogelang");
        client.setLastName("Phangisa");
        client.setEmail("amogelangphangi@gmail.com");
        client.setCellphoneNumber("0837352893");
        client.setIdentityNumber("9303105724044");

        return client;
    }

    public static User addUser(String UserName, String password, String confirmPassword, String changePassword, SystemUserStatus systemUserStatus) {

        User user = new User();
        user.setCreatedBy("Test");
        user.setCreatedDate(new Date());
        user.setPersonType(PersonType.ADMINISTRATOR);
        user.setUserName(UserName);
        user.setPassword(password);
        user.setChangePassword(changePassword);
        user.setConfirmPassword(confirmPassword);
        user.setSystemUserStatus(systemUserStatus);
        user.setFirstName("Amo");
        user.setLastName("Park");
        user.setEmail("amogelangphangi@gmail.com");
        user.setCellphoneNumber("0837352893");
        user.setIdentityNumber("9309105724088");
        user.setIdentifer(user.getIdentityNumber());

        return user;
    }
}
