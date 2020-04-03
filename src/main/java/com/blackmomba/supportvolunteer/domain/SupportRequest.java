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
@Entity(name = "demande")
public class SupportRequest {

    @Id
    @Column(name = "NumDemande")
    private Long id;

    @Column(name = "HeureDemande")
    private String time;

    @Column(name = "TypeAccomp")
    private String supportType;

    @Column(name = "NASClient")
    private String clientSin;

}
