package com.bonkAndrzej.iNeedProgrammers.seniority;

import com.bonkAndrzej.iNeedProgrammers.seniority.dto.SeniorityDto;
import com.bonkAndrzej.iNeedProgrammers.seniority.dto.SeniorityForm;
import com.bonkAndrzej.iNeedProgrammers.seniority.exception.SeniorityException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SeniorityService {

    private final SeniorityRepository seniorityRepository;

    public SeniorityDto createSeniority(SeniorityForm seniorityForm) {
        Seniority seniority = new Seniority();
        seniority.setName(seniorityForm.getName());

        Seniority seniorityAfterSave = seniorityRepository.save(seniority);
        return new SeniorityDto(seniorityAfterSave);
    }

    public SeniorityDto updateSeniority(SeniorityForm seniorityForm, Long id) throws SeniorityException {
        Seniority seniority = seniorityRepository.findById(id).orElseThrow(
                () -> new SeniorityException("Seniority not found with given id " + id));

        if (!seniority.getVersion().equals(seniorityForm.getVersion()))
            throw new SeniorityException("OptimisticLockException - wrong seniority version " + seniorityForm.getVersion() +
                    "\nExpected " + seniority.getVersion());

        seniority.setName(seniorityForm.getName());
        Seniority seniorityAfterUpdate = seniorityRepository.save(seniority);
        return new SeniorityDto(seniorityAfterUpdate);
    }

    @Transactional(readOnly = true)
    public SeniorityDto getSeniority(Long id) throws SeniorityException {
        Seniority seniority = seniorityRepository.findById(id).orElseThrow(
                () -> new SeniorityException("Seniority not found with given id " + id));

        return new SeniorityDto(seniority);
    }

    @Transactional(readOnly = true)
    public List<SeniorityDto> getAllSeniority() {
        List<Seniority> categories = seniorityRepository.findAll();

        return categories.stream()
                .map(SeniorityDto::new).collect(Collectors.toList());
    }

    public void deleteSeniority(Long id, Integer version) throws SeniorityException {
        Seniority seniority = seniorityRepository.findById(id).orElseThrow(
                () -> new SeniorityException("Seniority not found with given id " + id));

        if (!seniority.getVersion().equals(version))
            throw new SeniorityException("OptimisticLockException - wrong seniority version " + version +
                    "\nExpected " + seniority.getVersion());

        seniorityRepository.delete(seniority);
    }


}
