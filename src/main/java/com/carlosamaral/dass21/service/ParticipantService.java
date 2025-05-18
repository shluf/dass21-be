package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.model.ParticipantModel;
import com.carlosamaral.dass21.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipantService {
    private final ParticipantRepository repository;
    private final ResponseService responseService;
    public ParticipantService(ParticipantRepository repository, ResponseService responseService) {
        this.repository = repository;
        this.responseService = responseService;
    }

    public Boolean participantIsValid(ParticipantModel participantModel, Boolean mustCheckId) {
        if (mustCheckId && participantModel.getId() <= 0) return false;

        var nameIsEmpty = participantModel.getName().isEmpty();
        var ageIsLessThanZero = participantModel.getAge() <= 0;
        var ageIsGreaterThanMax = participantModel.getAge() >= 999;
        var genderIsValid = Objects.equals(participantModel.getGender().toUpperCase(), "MALE") || Objects.equals(participantModel.getGender().toUpperCase(), "FEMALE");
        return !nameIsEmpty && !ageIsLessThanZero && !ageIsGreaterThanMax && genderIsValid;
    }

    public ParticipantModel save(ParticipantModel participantModel) {
        return this.repository.save(participantModel);
    }

    public Optional<ParticipantModel> findById(Long id) {
        return this.repository.findById(id);
    }

    public List<ParticipantModel> findAll() {
        return this.repository.findAll();
    }

    public ParticipantModel update(ParticipantModel body) {
        return this.repository.save(body);
    }

    public void deleteById(Long id) {
        this.responseService.deleteByParticipantId(id);
        this.repository.deleteById(id);
    }
}
