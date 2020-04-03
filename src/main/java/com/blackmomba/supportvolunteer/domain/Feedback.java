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
@Entity(name = "commentaire")
public class Feedback {

    @Id
    @Column(name = "NumCommentaire")
    private Long id;

    @Column(name = "DateCommentaire")
    private String feedbackDate;

    @Column(name = "HeureCommentaiClientNASClientre")
    private String feedbackTime;

    @Column(name = "TypeCommentaire")
    private String feedbackType;

    @Column(name = "NASClient")
    private String clientSin;

}
