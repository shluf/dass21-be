package com.carlosamaral.dass21.dto;

import java.util.List;

public class UpdateResponseAndParticipantDTO {
    public Long participantId;
    public String name;
    public Integer age;
    public String gender;
    public List<ResponseMinDetailsDTO> responses;

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<ResponseMinDetailsDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseMinDetailsDTO> responses) {
        this.responses = responses;
    }
}
