package com.bonkAndrzej.iNeedProgrammers.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<List<Location>> findAllById(Set<Long> benefitsIds);

    Optional<Location> findById(Long id);
}
