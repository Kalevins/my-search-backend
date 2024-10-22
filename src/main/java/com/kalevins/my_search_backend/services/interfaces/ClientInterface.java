package com.kalevins.my_search_backend.services.interfaces;

import com.kalevins.my_search_backend.models.dtos.ClientDto;
import com.kalevins.my_search_backend.models.dtos.IdentificationDto;
import com.kalevins.my_search_backend.models.entities.Client;

import java.util.List;

public interface ClientInterface {
    ClientDto save(Client client);

    ClientDto findById(Integer id);

    List<Client> findAll();

    void deleteById(Integer id);

    boolean existsById(Integer id);

    ClientDto findByIdentification(IdentificationDto identificationDto);
}
