package com.bonkAndrzej.iNeedProgrammers.seniority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeniorityRepository extends JpaRepository<Seniority, Long> {
    Optional<Seniority> findById(Long id);

}
