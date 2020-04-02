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
public class SupportEvent {

    @Id
    private long id;

    @Column
    private String eventDate;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String supportType;

    @Column
    private String volunteerSin;

    @Column
    private String clientSin;

    @Column
    private String teamId;

}
