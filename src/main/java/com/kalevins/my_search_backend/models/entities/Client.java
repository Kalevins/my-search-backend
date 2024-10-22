package com.kalevins.my_search_backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "typeIdentification")
    private String typeIdentification;

    @Column(name = "identification")
    private Long identification;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "first_last_name")
    private String firstLastName;

    @Column(name = "second_last_name")
    private String secondLastName;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;
}
