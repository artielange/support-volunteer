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
    private String nasClient;

    @Column
    private String nomClient;

    @Column
    private String prenomClient;

    @Column
    private String dateNaissance;

    @Column
    private String numSecteur;

}
