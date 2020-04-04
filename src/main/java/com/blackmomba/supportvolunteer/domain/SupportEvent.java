package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return String.format("%-6s %-20s %-20s %-30s %-12s %-12s %-6s",
                id, simpleDateFormat.format(startTime), simpleDateFormat.format(endTime),
                supportType, volunteerSin, clientSin, teamId);
    }

}
