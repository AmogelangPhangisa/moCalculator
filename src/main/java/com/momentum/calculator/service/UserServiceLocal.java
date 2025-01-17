/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.momentum.calculator.service;

import com.momentum.calculator.domain.User;
import java.util.List;
/**
 *
 * @author S2028873
 */
public interface UserServiceLocal {

    User save(User user);

    User findById(Long id);

    User update(User user);

    void deleteAll();

    User deleteById(Long id);

    List<User> listAll();

    boolean isExist(User user);

    User findUserByUserNameAndPassword(String userName, String password);
    
    User findUserByUserName(String userName);


    User findUserByIdentifer(String identifer);
}
