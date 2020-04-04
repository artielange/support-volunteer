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

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return String.format("%-6s %-20s %-20s %-12s",
                id, simpleDateFormat.format(feedbackDateTime), feedbackType, clientSin);
    }

}
