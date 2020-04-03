package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "commentaire")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumCommentaire", updatable = false, nullable = false)
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
