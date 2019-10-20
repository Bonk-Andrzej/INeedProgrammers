package com.bonkAndrzej.iNeedProgrammers.user;


import com.bonkAndrzej.iNeedProgrammers.security.Auth;
import com.bonkAndrzej.iNeedProgrammers.security.RolesConstants;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserForm;
import com.bonkAndrzej.iNeedProgrammers.user.role.RoleService;
import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleForm;
import com.bonkAndrzej.iNeedProgrammers.util.error.CustomValidationException;
import com.bonkAndrzej.iNeedProgrammers.util.error.ResourceNotFoundException;
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
import java.util.stream.Stream;


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

    private enum Status {ACTIVE, INACTIVE}

    /**
     * Save a user.
     *
     * @param userForm the entity to save.
     * @return the persisted entity.
     */
    public UserDto save(UserForm userForm) throws ResourceNotFoundException {
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
    public UserDto findOne(Long id) throws ResourceNotFoundException {
        User user = userRepository.findOneByIdWithEagerRelationships(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new UserDto(user);
    }


    public UserDto update(Long id, UserForm userForm) throws ResourceNotFoundException, CustomValidationException {
        User user = userRepository.findOneByIdWithEagerRelationships(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userForm.getVersion().equals(user.getVersion()))
            throw new CustomValidationException("OptimisticLockException - Bad entity version");

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
    public void delete(Long id, Integer userVersion) throws ResourceNotFoundException, CustomValidationException {
        User user = userRepository.findOneByIdWithEagerRelationships(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userVersion.equals(user.getVersion()))
            throw new OptimisticLockException(user);

        if (userRepository.countByRoleName(RolesConstants.ADMIN) <= 1)
            throw new CustomValidationException(
                    "Roles are invalid. Minimum one SUPER_ADMIN role in the application is required");
        userRepository.delete(user);
    }

    /**
     * Change user status.
     *
     * @param id        {Long}   id
     * @param newStatus {String} status
     * @return {UserDto} userDto
     */
    public UserDto changeStatus(Long id, String newStatus) throws ResourceNotFoundException, CustomValidationException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Stream.of(Status.values()).noneMatch(status -> newStatus.equalsIgnoreCase(status.name())))
            throw new CustomValidationException("User status is incorrect. Please check status name");

//        user.setEnabled(newStatus.toUpperCase());
        User userAfterChangeStatus = userRepository.save(user);
        return new UserDto(userAfterChangeStatus);
    }

    /**
     * Change user role.
     *
     * @param userId   {Long}   id
     * @param roleForm {RoleForm} roleForm
     * @return {UserDto} userDto
     */
    public UserDto addRoleToUser(Long userId, RoleForm roleForm) throws CustomValidationException, ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String roleName = roleForm.getName();
        validateRole(roleName);

        user.setRole(roleService.getRoleByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found")));

        User userAfterChangeRole = userRepository.save(user);
        return new UserDto(userAfterChangeRole);
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(@NotBlank String email) {
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

    private void validateRole(String roleName) throws CustomValidationException {
        Set<String> currentUserRoles = auth.getCurrentUserRoles()
                .orElseThrow(() -> new CustomValidationException("User haven't any role"));

        if (!currentUserRoles.contains(RolesConstants.ADMIN) && !roleName.equalsIgnoreCase(RolesConstants.ADMIN))
            throw new CustomValidationException("You haven't permission to grant this role.");

        if (userRepository.countByRoleName(RolesConstants.ADMIN) == 1 &&
                !roleName.equalsIgnoreCase(RolesConstants.ADMIN))
            throw new CustomValidationException(
                    "Role is invalid. Minimum one ADMIN role in the application is required");
    }


    private User assignFieldsToUser(User user, UserForm userForm) throws ResourceNotFoundException {
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setLogin(userForm.getLogin());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEnabled(userForm.isEnabled());

        user.setRole(roleService.getRoleByName(userForm.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found")));

        return user;
    }

}
