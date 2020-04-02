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
public class SupportRequest {

    @Id
    private long id;

    @Column
    private String time;

    @Column
    private String supportType;

    @Column
    private String clientSin;

}
