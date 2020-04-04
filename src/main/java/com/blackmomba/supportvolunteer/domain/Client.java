package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    private Date dateOfBirth;

    @Column(name = "AdresseClient")
    private String address;

    @Column(name = "NumSecteur")
    private long sectorId;

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return String.format("%-12s %-20s%-20s %-20s %-40s %-1s",
                sin, firstName, lastName, simpleDateFormat.format(dateOfBirth), address, sectorId);
    }

}
