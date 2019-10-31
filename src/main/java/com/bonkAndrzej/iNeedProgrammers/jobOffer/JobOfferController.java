package com.bonkAndrzej.iNeedProgrammers.jobOffer;

import com.bonkAndrzej.iNeedProgrammers.jobOffer.dto.JobOfferDto;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.dto.JobOfferForm;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.exception.JobOfferException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @PostMapping("job-offers")
    public ResponseEntity<JobOfferDto> createJobOffer(@RequestBody JobOfferForm jobOfferForm) throws JobOfferException {
        JobOfferDto jobOfferDto = jobOfferService.createJobOffer(jobOfferForm);
        return new ResponseEntity<>(jobOfferDto, HttpStatus.OK);
    }


}
