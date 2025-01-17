/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.momentum.calculator.service;

import com.momentum.calculator.domain.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.momentum.calculator.persistence.ClientRepository;
/**
 *
 * @author S2028873
 */
@Service
public class ClientService implements ClientServiceLocal {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("the requested id[ " + id + "] does not exist."));
    }

    @Override
    public Client update(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public Client deleteById(Long id) {
        Client client = findById(id);
        if (client != null) {
            clientRepository.deleteById(id);
        }
        return client;
    }

    @Override
    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    @Override
    public boolean isExist(Client client) {
        return clientRepository.findById(client.getId()) != null;
    }

    @Override
    public Client findUserByClientId(String clientId) {
    return clientRepository.findUserByClientId(clientId);
    }
}
