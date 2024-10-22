package com.kalevins.my_search_backend.controllers;

import com.kalevins.my_search_backend.models.dtos.ClientDto;
import com.kalevins.my_search_backend.models.dtos.IdentificationDto;
import com.kalevins.my_search_backend.models.entities.Client;
import com.kalevins.my_search_backend.services.interfaces.ClientInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientControllerTest {
    @Mock
    private ClientInterface clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowAll() {
        when(clientService.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Client>> response = clientController.showAll();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Client client = new Client();
        when(clientService.findAll()).thenReturn(List.of(client));
        response = clientController.showAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testShowById() {
        ClientDto clientDto =  ClientDto.builder().build();
        when(clientService.findById(1)).thenReturn(clientDto);
        ResponseEntity<ClientDto> response = clientController.showById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody());

        when(clientService.findById(2)).thenThrow(new RuntimeException());
        response = clientController.showById(2);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testShowByIdentification() {
        IdentificationDto identificationDto = IdentificationDto.builder().build();
        ClientDto clientDto = ClientDto.builder().build();
        when(clientService.findByIdentification(identificationDto)).thenReturn(clientDto);
        ResponseEntity<ClientDto> response = clientController.showByIdentification(identificationDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody());

        when(clientService.findByIdentification(identificationDto)).thenThrow(new RuntimeException());
        response = clientController.showByIdentification(identificationDto);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testCreate() {
        Client client = new Client();
        client.setTypeIdentification("C");
        client.setIdentification(123L);

        IdentificationDto identificationDto = IdentificationDto.builder()
                .type(client.getTypeIdentification())
                .number(client.getIdentification())
                .build();

        when(clientService.findByIdentification(identificationDto)).thenThrow(new RuntimeException());
        ResponseEntity<ClientDto> response = clientController.create(client);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        client.setTypeIdentification("X");
        response = clientController.create(client);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    void testUpdate() {
        Client client = new Client();
        client.setId(1);
        client.setTypeIdentification("C");
        client.setIdentification(123L);

        when(clientService.existsById(1)).thenReturn(true);
        IdentificationDto identificationDto = IdentificationDto.builder()
                .type(client.getTypeIdentification())
                .number(client.getIdentification())
                .build();

        when(clientService.findByIdentification(identificationDto)).thenThrow(new RuntimeException());
        ResponseEntity<ClientDto> response = clientController.update(client);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        when(clientService.existsById(2)).thenReturn(false);
        client.setId(2);
        response = clientController.update(client);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDelete() {
        doNothing().when(clientService).deleteById(1);
        ResponseEntity response = clientController.delete(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        doThrow(new RuntimeException()).when(clientService).deleteById(2);
        response = clientController.delete(2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
