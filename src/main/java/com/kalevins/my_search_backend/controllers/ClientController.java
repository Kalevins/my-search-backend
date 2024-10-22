package com.kalevins.my_search_backend.controllers;

import com.kalevins.my_search_backend.models.dtos.ClientDto;
import com.kalevins.my_search_backend.models.dtos.IdentificationDto;
import com.kalevins.my_search_backend.models.entities.Client;
import com.kalevins.my_search_backend.services.interfaces.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientInterface clientService;

    /* GETS */

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> showAll () {
        List<Client> clients = clientService.findAll();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDto> showById (@PathVariable Integer id) {
        try {
            ClientDto client = clientService.findById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/client")
    public ResponseEntity<ClientDto> showByIdentification (@RequestBody IdentificationDto identificationDto) {
        try {
            ClientDto client = clientService.findByIdentification(identificationDto);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /* POSTS */

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientDto> create (@RequestBody Client client) {
        if(!Objects.equals(client.getTypeIdentification(), "C") && !Objects.equals(client.getTypeIdentification(), "P")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        IdentificationDto identificationDto = IdentificationDto.builder()
                .type(client.getTypeIdentification())
                .number(client.getIdentification())
                .build();
        try {
            ClientDto clientDto = clientService.findByIdentification(identificationDto);
            return new ResponseEntity<>(clientDto, HttpStatus.CONFLICT);
        } catch (Exception e) {
            clientService.save(client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /* PUTS */

    @PutMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientDto> update (@RequestBody Client client) {
        if(!clientService.existsById(client.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(!Objects.equals(client.getTypeIdentification(), "C") && !Objects.equals(client.getTypeIdentification(), "P")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        IdentificationDto identificationDto = IdentificationDto.builder()
                .type(client.getTypeIdentification())
                .number(client.getIdentification())
                .build();
        try {
            ClientDto clientDto = clientService.findByIdentification(identificationDto);
            return new ResponseEntity<>(clientDto, HttpStatus.CONFLICT);
        } catch (Exception e) {
            clientService.save(client);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /* DELETES */

    @DeleteMapping("/client/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete (@PathVariable Integer id) {
        try {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
