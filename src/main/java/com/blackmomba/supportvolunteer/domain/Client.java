package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client {

    private String nasClient;
    private String nomClient;
    private String prenomClient;
    private String dateNaissance;
    private String numSecteur;

}
