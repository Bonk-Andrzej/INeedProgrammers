package com.bonkAndrzej.iNeedProgrammers.jobOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Query(value = "select distinct jobOffer from JobOffer jobOffer " +
            "left join fetch jobOffer.benefits " +
            "left join fetch jobOffer.categories " +
            "left join fetch jobOffer.locations " +
            "left join fetch jobOffer.seniorities " +
            "left join fetch jobOffer.technologies")
    List<JobOffer> findAllWithEagerEagerRelationships();


    @Query(value = "select distinct jobOffer from JobOffer jobOffer " +
            "left join fetch jobOffer.benefits " +
            "left join fetch jobOffer.categories " +
            "left join fetch jobOffer.locations " +
            "left join fetch jobOffer.seniorities " +
            "left join fetch jobOffer.technologies " +
            "where jobOffer.id =:id")
    Optional<JobOffer> findOneByIdWithEagerEagerRelationships(@Param("id") Long id);
}
