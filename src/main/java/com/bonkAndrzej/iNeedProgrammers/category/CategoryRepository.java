package com.bonkAndrzej.iNeedProgrammers.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findAllById(Set<Long> benefitsIds);

    Optional<Category> findById(Long id);

}
