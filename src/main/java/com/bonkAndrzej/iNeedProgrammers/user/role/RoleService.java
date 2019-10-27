package com.bonkAndrzej.iNeedProgrammers.user.role;

import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleDto;
import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleForm;
import com.bonkAndrzej.iNeedProgrammers.user.role.exception.RoleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Role}.
 */
@Service @Validated
@Transactional
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Save a role.
     *
     * @param roleForm the entity to save.
     * @return the persisted entity.
     */
    public RoleDto save(RoleForm roleForm) {
        Role role = new Role();
        Role roleAfterSave = roleRepository.save(assignFieldsToRole(role, roleForm));
        return new RoleDto(roleAfterSave);
    }

    public RoleDto update(RoleForm roleForm, Long id) throws RoleException {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleException("Role not found"));

        if (!roleForm.getVersion().equals(role.getVersion()))
            throw new RoleException("OptimisticLockException - Bad entity version");

        Role roleAfterUpdate = roleRepository.saveAndFlush(assignFieldsToRole(role, roleForm));
        return new RoleDto(roleAfterUpdate);
    }

    /**
     * Get all the role.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
            .map(RoleDto::new).collect(Collectors.toList());
    }


    /**
     * Get one role by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public RoleDto findOne(Long id) throws RoleException {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleException("Role not found"));

        return new RoleDto(role);
    }

    /**
     * Delete the role by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id, Integer version) throws RoleException {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleException("Role not found"));

        if (!version.equals(role.getVersion()))
            throw new RoleException("OptimisticLockException - Bad entity version");

        roleRepository.deleteById(id);
    }

    /**
     * Get the role by name.
     *
     * @param name the id of the entity
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Role> getRoleByName(@NotBlank String name) {
        return roleRepository.findByName(name);
    }


    private Role assignFieldsToRole(Role role, RoleForm roleForm) {
        role.setName(roleForm.getName());
        role.setSlug(roleForm.getSlug());
        return role;
    }
}
