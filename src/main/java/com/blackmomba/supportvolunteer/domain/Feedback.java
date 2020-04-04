package com.blackmomba.supportvolunteer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "commentaire")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumCommentaire", updatable = false, nullable = false)
    private Long id;

    @Column(name = "DateHeureCommentaire")
    private Date feedbackDateTime;

    @Column(name = "TypeCommentaire")
    private String feedbackType;

    @Column(name = "NASClient")
    private String clientSin;

}
