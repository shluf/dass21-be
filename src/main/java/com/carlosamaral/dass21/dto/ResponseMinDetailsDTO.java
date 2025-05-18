package com.carlosamaral.dass21.dto;

import java.time.Instant;

public class ResponseMinDetailsDTO {
    private Long responseId;
    public Instant responseDate;
    private Integer totalAnxietyScore;
    private Integer totalDepressionScore;
    private Integer totalStressScore;

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Integer getTotalAnxietyScore() {
        return totalAnxietyScore;
    }

    public void setTotalAnxietyScore(Integer totalAnxietyScore) {
        this.totalAnxietyScore = totalAnxietyScore;
    }

    public Integer getTotalDepressionScore() {
        return totalDepressionScore;
    }

    public void setTotalDepressionScore(Integer totalDepressionScore) {
        this.totalDepressionScore = totalDepressionScore;
    }

    public Integer getTotalStressScore() {
        return totalStressScore;
    }

    public void setTotalStressScore(Integer totalStressScore) {
        this.totalStressScore = totalStressScore;
    }

    public Instant getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }
}
