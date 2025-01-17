/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.momentum.calculator.service;

import com.momentum.calculator.domain.Client;
import java.util.List;
/**
 *
 * @author S2028873
 */
public interface ClientServiceLocal {

    Client save(Client client);

    Client findById(Long id);

    Client update(Client client);

    void deleteAll();

    Client deleteById(Long id);

    List<Client> listAll();

    boolean isExist(Client client);

    Client findUserByClientId(String clientId);
}
