package com.bonkAndrzej.iNeedProgrammers.technology;

import com.bonkAndrzej.iNeedProgrammers.technology.dto.TechnologyDto;
import com.bonkAndrzej.iNeedProgrammers.technology.dto.TechnologyForm;
import com.bonkAndrzej.iNeedProgrammers.technology.exception.TechnologyException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyDto createTechnology(TechnologyForm technologyForm) {
        Technology technology = new Technology();
        technology.setName(technologyForm.getName());

        Technology technologyAfterSave = technologyRepository.save(technology);
        return new TechnologyDto(technologyAfterSave);
    }

    public TechnologyDto updateTechnology(TechnologyForm technologyForm, Long id) throws TechnologyException {
        Technology technology = technologyRepository.findById(id).orElseThrow(
                () -> new TechnologyException("Technology not found with given id " + id));

        if (!technology.getVersion().equals(technologyForm.getVersion()))
            throw new TechnologyException("OptimisticLockException - wrong technology version " + technologyForm.getVersion() +
                    "\nExpected " + technology.getVersion());

        technology.setName(technologyForm.getName());
        Technology technologyAfterUpdate = technologyRepository.save(technology);
        return new TechnologyDto(technologyAfterUpdate);
    }

    @Transactional(readOnly = true)
    public TechnologyDto getTechnology(Long id) throws TechnologyException {
        Technology technology = technologyRepository.findById(id).orElseThrow(
                () -> new TechnologyException("Technology not found with given id " + id));

        return new TechnologyDto(technology);
    }

    @Transactional(readOnly = true)
    public List<TechnologyDto> getAllTechnologies() {
        List<Technology> categories = technologyRepository.findAll();

        return categories.stream()
                .map(TechnologyDto::new).collect(Collectors.toList());
    }

    public void deleteTechnology(Long id, Integer version) throws TechnologyException {
        Technology technology = technologyRepository.findById(id).orElseThrow(
                () -> new TechnologyException("Technology not found with given id " + id));

        if (!technology.getVersion().equals(version))
            throw new TechnologyException("OptimisticLockException - wrong technology version " + version +
                    "\nExpected " + technology.getVersion());

        technologyRepository.delete(technology);
    }


}
