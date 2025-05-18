package com.carlosamaral.dass21.controller;

import com.carlosamaral.dass21.controller.crud.ResponseCrudController;
import com.carlosamaral.dass21.dto.ResponseDetailsDTO;
import com.carlosamaral.dass21.dto.SaveResponseDTO;
import com.carlosamaral.dass21.dto.UpdateResponseAndParticipantDTO;
import com.carlosamaral.dass21.model.ResponseModel;
import com.carlosamaral.dass21.service.RequirementsService;
import com.carlosamaral.dass21.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/requirements")
public class RequirementsController {
    private final ResponseCrudController responseCrudController;
    private final ResponseService responseService;
    private final RequirementsService requirementsService;

    public RequirementsController(ResponseCrudController responseCrudController, ResponseService responseService, RequirementsService requirementsService) {
        this.responseCrudController = responseCrudController;
        this.responseService = responseService;
        this.requirementsService = requirementsService;
    }

    @PostMapping("/step4")
    public ResponseEntity<Optional<ResponseDetailsDTO>> step4(@RequestBody SaveResponseDTO response) {
        try {
            if (!responseService.responseIsValid(response)) {
                return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
            }

            ResponseModel responseModel = this.responseService.save(response);
            ResponseDetailsDTO resp = responseService.toDetailsDTO(responseModel);
            return new ResponseEntity<>(Optional.of(resp), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/step5")
    public ResponseEntity<List<ResponseDetailsDTO>> step5() {
        return this.responseCrudController.findAll();
    }

    @PutMapping("/step6")
    public ResponseEntity<Optional<UpdateResponseAndParticipantDTO>> step6(@RequestBody UpdateResponseAndParticipantDTO req) {
        try {
            var res = requirementsService.updateResponseAndParticipant(req);
            if (res.isEmpty()) return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/step7/{id}")
    public ResponseEntity<List<ResponseDetailsDTO>> step7(@PathVariable Long id) {
        try {
            var res = responseService.findByParticipantId(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }
    }
}
