package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "accompagnement")
public class SupportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAccomp", updatable = false, nullable = false)
    private Long id;

    @Column(name = "DateAccomp")
    private String eventDate;

    @Column(name = "HeureDebutAccomp")
    private String startTime;

    @Column(name = "HeureFinAccomp")
    private String endTime;

    @Column(name = "TypeAccomp")
    private String supportType;

    @Column(name = "NASBenevole")
    private String volunteerSin;

    @Column(name = "NASClient")
    private String clientSin;

    @Column(name = "IDEquipe")
    private String teamId;

}
