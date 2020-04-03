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
@Entity(name = "voiture")
public class Vehicle {

    @Id
    @Column(name = "NoImmatriculation")
    private String registrationNumber;

    @Column(name = "MarqueVehicule")
    private String make;

    @Column(name = "AnneeVehicule")
    private String model;

    @Column(name = "TypeVehicule")
    private String manufacturingYear;

    @Column(name = "NASBenevole")
    private String volunteerSin;

    @Column(name = "IDEquipe")
    private String teamId;

}
