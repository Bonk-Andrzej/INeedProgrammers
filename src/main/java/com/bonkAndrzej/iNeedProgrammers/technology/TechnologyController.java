package com.bonkAndrzej.iNeedProgrammers.technology;

import com.bonkAndrzej.iNeedProgrammers.technology.dto.TechnologyDto;
import com.bonkAndrzej.iNeedProgrammers.technology.dto.TechnologyForm;
import com.bonkAndrzej.iNeedProgrammers.technology.exception.TechnologyException;
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
public class TechnologyController {

    private final TechnologyService technologyService;

    @Admin
    @PostMapping("/technologies")
    public ResponseEntity<TechnologyDto> createTechnology(@Valid @RequestBody TechnologyForm technologyForm) {
        TechnologyDto technologyDto = technologyService.createTechnology(technologyForm);

        return new ResponseEntity<>(technologyDto, HttpStatus.CREATED);
    }


    @GetMapping("/technologies/{id}")
    public ResponseEntity<TechnologyDto> getTechnology(@Positive @PathVariable Long id) throws TechnologyException {
        TechnologyDto technologyDto = technologyService.getTechnology(id);

        return new ResponseEntity<>(technologyDto, HttpStatus.OK);
    }

    @GetMapping("/technologies")
    public ResponseEntity<List<TechnologyDto>> getAllTechnologies() {
        List<TechnologyDto> technologyDtos = technologyService.getAllTechnologies();

        return new ResponseEntity<>(technologyDtos, HttpStatus.OK);
    }

    @Admin
    @PutMapping("/technologies/{id}")
    public ResponseEntity<TechnologyDto> updateTechnology(@Valid @RequestBody TechnologyForm technologyForm, @Positive @PathVariable Long id)
            throws TechnologyException {
        TechnologyDto technologyDto = technologyService.updateTechnology(technologyForm, id);

        return new ResponseEntity<>(technologyDto, HttpStatus.OK);
    }

    @Admin
    @DeleteMapping("/technologies/{id}")
    public ResponseEntity<Void> deleteTechnology(@Positive @PathVariable Long id, @PositiveOrZero @RequestParam Integer version)
            throws TechnologyException {
        technologyService.deleteTechnology(id, version);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
