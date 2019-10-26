package com.bonkAndrzej.iNeedProgrammers.technology;

import com.bonkAndrzej.iNeedProgrammers.seniority.Seniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
