package com.bonkAndrzej.iNeedProgrammers.jobOffer;

import com.bonkAndrzej.iNeedProgrammers.jobOffer.dto.JobOfferDto;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.dto.JobOfferForm;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.exception.JobOfferException;
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
public class JobOfferController {
    // todo add feature - only admin and job offer author can delete and update,
//      add pagination

    private final JobOfferService jobOfferService;

    @PostMapping("job-offers")
    public ResponseEntity<JobOfferDto> createJobOffer(@RequestBody JobOfferForm jobOfferForm) throws JobOfferException {
        JobOfferDto jobOfferDto = jobOfferService.createJobOffer(jobOfferForm);
        return new ResponseEntity<>(jobOfferDto, HttpStatus.OK);
    }


    @GetMapping("/job-offers/{id}")
    public ResponseEntity<JobOfferDto> getJobOffer(@Positive @PathVariable Long id) throws JobOfferException {
        JobOfferDto jobOfferDto = jobOfferService.getJobOffer(id);

        return new ResponseEntity<>(jobOfferDto, HttpStatus.OK);
    }

    @GetMapping("/job-offers")
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers() {
        List<JobOfferDto> jobOffersDto = jobOfferService.getAllJobOffers();

        return new ResponseEntity<>(jobOffersDto, HttpStatus.OK);
    }

    @PutMapping("/job-offers/{id}")
    public ResponseEntity<JobOfferDto> updateJobOffer(@Valid @RequestBody JobOfferForm jobOfferForm, @Positive @PathVariable Long id)
            throws JobOfferException {
        JobOfferDto jobOfferDto = jobOfferService.updateJobOffer(jobOfferForm, id);

        return new ResponseEntity<>(jobOfferDto, HttpStatus.OK);
    }

    @DeleteMapping("/job-offers/{id}")
    public ResponseEntity<Void> deleteJobOffer(@Positive @PathVariable Long id, @PositiveOrZero @RequestParam Integer version)
            throws JobOfferException {
        jobOfferService.delete(id, version);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
