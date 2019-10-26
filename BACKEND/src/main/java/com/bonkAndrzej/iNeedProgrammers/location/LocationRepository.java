package com.bonkAndrzej.iNeedProgrammers.location;

import com.bonkAndrzej.iNeedProgrammers.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
