package com.kalevins.my_search_backend.services.implementations;

import com.kalevins.my_search_backend.models.dtos.ClientDto;
import com.kalevins.my_search_backend.models.dtos.IdentificationDto;
import com.kalevins.my_search_backend.models.repositories.ClientRepository;
import com.kalevins.my_search_backend.models.entities.Client;
import com.kalevins.my_search_backend.services.interfaces.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientImplementation implements ClientInterface {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    @Override
    public ClientDto save(Client client) {
        clientRepository.save(client);
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .secondName(client.getSecondName())
                .firstLastName(client.getFirstLastName())
                .secondLastName(client.getSecondLastName())
                .phone(client.getPhone())
                .address(client.getAddress())
                .city(client.getCity())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto findById(Integer id) {
        Client client = clientRepository.findById(id).orElse(null);
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .secondName(client.getSecondName())
                .firstLastName(client.getFirstLastName())
                .secondLastName(client.getSecondLastName())
                .phone(client.getPhone())
                .address(client.getAddress())
                .city(client.getCity())
                .build();
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Integer id) {
        return clientRepository.existsById(id);
    }

    @Override
    public ClientDto findByIdentification(IdentificationDto identificationDto) {
        Client client = clientRepository.findByIdentification(identificationDto);
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .secondName(client.getSecondName())
                .firstLastName(client.getFirstLastName())
                .secondLastName(client.getSecondLastName())
                .phone(client.getPhone())
                .address(client.getAddress())
                .city(client.getCity())
                .build();
    }

}
