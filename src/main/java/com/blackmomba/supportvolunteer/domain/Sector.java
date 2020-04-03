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
@Entity(name = "secteur")
public class Sector {

    @Id
    @Column(name = "NumSecteur")
    private Long id;

    @Column(name = "NomSecteur")
    private String name;

    @Column(name = "CodePostal")
    private String postalCode;

}
