/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author S2028873
 */
@ManagedBean
@RequestScoped
public class HeaderBean extends BaseBean {

    @PostConstruct
    public void init() {

    }

    public void logout() {

        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(false);

            if (session != null) {
                session.invalidate();
            }
            externalContext.redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(HeaderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
