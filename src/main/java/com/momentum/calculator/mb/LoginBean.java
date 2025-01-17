/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import com.momentum.calculator.common.PersonType;
import com.momentum.calculator.common.SystemUserStatus;
import com.momentum.calculator.domain.User;
import com.momentum.calculator.service.UserServiceLocal;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
/**
 *
 * @author S2028873
 */
@ManagedBean
@RequestScope
public class LoginBean extends BaseBean {

    @Autowired
    private UserServiceLocal userService;

    private String username;
    private String password;

    public void login() {

        User user = userService.findUserByUserNameAndPassword(username, password);

        if (user != null) {

            if (user.getPersonType().equals(PersonType.ADMINISTRATOR) && user.getSystemUserStatus().equals(SystemUserStatus.ACTIVE)) {
                getActiveUserBean().setModuleWelcomeMessage("welcome to admin console");
                getActiveUser().getRouter().reset().setAdminstrator(true);
                getActiveUser().setLoggedOnUserSession(user);
                redirect("landingPage");
            }  
            else if(user.getPersonType().equals(PersonType.CLIENT) && user.getSystemUserStatus().equals(SystemUserStatus.ACTIVE)){
            getActiveUserBean().setModuleWelcomeMessage("welcome to user console");
                getActiveUser().getRouter().reset().setAdminstrator(true);
                getActiveUser().setLoggedOnUserSession(user);
                redirect("calculatorLandingPage");
            
            }
//            else {
//                addErrorMessage("The user with the username " + username + " is not authorised to use the system");
//               // redirect("login");
//            }
        }
         else {
                addErrorMessage("The user with the username " + username + " is not authorised to use the system");
               // redirect("login");
            }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
