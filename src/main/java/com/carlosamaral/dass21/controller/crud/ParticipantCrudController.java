package com.carlosamaral.dass21.controller.crud;

import com.carlosamaral.dass21.model.ParticipantModel;
import com.carlosamaral.dass21.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/crud/participant")
public class ParticipantCrudController {
    private final ParticipantService participantService;
    public ParticipantCrudController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<ParticipantModel>> findAll() {
        return new ResponseEntity<>(this.participantService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<ParticipantModel>> findById(@PathVariable Long id) {
        if (id <= 0) return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        Optional<ParticipantModel> participantFound = this.participantService.findById(id);
        return new ResponseEntity<>(participantFound, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Optional<ParticipantModel>> save(@RequestBody ParticipantModel participantModel) {
        if (!participantService.participantIsValid(participantModel, false)) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        ParticipantModel participantModelSavedObject = this.participantService.save(participantModel);
        return new ResponseEntity<>(Optional.of(participantModelSavedObject), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ParticipantModel> update(@RequestBody ParticipantModel participantModel) {
        if (!participantService.participantIsValid(participantModel, true)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ParticipantModel participantModelSavedObject = this.participantService.update(participantModel);
        return new ResponseEntity<>(participantModelSavedObject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            this.participantService.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
