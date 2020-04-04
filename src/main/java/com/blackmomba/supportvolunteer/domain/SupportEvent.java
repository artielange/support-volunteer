package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "accompagnement")
public class SupportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAccomp", updatable = false, nullable = false)
    private Long id;

    @Column(name = "DebutAccomp")
    private Date startTime;

    @Column(name = "FinAccomp")
    private Date endTime;

    @Column(name = "TypeAccomp")
    private String supportType;

    @Column(name = "NASBenevole")
    private String volunteerSin;

    @Column(name = "NASClient")
    private String clientSin;

    @Column(name = "IDEquipe")
    private String teamId;

}
