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
@Entity(name = "demande")
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumDemande", updatable = false, nullable = false)
    private Long id;

    @Column(name = "DateHeureDemande")
    private Date requestDate;

    @Column(name = "TypeAccomp")
    private String supportType;

    @Column(name = "NASClient")
    private String clientSin;

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return String.format("%-6s %-20s %-30s %-11s",
                id, simpleDateFormat.format(requestDate), supportType, clientSin);
    }

}
