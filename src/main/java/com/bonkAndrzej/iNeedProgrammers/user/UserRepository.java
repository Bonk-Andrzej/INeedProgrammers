package com.bonkAndrzej.iNeedProgrammers.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Long countByRoleName(String roleName);


    Optional<User> findOneByResetPasswordKey(String resetKey);


    @Query("select user from User user " +
                   "where user.activationKey =:activationKey")
    Optional<User> findOneByActivationKey(@Param("activationKey") String activationKey);


    @Query("select user from User user " +
                   "where user.login =:login")
    Optional<User> findOneByLogin(@Param("login") String login);

    @Query("select user from User user " +
                   "where user.email =:email")
    Optional<User> findOneByEmail(@Param("email") String email);

    @Query("select user.email from User user " +
                   "where user.email =:email")
    Optional<String> getUserEmail(@Param("email") String email);

    @Query("select user from User user " +
                   "left join fetch user.role " +
                   "where user.email =:email")
    Optional<User> findOneByEmailWithEagerRelationships(@Param("email") String email);

    @Query("select user from User user " +
                   "left join fetch user.role " +
                   "where user.id =:id")
    Optional<User> findOneByIdWithEagerRelationships(@Param("id") Long id);

    @Query("select distinct user.password from User user")
    Set<String> getAllUsersPasswords();

    @Query("select distinct user from User user " +
                   "left join fetch user.role")
    List<User> findAllWithEagerRelationships();

    @Query(value = "select distinct user from User user " +
            "left join fetch user.role ",
           countQuery = "select count(distinct user) from User user")
    Page<User> findAllWithEagerRelationships(Pageable pageable);

}
