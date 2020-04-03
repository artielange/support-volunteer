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
@Entity(name = "equipe")
public class Team {

    @Id
    @Column(name = "IDEquipe")
    private Long id;

    @Column(name = "DispoEquipe")
    private boolean available;

    @Column(name = "StatutEquipe")
    private String status;

    @Column(name = "NASBenevole")
    private String volunteerSin;

    @Column(name = "NumSecteur")
    private Long sectorId;

}
