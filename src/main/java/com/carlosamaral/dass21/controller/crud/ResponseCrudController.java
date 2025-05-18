package com.carlosamaral.dass21.controller.crud;

import com.carlosamaral.dass21.dto.ResponseDetailsDTO;
import com.carlosamaral.dass21.dto.SaveResponseDTO;
import com.carlosamaral.dass21.dto.UpdateResponseDTO;
import com.carlosamaral.dass21.model.ResponseModel;
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
@RequestMapping("/crud/response")
public class ResponseCrudController {
    private final ResponseService responseService;
    public ResponseCrudController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<ResponseDetailsDTO>> findAll() {
        return new ResponseEntity<>(this.responseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<ResponseDetailsDTO>> findById(@PathVariable Long id) {
        if (id <= 0) return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);

        Optional<ResponseDetailsDTO> responseFound = this.responseService.findById(id);
        return new ResponseEntity<>(responseFound, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Optional<ResponseDetailsDTO>> save(@RequestBody SaveResponseDTO response) {
        if (!responseService.responseIsValid(response)) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

        try {
            ResponseModel responseModel = this.responseService.save(response);
            ResponseDetailsDTO responseDTO = responseService.toDetailsDTO(responseModel);
            return new ResponseEntity<>(Optional.of(responseDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<ResponseDetailsDTO>> update(@RequestBody UpdateResponseDTO body) {
        try {
            var res = this.responseService.update(body);
            return new ResponseEntity<>(Optional.of(res), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            if (responseService.findById(id).isEmpty()) {
                return new ResponseEntity<>("Response not found", HttpStatus.BAD_REQUEST);
            }

            responseService.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
