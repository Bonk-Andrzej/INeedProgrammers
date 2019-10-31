package com.bonkAndrzej.iNeedProgrammers.benefit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    Optional<List<Benefit>> findAllById(Set<Long> benefitsIds);

    Optional<Benefit> findById(Long id);
}
