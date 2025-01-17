/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import com.momentum.calculator.common.PersonType;
import com.momentum.calculator.common.SystemUserStatus;
import com.momentum.calculator.domain.User;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author S2028873
 */
@ManagedBean
@SessionScoped
@Getter
@Setter
public class ActiveUserBean implements Serializable {

    private String username;
    private PersonType personType;
    private SystemUserStatus systemUserStatus;
    private String moduleWelcomeMessage;
    private String firstName;
    private String lastName;
    private String identifier;
    private boolean userLoginIndicator;
    private Router router = new Router();

   
    private boolean administrator;

    public ActiveUserBean() {userLoginIndicator = Boolean.FALSE;}

    public void setLoggedOnUserSession(User user) {

        if (user.getId() != null) {
            this.setUsername(user.getUserName());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setPersonType(user.getPersonType());
            this.setSystemUserStatus(user.getSystemUserStatus());
            this.setIdentifier(user.getIdentifer());
            this.setUserLoginIndicator(true);
        }
    }

    public ActiveUserBean reset() {

       
        setAdministrator(false);
        
        return this;
    }
}