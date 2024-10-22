package com.kalevins.my_search_backend.controllers;

import com.kalevins.my_search_backend.models.entities.Client;
import com.kalevins.my_search_backend.services.interfaces.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ClientInterface clientService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataLoader.run");

        Client client = new Client();
        client.setTypeIdentification("C");
        client.setIdentification(23445322L);
        client.setFirstName("Kevin");
        client.setSecondName("");
        client.setFirstLastName("Munoz");
        client.setSecondLastName("Rengifo");
        client.setPhone(3124567890L);
        client.setAddress("Calle 123");
        client.setCity("Bogota");
        clientService.save(client);
    }
}
