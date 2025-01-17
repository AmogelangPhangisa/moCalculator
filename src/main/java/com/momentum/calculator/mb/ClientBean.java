/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.mb;

import com.momentum.calculator.common.ClientType;
import com.momentum.calculator.common.PersonType;
import com.momentum.calculator.domain.Client;
import com.momentum.calculator.service.ClientServiceLocal;
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
public class ClientBean extends BaseBean<Client> {

    @Autowired
    private ClientServiceLocal clientService;
    private List<ClientType> clientTypes = new ArrayList<>();
    private List<PersonType> personTypes = new ArrayList<>();
    private boolean clientVisible;
    
    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("Client");
        addCollections(clientService.listAll());
        clientTypes.addAll(Arrays.asList(ClientType.values()));
        personTypes.addAll(Arrays.asList(PersonType.values()));
        clientVisible = Boolean.FALSE;
    }

  
    public void addOrUpdate(Client client) {
        reset().setAdd(true);
        if (client != null) {
            setPanelTitleName("Update Client");
            client.setUpdateDate(new Date());
            client.setUpdatedBy(getActiveUser().getFirstName() + " "+ getActiveUser().getLastName());
        } else {
            client = new Client();
            setPanelTitleName("Add new Client");
            client.setCreatedBy(getActiveUser().getFirstName() + " "+ getActiveUser().getLastName());
            client.setCreatedDate(new Date());
            client.setPersonType(PersonType.CLIENT);


            addCollection(client);
        }
        addEntity(client);
    }
    public void save(Client client) {
        if (client.getId() != null ) {
            clientService.update(client);
            addInformationMessage("Client was successfully updated");
        } else {
            clientService.save(client);
            addInformationMessage("Client was successfully created");
        }
        reset().setList(true);
        setPanelTitleName("Client");
    }

    public void cancel(Client client) {
        if (client.getId() == null && getClients().contains(client)) {
            remove(client);
        }
        reset().setList(true);
        setPanelTitleName("clients");
    }

    public void delete(Client client) {
 
       clientService.deleteById(client.getId());
        sychronizeClient(client);
        addInformationMessage("Client was successully deleted");
        reset().setList(true);
    }

    public void sychronizeClient(Client client) {
        if (getCollections().contains(client)) {
            getCollections().remove(client);
        }
    }

    public List<Client> getClients() {
        return this.getCollections();
    }

    public List<ClientType> getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(List<ClientType> clientTypes) {
        this.clientTypes = clientTypes;
    }


    public List<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }


    public boolean isClientVisible() {
        return clientVisible;
    }

    public void setClientVisible(boolean clientVisible) {
        this.clientVisible = clientVisible;
    }
}
