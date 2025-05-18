package com.carlosamaral.dass21.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "responses")
public class ResponseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_id")
    private ParticipantModel participantModel;

    @NotNull
    @Column(name = "response_date", nullable = false)
    private Instant responseDate;

    @NotNull
    @Column(name = "total_depression_score", nullable = false)
    private Integer totalDepressionScore;

    @NotNull
    @Column(name = "total_anxiety_score", nullable = false)
    private Integer totalAnxietyScore;

    @NotNull
    @Column(name = "total_stress_score", nullable = false)
    private Integer totalStressScore;

    public Long getId() {
        return id;
    }

    public ParticipantModel getParticipant() {
        return participantModel;
    }

    public Instant getResponseDate() {
        return responseDate;
    }

    public Integer getTotalDepressionScore() {
        return totalDepressionScore;
    }

    public Integer getTotalAnxietyScore() {
        return totalAnxietyScore;
    }

    public Integer getTotalStressScore() {
        return totalStressScore;
    }

}