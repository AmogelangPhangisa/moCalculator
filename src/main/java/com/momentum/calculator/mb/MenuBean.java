/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author S2028873
 */
@ManagedBean
@RequestScoped
public class MenuBean extends BaseBean {
    
    @PostConstruct
    public void init(){
    }
    public String route(String page){
    
        System.out.println("selected page = "+ page);
        return defaultRouting(page);
    }
    
   public String routing(String page){
   
    getActiveUser().setModuleWelcomeMessage("welcome to user");
    
    return defaultRouting(page);
   }
    
}

