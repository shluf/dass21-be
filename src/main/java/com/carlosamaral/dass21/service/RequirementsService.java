package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.dto.ResponseDetailsDTO;
import com.carlosamaral.dass21.dto.ResponseMinDetailsDTO;
import com.carlosamaral.dass21.dto.UpdateResponseAndParticipantDTO;
import com.carlosamaral.dass21.dto.UpdateResponseDTO;
import com.carlosamaral.dass21.model.ParticipantModel;
import com.carlosamaral.dass21.model.ResponseModel;
import com.carlosamaral.dass21.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RequirementsService {
    @Autowired
    private ResponseService responseService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ResponseRepository responseRepository;

    public Optional<UpdateResponseAndParticipantDTO> updateResponseAndParticipant(UpdateResponseAndParticipantDTO req) {
        UpdateResponseAndParticipantDTO response = new UpdateResponseAndParticipantDTO();
        response.setResponses(List.of());

        try {
            ParticipantModel p = new ParticipantModel();
            p.setId(req.getParticipantId());
            p.setName(req.getName());
            p.setAge(req.getAge());
            p.setGender(req.getGender());

            var isValid = this.participantService.participantIsValid(p, true);
            var exists = this.participantService.findById(p.getId()).isPresent();
            if (isValid && exists) {
                this.participantService.update(p);
                response.setName(p.getName());
                response.setAge(p.getAge());
                response.setGender(p.getGender());
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            return Optional.empty();
        }

        List<ResponseMinDetailsDTO> participantResponses = new ArrayList<>();
        try {
            for (ResponseMinDetailsDTO responseMinDetails : req.responses) {
                UpdateResponseDTO updateResponseDTO = toUpdateResponseDTO(req, responseMinDetails);

                var questionFounded = this.responseService.findById(updateResponseDTO.getResponseId());
                if (questionFounded.isEmpty()) {
                    break;
                }

                boolean isQuestionOwner = Objects.equals(updateResponseDTO.participantId, req.getParticipantId());
                if (isQuestionOwner) {
                    this.responseService.update(updateResponseDTO);
                    participantResponses.add(responseMinDetails);
                }

            }
        } catch (Exception e) {
            return Optional.empty();
        }

        response.setResponses(participantResponses);
        return Optional.of(response);
    }

    private static UpdateResponseDTO toUpdateResponseDTO(UpdateResponseAndParticipantDTO req, ResponseMinDetailsDTO response) {
        UpdateResponseDTO updateResponseDTO = new UpdateResponseDTO();
        updateResponseDTO.setResponseId(response.getResponseId());
        updateResponseDTO.setParticipantId(req.getParticipantId());
        updateResponseDTO.setResponseDate(response.getResponseDate());
        updateResponseDTO.setTotalDepressionScore(response.getTotalDepressionScore());
        updateResponseDTO.setTotalAnxietyScore(response.getTotalAnxietyScore());
        updateResponseDTO.setTotalStressScore(response.getTotalStressScore());
        return updateResponseDTO;
    }
}
