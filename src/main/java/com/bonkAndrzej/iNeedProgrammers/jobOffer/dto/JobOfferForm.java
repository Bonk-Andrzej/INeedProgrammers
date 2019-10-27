package com.bonkAndrzej.iNeedProgrammers.jobOffer.dto;

import com.bonkAndrzej.iNeedProgrammers.util.UtilConstants;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class JobOfferForm {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Email
    private String email;
    @Positive
    private Long salary;
    @Pattern(regexp = UtilConstants.phoneNumberRegex)
    private String phoneNumber;
    private Long employerId;
    private Set<Long> categoriesIds;
    private Set<Long> technologiesIds;
    private Set<Long> senioritySetIds;
    private Set<Long> locationsIds;
    private Set<Long> benefitsIds;
}