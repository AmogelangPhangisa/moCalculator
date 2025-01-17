/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;

import java.io.IOException;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
/**
 *
 * @author S2028873
 */
public class BaseBean<T> extends SpringBeanAutowiringSupport implements Serializable {

    @ManagedProperty(value = "#{activeUserBean}")
    private ActiveUserBean activeUserBean;
    private List<String> errorCollectMag = new ArrayList<>();
    private boolean list;
    private boolean add;
    private boolean update;
    private boolean search;
    private List<T> collections = new ArrayList<>();
    T entity;
    private String comfirmMassage;
    private String panelTitleName;

    final Logger LOG = Logger.getLogger(BaseBean.class.getName());

    public void redirect(String page) {
        try {
            StringBuilder builder = new StringBuilder(page);
            builder.append(".xhtml");
            FacesContext.getCurrentInstance().getExternalContext().redirect(builder.toString());
        } catch (IOException ex) {
            Logger.getLogger(BaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String defaultRouting(String page) {
        StringBuilder builder = new StringBuilder(page);
        builder.append(".xhtml");
        return builder.toString();
    }

    public void addInformationMessage(String detail) {
        FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", detail);
        FacesContext.getCurrentInstance().addMessage(null, massage);
    }

    public void addErrorMessage(String detail) {
        FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", detail);
        FacesContext.getCurrentInstance().addMessage(null, massage);
    }

    public void addWarningMessage(String detail) {
        FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "", detail);
        FacesContext.getCurrentInstance().addMessage(null, massage);
    }

    public BaseBean reset() {
        setList(false);
        setAdd(false);
        setUpdate(false);
        setSearch(false);
        return this;
    }

    //generic methods
    public void addEntity(T entity) {
        this.entity = entity;
    }

    public void remove(T entity) {
        collections.remove(entity);
    }

    public void addCollections(List<T> list) {
        collections.clear();
        collections.addAll(list);
    }

    public void synchronize(T entity) {
        if (collections.contains(entity)) {
            collections.remove(entity);
        }
    }

    public void updateCollections(List<T> list) {
        collections.addAll(list);
    }

    public void addCollection(T entity) {
        collections.add(0, entity);
    }

    public void addCollections(Set<T> list) {
        collections.clear();
        collections.addAll(list);
    }

    //end of generics
    public List<String> getErrorCollectMag() {
        return errorCollectMag;
    }

    public void setErrorCollectMag(List<String> errorCollectMag) {
        this.errorCollectMag = errorCollectMag;
    }

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public List<T> getCollections() {
        return collections;
    }

    public void setCollections(List<T> collections) {
        this.collections = collections;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getComfirmMassage() {
        return comfirmMassage;
    }

    public void setComfirmMassage(String comfirmMassage) {
        this.comfirmMassage = comfirmMassage;
    }

    public String getPanelTitleName() {
        return panelTitleName;
    }

    public void setPanelTitleName(String panelTitleName) {
        this.panelTitleName = panelTitleName;
    }

    public ActiveUserBean getActiveUserBean() {
        return activeUserBean;
    }

    public void setActiveUserBean(ActiveUserBean activeUserBean) {
        this.activeUserBean = activeUserBean;
    }

    public ActiveUserBean getActiveUser() {
        return activeUserBean;
    }

    public void setActiveUser(ActiveUserBean activeUserBean) {
        this.activeUserBean = activeUserBean;
    }
}
