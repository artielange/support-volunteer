package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "voiture")
public class Vehicle {

    @Id
    @Column(name = "NoImmatriculation")
    private String registrationNumber;

    @Column(name = "MarqueVehicule")
    private String make;

    @Column(name = "AnneeVehicule")
    private String manufacturingYear;

    @Column(name = "TypeVehicule")
    private String model;

    @Column(name = "NASBenevole")
    private String volunteerSin;

    @Column(name = "IDEquipe")
    private Long teamId;

    @Override
    public String toString() {
        return String.format("%-20s %-10s %-10s %-5s %-11s",
                registrationNumber, make, model, manufacturingYear, volunteerSin);
    }

}
