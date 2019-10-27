package com.bonkAndrzej.iNeedProgrammers.benefit;

import com.bonkAndrzej.iNeedProgrammers.benefit.dto.BenefitDto;
import com.bonkAndrzej.iNeedProgrammers.benefit.dto.BenefitForm;
import com.bonkAndrzej.iNeedProgrammers.benefit.exception.BenefitException;
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
public class BenefitController {

    private final BenefitService benefitService;

    @Admin
    @PostMapping("/benefits")
    public ResponseEntity<BenefitDto> createBenefit(@Valid @RequestBody BenefitForm benefitForm) {
        BenefitDto benefitDto = benefitService.createBenefit(benefitForm);

        return new ResponseEntity<>(benefitDto, HttpStatus.CREATED);
    }


    @GetMapping("/benefits/{id}")
    public ResponseEntity<BenefitDto> getBenefit(@Positive @PathVariable Long id) throws BenefitException {
        BenefitDto benefitDto = benefitService.getBenefit(id);

        return new ResponseEntity<>(benefitDto, HttpStatus.OK);
    }

    @GetMapping("/benefits")
    public ResponseEntity<List<BenefitDto>> getAllBenefit() {
        List<BenefitDto> benefitDtos = benefitService.getAllBenefit();

        return new ResponseEntity<>(benefitDtos, HttpStatus.OK);
    }

    @Admin
    @PutMapping("/benefit/{id}")
    public ResponseEntity<BenefitDto> updateBenefit(@Valid @RequestBody BenefitForm benefitForm, @Positive @PathVariable Long id)
            throws BenefitException {
        BenefitDto benefitDto = benefitService.updateBenefit(benefitForm, id);

        return new ResponseEntity<>(benefitDto, HttpStatus.OK);
    }

    @Admin
    @DeleteMapping("/benefits/{id}")
    public ResponseEntity<Void> deleteBenefit(@Positive @PathVariable Long id, @PositiveOrZero @RequestParam Integer version)
            throws BenefitException {
        benefitService.deleteBenefit(id, version);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
