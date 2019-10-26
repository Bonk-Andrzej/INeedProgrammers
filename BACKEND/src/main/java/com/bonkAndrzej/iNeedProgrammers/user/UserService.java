package com.bonkAndrzej.iNeedProgrammers.user;


import com.bonkAndrzej.iNeedProgrammers.security.Auth;
import com.bonkAndrzej.iNeedProgrammers.user.exception.UserException;
import com.bonkAndrzej.iNeedProgrammers.util.RolesConstants;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserForm;
import com.bonkAndrzej.iNeedProgrammers.user.role.RoleService;
import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleForm;
import com.bonkAndrzej.iNeedProgrammers.util.error.CustomValidationException;
import com.bonkAndrzej.iNeedProgrammers.util.error.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.OptimisticLockException;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing {@link User}.
 */
@Slf4j
@Service @Validated
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final Auth auth;

    /**
     * Save a user.
     *
     * @param userForm the entity to save.
     * @return the persisted entity.
     */
    public UserDto save(UserForm userForm) throws UserException {
        User user = new User();
        User userAfterSave = userRepository.save(assignFieldsToUser(user, userForm));

        return new UserDto(userAfterSave);
    }

    /**
     * Get all the user.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAllWithEagerRelationships()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    /**
     * Get one user by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public UserDto findOne(Long id) throws UserException {
        User user = userRepository.findOneByIdWithEagerRelationships(id)
                .orElseThrow(() -> new UserException("User not found"));

        return new UserDto(user);
    }


    public UserDto update(Long id, UserForm userForm) throws UserException {
        User user = userRepository.findOneByIdWithEagerRelationships(id)
                .orElseThrow(() -> new UserException("User not found"));

        if (!userForm.getVersion().equals(user.getVersion()))
            throw new UserException("OptimisticLockException - Bad entity version");

        validateRole(userForm.getRole());

//        User userAfterUpdate = userRepository.saveAndFlush(assignFieldsToUser(user, userForm));
        User userAfterUpdate = assignFieldsToUser(user, userForm);
        return new UserDto(userAfterUpdate);
    }


    /**
     * Delete the user by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id, Integer userVersion) throws UserException {
        User user = userRepository.findOneByIdWithEagerRelationships(id)
                .orElseThrow(() -> new UserException("User not found"));

        if (!userVersion.equals(user.getVersion()))
            throw new UserException("OptimisticLockException - Bad entity version");

        if (userRepository.countByRoleName(RolesConstants.ADMIN) <= 1)
            throw new UserException(
                    "Roles are invalid. Minimum one SUPER_ADMIN role in the application is required");
        userRepository.delete(user);
    }

    /**
     * Change user role.
     *
     * @param userId   {Long}   id
     * @param roleForm {RoleForm} roleForm
     * @return {UserDto} userDto
     */
    public UserDto changeUserRole(Long userId, RoleForm roleForm) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        String roleName = roleForm.getName();
        validateRole(roleName);

        user.setRole(roleService.getRoleByName(roleName)
                .orElseThrow(() -> new UserException("Role not found")));

        User userAfterChangeRole = userRepository.save(user);
        return new UserDto(userAfterChangeRole);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
        userRepository.flush();
    }

    public boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isEnabled()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

    public Optional<User> findOneByActivationKey(String activationKey) {
        return userRepository.findOneByActivationKey(activationKey);
    }

    @Transactional(readOnly = true)
    public Optional<User> findOneById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findOneByUsername(@NotBlank String email) {
        return userRepository.findOneByEmailWithEagerRelationships(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> findOneByResetPasswordKey(String resetPasswordKey) {
        return userRepository.findOneByResetPasswordKey(resetPasswordKey);
    }

    @Transactional(readOnly = true)
    public Optional<String> getUserEmail(String email) {
        return userRepository.getUserEmail(email);
    }


    @Transactional(readOnly = true)
    public Set<String> getAllEncodedPasswords() {
        return userRepository.getAllUsersPasswords();
    }

    @Transactional(readOnly = true)
    public Optional<User> findOneByLogin(@NotBlank String login) {
        return userRepository.findOneByLogin(login);
    }


    private void validateRole(String roleName) throws UserException {
        Set<String> currentUserRoles = auth.getCurrentUserRoles()
                .orElseThrow(() -> new UserException("User haven't any role"));

        if (!currentUserRoles.contains(RolesConstants.ADMIN) && !roleName.equalsIgnoreCase(RolesConstants.ADMIN))
            throw new UserException("You haven't permission to grant this role.");

        if (userRepository.countByRoleName(RolesConstants.ADMIN) == 1 &&
                !roleName.equalsIgnoreCase(RolesConstants.ADMIN))
            throw new UserException(
                    "Role is invalid. Minimum one ADMIN role in the application is required");
    }


    public User assignFieldsToUser(User user, UserForm userForm) throws UserException {
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setLogin(userForm.getLogin());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        user.setRole(roleService.getRoleByName(userForm.getRole())
                .orElseThrow(() -> new UserException("Role not found")));

        return user;
    }

}
