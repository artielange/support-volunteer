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
public class Feedback {

    @Id
    private long id;

    @Column
    private String feedbackDate;

    @Column
    private String feedbackTime;

    @Column
    private String feedbackType;

    @Column
    private String clientSin;

}
