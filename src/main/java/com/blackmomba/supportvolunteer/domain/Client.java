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
public class Client {

    @Id
    private String sin;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String dateOfBirth;

    @Column
    private String address;

    @Column
    private long sectorId;

}
