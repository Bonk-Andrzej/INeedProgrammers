package com.bonkAndrzej.iNeedProgrammers.jobOffer.dto;

import com.bonkAndrzej.iNeedProgrammers.benefit.Benefit;
import com.bonkAndrzej.iNeedProgrammers.category.Category;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.JobOffer;
import com.bonkAndrzej.iNeedProgrammers.location.Location;
import com.bonkAndrzej.iNeedProgrammers.seniority.Seniority;
import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class JobOfferDto {

    private String title;
    private String content;
    private String email;
    private Long salary;
    private String phoneNumber;

    private Long employerId;
    private Set<Long> categoriesIds;
    private Set<Long> technologiesIds;
    private Set<Long> senioritySetIds;
    private Set<Long> locationsIds;
    private Set<Long> benefitsIds;


    public JobOfferDto(JobOffer jobOffer) {
        this.title = jobOffer.getTitle();
        this.content = jobOffer.getContent();
        this.email = jobOffer.getEmail();
        this.salary = jobOffer.getSalary();
        this.phoneNumber = jobOffer.getPhoneNumber();

        this.employerId = jobOffer.getEmployer().getId();

        this.categoriesIds = jobOffer.getCategories().stream()
                .map(Category::getId).collect(Collectors.toSet());

        this.technologiesIds = jobOffer.getTechnologies().stream()
                .map(Technology::getId).collect(Collectors.toSet());

        this.senioritySetIds = jobOffer.getSenioritySet().stream()
                .map(Seniority::getId).collect(Collectors.toSet());

        this.locationsIds = jobOffer.getLocations().stream()
                .map(Location::getId).collect(Collectors.toSet());

        this.benefitsIds = jobOffer.getBenefits().stream()
                .map(Benefit::getId).collect(Collectors.toSet());
    }
}
