/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import com.momentum.calculator.common.PersonType;
import com.momentum.calculator.common.SystemUserStatus;
import com.momentum.calculator.domain.Client;
import com.momentum.calculator.domain.User;
import com.momentum.calculator.service.ClientServiceLocal;
import com.momentum.calculator.service.UserServiceLocal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author S2028873
 */
@ManagedBean
@ViewScoped
public class UserBean extends BaseBean<User> {

    @Autowired
    private ClientServiceLocal clientService;
    
    @Autowired
    private UserServiceLocal userService;

    private List<Client> clients = new ArrayList<>();
    private List<PersonType> personTypes = new ArrayList<>();
    private List<SystemUserStatus> systemUserStatus = new ArrayList<>();

    private Client client;
    private boolean clientVisible;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("User");
        addCollections(userService.listAll());
        clients = clientService.listAll();
        personTypes.addAll(Arrays.asList(PersonType.values()));
        systemUserStatus.addAll(Arrays.asList(SystemUserStatus.values()));
        clientVisible = Boolean.FALSE;
    }

    public void addOrUpdate(User user) {
        reset().setAdd(true);
        if (user != null) {
            setPanelTitleName("Update User");
            
            if (user.getPersonType().equals(PersonType.ADMINISTRATOR) || user.getPersonType().equals(PersonType.CLIENT)) {
                clientVisible = Boolean.TRUE;
                client = clientService.findUserByClientId(user.getIdentifer());
            }
            user.setUpdateDate(new Date());
            user.setUpdatedBy(getActiveUser().getIdentifier());
        } else {
            setPanelTitleName("Add new User");
            user = new User();
            user.setCreatedBy(getActiveUser().getIdentifier());
            user.setCreatedDate(new Date());

            addCollection(user);
        }
        addEntity(user);
    }

   public void save(User user) {
        if (user.getId() != null) {
            userService.update(user);
            addInformationMessage("User  was successfully updated.");
        } else {
            userService.save(user);
            addInformationMessage("User was successfully created.");
        }
        reset().setList(true);
        setPanelTitleName("User");
    }
   
    public void cancel(User user) {
        if (user.getId() == null && getUsers().contains(user)) {
            remove(user);
        }
        reset().setList(true);
        setPanelTitleName("Users");
    }

    public void delete(User user) {
        userService.deleteById(user.getId());
        synchronizeUser(user);
        addInformationMessage("User was successfully deleted");
        reset().setList(true);
    }

    public void synchronizeUser(User user) {
        if (getCollections().contains(user)) {
            getCollections().remove(user);
        }
    }

    public List<String> getIdentifier() {
        List<String> identifiers = new ArrayList<>();
        for (User user : userService.listAll()) {
            identifiers.add(user.getIdentifer());
        }
        return identifiers;
    }

    public void systemUserListener() {
        if (getEntity().getPersonType().equals(PersonType.CLIENT) || getEntity().getPersonType().equals(PersonType.ADMINISTRATOR)) {
            clientVisible = Boolean.TRUE;
        }

    }

    public List<User> getUsers() {
        return this.getCollections();
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public List<SystemUserStatus> getSystemUserStatus() {
        return systemUserStatus;
    }

    public void setSystemUserStatus(List<SystemUserStatus> systemUserStatus) {
        this.systemUserStatus = systemUserStatus;
    }

    public boolean isClientVisible() {
        return clientVisible;
    }

    public void setClientVisible(boolean clientVisible) {
        this.clientVisible = clientVisible;
    }
}
