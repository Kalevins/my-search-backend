package com.kalevins.my_search_backend.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private Long phone;
    private String address;
    private String city;
}
