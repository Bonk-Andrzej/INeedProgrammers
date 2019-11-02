package com.bonkAndrzej.iNeedProgrammers.jobOffer;

import com.bonkAndrzej.iNeedProgrammers.benefit.Benefit;
import com.bonkAndrzej.iNeedProgrammers.benefit.BenefitService;
import com.bonkAndrzej.iNeedProgrammers.category.Category;
import com.bonkAndrzej.iNeedProgrammers.category.CategoryService;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.dto.JobOfferDto;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.dto.JobOfferForm;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.exception.JobOfferException;
import com.bonkAndrzej.iNeedProgrammers.location.Location;
import com.bonkAndrzej.iNeedProgrammers.location.LocationService;
import com.bonkAndrzej.iNeedProgrammers.seniority.Seniority;
import com.bonkAndrzej.iNeedProgrammers.seniority.SeniorityService;
import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import com.bonkAndrzej.iNeedProgrammers.technology.TechnologyService;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import com.bonkAndrzej.iNeedProgrammers.user.UserService;
import com.bonkAndrzej.iNeedProgrammers.util.LambdaExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service @Transactional
@AllArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final UserService userService;
    private final BenefitService benefitService;
    private final CategoryService categoryService;
    private final LocationService locationService;
    private final SeniorityService seniorityService;
    private final TechnologyService technologyService;


    public JobOfferDto createJobOffer(JobOfferForm jobOfferForm) throws JobOfferException {
        JobOffer jobOfferAfterSave = jobOfferRepository.save(assignFieldsToJobOffer(new JobOffer(), jobOfferForm));
        return new JobOfferDto(jobOfferAfterSave);
    }

    public JobOfferDto updateJobOffer(JobOfferForm jobOfferForm, Long id) throws JobOfferException {
        JobOffer jobOffer = jobOfferRepository.findOneByIdWithEagerEagerRelationships(id)
                .orElseThrow(() -> new JobOfferException("Job offer not found with given ID " + id));

        if (!jobOffer.getVersion().equals(jobOfferForm.getVersion()))
            throw new JobOfferException("OptimisticLockException - provided wrong job offer version " + jobOfferForm.getVersion() +
                    "\nExpected " + jobOffer.getVersion());

        JobOffer jobOfferAfterUpdate = jobOfferRepository.save(assignFieldsToJobOffer(jobOffer, jobOfferForm));

        return new JobOfferDto(jobOfferAfterUpdate);
    }


    public JobOfferDto getJobOffer(Long id) throws JobOfferException {
        JobOffer jobOffer = jobOfferRepository.findOneByIdWithEagerEagerRelationships(id)
                .orElseThrow(() -> new JobOfferException("Job offer not found with given ID " + id));

        return new JobOfferDto(jobOffer);
    }

    public List<JobOfferDto> getAllJobOffers() {
        return jobOfferRepository.findAllWithEagerEagerRelationships()
                .stream().map(JobOfferDto::new).collect(Collectors.toList());
    }

    public void delete(Long id, Integer version) throws JobOfferException {
        JobOffer jobOffer = jobOfferRepository.findOneByIdWithEagerEagerRelationships(id)
                .orElseThrow(() -> new JobOfferException("Job offer not found with given ID " + id));

        if (!jobOffer.getVersion().equals(version))
            throw new JobOfferException("OptimisticLockException - provided wrong job offer version " + version +
                    "\nExpected " + jobOffer.getVersion());

        jobOfferRepository.delete(jobOffer);
    }


    private JobOffer assignFieldsToJobOffer(JobOffer jobOffer, JobOfferForm jobOfferForm) throws JobOfferException {
        User employer = userService.findOneById(jobOfferForm.getEmployerId())
                .orElseThrow(() -> new JobOfferException("Employer not found with given IDs " + jobOfferForm.getEmployerId()));

        Set<Benefit> benefits = jobOfferForm.getBenefitsIds().stream().map(LambdaExceptionUtil.rethrowFunction(id ->
                benefitService.findById(id).orElseThrow(() -> new JobOfferException("Benefits not found with given ID " + id))))
                .collect(Collectors.toSet());

        Set<Category> categories = jobOfferForm.getCategoriesIds().stream().map(LambdaExceptionUtil.rethrowFunction(id ->
                categoryService.findById(id).orElseThrow(() -> new JobOfferException("Category not found with given ID " + id))))
                .collect(Collectors.toSet());

        Set<Location> locations = jobOfferForm.getLocationsIds().stream().map(LambdaExceptionUtil.rethrowFunction(id ->
                locationService.findById(id).orElseThrow(() -> new JobOfferException("Category not found with given ID " + id))))
                .collect(Collectors.toSet());

        Set<Seniority> seniorities = jobOfferForm.getSenioritiesIds().stream().map(LambdaExceptionUtil.rethrowFunction(id ->
                seniorityService.findById(id).orElseThrow(() -> new JobOfferException("Category not found with given ID " + id))))
                .collect(Collectors.toSet());

        Set<Technology> technologies = jobOfferForm.getTechnologiesIds().stream().map(LambdaExceptionUtil.rethrowFunction(id ->
                technologyService.findById(id).orElseThrow(() -> new JobOfferException("Category not found with given ID " + id))))
                .collect(Collectors.toSet());

        jobOffer.setTitle(jobOfferForm.getTitle());
        jobOffer.setContent(jobOfferForm.getContent());
        jobOffer.setEmail(jobOfferForm.getEmail());
        jobOffer.setSalary(jobOfferForm.getSalary());
        jobOffer.setPhoneNumber(jobOfferForm.getPhoneNumber());
        jobOffer.setEmployer(employer);
        jobOffer.setBenefits(benefits);
        jobOffer.setCategories(categories);
        jobOffer.setLocations(locations);
        jobOffer.setSeniorities(seniorities);
        jobOffer.setTechnologies(technologies);
        return jobOffer;
    }

}
