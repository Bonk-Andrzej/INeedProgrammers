package com.bonkAndrzej.iNeedProgrammers.benefit;

import com.bonkAndrzej.iNeedProgrammers.benefit.dto.BenefitDto;
import com.bonkAndrzej.iNeedProgrammers.benefit.dto.BenefitForm;
import com.bonkAndrzej.iNeedProgrammers.benefit.exception.BenefitException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BenefitService {
    private final BenefitRepository benefitRepository;

    public BenefitDto createBenefit(BenefitForm benefitForm) {
        Benefit benefit = new Benefit();
        benefit.setName(benefitForm.getName());

        Benefit benefitAfterSave = benefitRepository.save(benefit);
        return new BenefitDto(benefitAfterSave);
    }

    public BenefitDto updateBenefit(BenefitForm benefitForm, Long id) throws BenefitException {
        Benefit benefit = benefitRepository.findById(id).orElseThrow(
                () -> new BenefitException("Benefit not found with given id " + id));

        if (!benefit.getVersion().equals(benefitForm.getVersion()))
            throw new BenefitException("OptimisticLockException - wrong benefit version");

        benefit.setName(benefitForm.getName());
        Benefit benefitAfterUpdate = benefitRepository.save(benefit);
        return new BenefitDto(benefitAfterUpdate);
    }

    @Transactional(readOnly = true)
    public BenefitDto getBenefit(Long id) throws BenefitException {
        Benefit benefit = benefitRepository.findById(id).orElseThrow(
                () -> new BenefitException("Benefit not found with given id " + id));

        return new BenefitDto(benefit);
    }

    @Transactional(readOnly = true)
    public List<BenefitDto> getAllBenefit() {
        List<Benefit> benefits = benefitRepository.findAll();

        return benefits.stream()
                .map(BenefitDto::new).collect(Collectors.toList());
    }

    public void deleteBenefit(Long id, Integer version) throws BenefitException {
        Benefit benefit = benefitRepository.findById(id).orElseThrow(
                () -> new BenefitException("Benefit not found with given id " + id));

        if (!benefit.getVersion().equals(version))
            throw new BenefitException("OptimisticLockException - wrong benefit version");

        benefitRepository.delete(benefit);

    }

}
