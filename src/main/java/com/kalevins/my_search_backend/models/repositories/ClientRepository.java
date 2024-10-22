package com.kalevins.my_search_backend.models.repositories;

import com.kalevins.my_search_backend.models.dtos.IdentificationDto;
import com.kalevins.my_search_backend.models.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findAll();

    @Query("SELECT client FROM Client client WHERE client.typeIdentification = :#{#identificationDto.type} AND client.identification = :#{#identificationDto.number}")
    Client findByIdentification(IdentificationDto identificationDto);
}
