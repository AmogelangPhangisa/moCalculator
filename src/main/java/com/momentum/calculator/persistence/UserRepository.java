/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.momentum.calculator.persistence;

import com.momentum.calculator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author S2028873
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findUserByUserNameAndPassword(String userName,String password);
    
    User findUserByUserName(String userName);

     User findUserByIdentifer(String identifer);
}