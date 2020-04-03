package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "demande")
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumDemande", updatable = false, nullable = false)
    private Long id;

    @Column(name = "HeureDemande")
    private String time;

    @Column(name = "TypeAccomp")
    private String supportType;

    @Column(name = "NASClient")
    private String clientSin;

}
