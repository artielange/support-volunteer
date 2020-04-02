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
public class Team {

    @Id
    private long id;

    @Column
    private boolean available;

    @Column
    private String status;

    @Column
    private String volunteerSin;

    @Column
    private long sectorId;

}
