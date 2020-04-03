package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "client")
public class Client {

    @Id
    @Column(name = "NASClient")
    private String sin;

    @Column(name = "NomClient")
    private String lastName;

    @Column(name = "PrenomClient")
    private String firstName;

    @Column(name = "DateNaissance")
    private String dateOfBirth;

    @Column(name = "AdresseClient")
    private String address;

    @Column(name = "NumSecteur")
    private long sectorId;

}
