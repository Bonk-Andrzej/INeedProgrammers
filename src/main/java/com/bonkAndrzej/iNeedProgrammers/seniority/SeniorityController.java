package com.bonkAndrzej.iNeedProgrammers.seniority;

import com.bonkAndrzej.iNeedProgrammers.seniority.dto.SeniorityDto;
import com.bonkAndrzej.iNeedProgrammers.seniority.dto.SeniorityForm;
import com.bonkAndrzej.iNeedProgrammers.seniority.exception.SeniorityException;
import com.bonkAndrzej.iNeedProgrammers.user.role.roleAnnotation.Admin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class SeniorityController {

    private final SeniorityService seniorityService;

    @Admin
    @PostMapping("/seniorities")
    public ResponseEntity<SeniorityDto> createSeniority(@Valid @RequestBody SeniorityForm seniorityForm) {
        SeniorityDto seniorityDto = seniorityService.createSeniority(seniorityForm);

        return new ResponseEntity<>(seniorityDto, HttpStatus.CREATED);
    }


    @GetMapping("/seniorities/{id}")
    public ResponseEntity<SeniorityDto> getSeniority(@Positive @PathVariable Long id) throws SeniorityException {
        SeniorityDto seniorityDto = seniorityService.getSeniority(id);

        return new ResponseEntity<>(seniorityDto, HttpStatus.OK);
    }

    @GetMapping("/seniorities")
    public ResponseEntity<List<SeniorityDto>> getAllCategories() {
        List<SeniorityDto> senioritiesDto = seniorityService.getAllSeniority();

        return new ResponseEntity<>(senioritiesDto, HttpStatus.OK);
    }

    @Admin
    @PutMapping("/seniorities/{id}")
    public ResponseEntity<SeniorityDto> updateSeniority(@Valid @RequestBody SeniorityForm seniorityForm, @Positive @PathVariable Long id)
            throws SeniorityException {
        SeniorityDto seniorityDto = seniorityService.updateSeniority(seniorityForm, id);

        return new ResponseEntity<>(seniorityDto, HttpStatus.OK);
    }

    @Admin
    @DeleteMapping("/seniorities/{id}")
    public ResponseEntity<Void> deleteSeniority(@Positive @PathVariable Long id, @PositiveOrZero @RequestParam Integer version)
            throws SeniorityException {
        seniorityService.deleteSeniority(id, version);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
