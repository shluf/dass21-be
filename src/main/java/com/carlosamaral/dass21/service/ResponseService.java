package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.dto.ResponseDetailsDTO;
import com.carlosamaral.dass21.dto.SaveResponseDTO;
import com.carlosamaral.dass21.dto.UpdateResponseDTO;
import com.carlosamaral.dass21.enums.SymptomTypeEnum;
import com.carlosamaral.dass21.model.ParticipantModel;
import com.carlosamaral.dass21.model.ResponseModel;
import com.carlosamaral.dass21.repository.ParticipantRepository;
import com.carlosamaral.dass21.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private final SymptomService symptomService;
    private final ParticipantRepository participantRepository;
    public ResponseService(ResponseRepository responseRepository, SymptomService symptomService, ParticipantRepository participantRepository) {
        this.responseRepository = responseRepository;
        this.symptomService = symptomService;
        this.participantRepository = participantRepository;
    }

    public ResponseDetailsDTO toDetailsDTO(ResponseModel resp) {
        ResponseDetailsDTO dto = new ResponseDetailsDTO();
        dto.responseId = resp.getId();
        dto.participantId = resp.getParticipant().getId();
        dto.age = resp.getParticipant().getAge();
        dto.gender = resp.getParticipant().getGender();
        dto.responseDate = resp.getResponseDate();

        dto.totalAnxietyScore = resp.getTotalAnxietyScore();
        dto.anxietyScale = this.symptomService.getScaleByType(
                SymptomTypeEnum.ANXIETY,
                resp.getTotalAnxietyScore()
        ).getDescription();

        dto.totalDepressionScore = resp.getTotalDepressionScore();
        dto.depressionScale = this.symptomService.getScaleByType(
                SymptomTypeEnum.DEPRESSION,
                resp.getTotalDepressionScore()
        ).getDescription();

        dto.totalStressScore = resp.getTotalStressScore();
        dto.stressScale = this.symptomService.getScaleByType(
                SymptomTypeEnum.STRESS,
                resp.getTotalStressScore()
        ).getDescription();

        return dto;
    }

    private Boolean defaultValidationIsOk(Optional<SaveResponseDTO> saveResponseDTO, Optional<UpdateResponseDTO> updateResponseDTO) {
        boolean responseDateIsNotNull = false;
        boolean totalDepressionScoreIsValid = false;
        boolean totalAnxietyScoreIsValid = false;
        boolean totalStressScoreIsValid = false;

        if (saveResponseDTO.isPresent()) {
            responseDateIsNotNull = saveResponseDTO.get().getResponseDate() != null;
            totalDepressionScoreIsValid = saveResponseDTO.get().getTotalDepressionScore() >= 0;
            totalAnxietyScoreIsValid = saveResponseDTO.get().getTotalAnxietyScore() >= 0;
            totalStressScoreIsValid = saveResponseDTO.get().getTotalStressScore() >= 0;
        }
        if (updateResponseDTO.isPresent()) {
            responseDateIsNotNull = updateResponseDTO.get().getResponseDate() != null;
            totalDepressionScoreIsValid = updateResponseDTO.get().getTotalDepressionScore() >= 0;
            totalAnxietyScoreIsValid = updateResponseDTO.get().getTotalAnxietyScore() >= 0;
            totalStressScoreIsValid = updateResponseDTO.get().getTotalStressScore() >= 0;
        }
        return responseDateIsNotNull && totalDepressionScoreIsValid && totalAnxietyScoreIsValid && totalStressScoreIsValid;
    }

    public Boolean responseIsValid(SaveResponseDTO resp) {
        return defaultValidationIsOk(Optional.of(resp), Optional.empty());
    }

    public ResponseModel save(SaveResponseDTO body) {
        Optional<ParticipantModel> p = this.participantRepository.findById(body.getParticipantId());
        if (p.isEmpty()) return null;

        return this.responseRepository.save(
                body.getParticipantId(),
                body.getResponseDate(),
                body.getTotalDepressionScore(),
                body.getTotalAnxietyScore(),
                body.getTotalStressScore()
        );
    }

    public Optional<ResponseDetailsDTO> findById(Long id) {
        Optional<ResponseModel> p = this.responseRepository.findById(id);
        return p.map(this::toDetailsDTO);
    }

    public List<ResponseDetailsDTO> findAll() {
        List<ResponseModel> list = this.responseRepository.findAll();
        return list.stream().map(this::toDetailsDTO).toList();
    }

    public ResponseDetailsDTO update(UpdateResponseDTO body) {
        this.responseRepository.update(
            body.getResponseId(),
            body.getParticipantId(),
            body.getResponseDate(),
            body.getTotalDepressionScore(),
            body.getTotalAnxietyScore(),
            body.getTotalStressScore()
        );
        Optional<ResponseModel> responseModel = this.responseRepository.findById(body.getResponseId());
        return responseModel.map(this::toDetailsDTO).orElse(null);
    }

    public void deleteById(Long id) {
        this.responseRepository.deleteById(id);
    }

    public void deleteByParticipantId(Long id) {
        this.responseRepository.deleteByParticipantId(id);
    }

    public List<ResponseDetailsDTO> findByParticipantId(Long id) {
        List<ResponseModel> list = this.responseRepository.findResponseByParticipantId(id);
        return list.stream().map(this::toDetailsDTO).toList();
    }
}
