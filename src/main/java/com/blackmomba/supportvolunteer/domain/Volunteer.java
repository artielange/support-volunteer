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
@Entity(name = "benevole")
public class Volunteer {

    @Id
    @Column(name = "NASBenevole")
    private String sin;

    @Column(name = "NomBenevole")
    private String lastName;

    @Column(name = "PrenomBenevole")
    private String firstName;

    @Column(name = "AdresseBenevole")
    private String address;

    @Column(name = "DispoBenevole")
    private boolean available;

    @Column(name = "NumSecteur")
    private Long sectorId;

}
