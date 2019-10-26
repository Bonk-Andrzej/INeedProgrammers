package com.bonkAndrzej.iNeedProgrammers.seniority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorityRepository extends JpaRepository<Seniority, Long> {
}
