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
@Entity
public class Vehicle {

    @Id
    private String registrationNumber;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private String manufacturingYear;

    @Column
    private String volunteerSin;

    @Column
    private String teamId;

}
