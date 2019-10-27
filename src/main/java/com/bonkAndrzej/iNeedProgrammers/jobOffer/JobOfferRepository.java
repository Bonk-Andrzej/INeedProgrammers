package com.bonkAndrzej.iNeedProgrammers.jobOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
}
