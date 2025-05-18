package com.carlosamaral.dass21.dto;


import java.time.Instant;

public class UpdateResponseDTO {
    public Long responseId;
    public Long participantId;
    public Instant responseDate;
    public Integer totalDepressionScore;
    public Integer totalAnxietyScore;
    public Integer totalStressScore;

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

    public Long getResponseId() {
        return responseId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    public void setTotalDepressionScore(Integer totalDepressionScore) {
        this.totalDepressionScore = totalDepressionScore;
    }

    public void setTotalAnxietyScore(Integer totalAnxietyScore) {
        this.totalAnxietyScore = totalAnxietyScore;
    }

    public void setTotalStressScore(Integer totalStressScore) {
        this.totalStressScore = totalStressScore;
    }
}
