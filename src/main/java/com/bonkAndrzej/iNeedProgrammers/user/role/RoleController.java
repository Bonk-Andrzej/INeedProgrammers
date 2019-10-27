package com.bonkAndrzej.iNeedProgrammers.user.role;


import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleDto;
import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleForm;
import com.bonkAndrzej.iNeedProgrammers.user.role.exception.RoleException;
import com.bonkAndrzej.iNeedProgrammers.user.role.roleAnnotation.Admin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * REST controller for managing {@link Role}.
 */
@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    /**
     * {@code POST  /roles} : Create a new role.
     *
     * @param roleForm the role to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new role,
     * or with status {@code 400 (Bad Request)} if the role is not valid.
     */
    @Admin
    @PostMapping("/roles")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleForm roleForm) {
        RoleDto roleDto = roleService.save(roleForm);
        return new ResponseEntity<>(roleDto, HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /roles} : Updates an existing role.
     *
     * @param roleForm the roles to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated role,
     * or with status {@code 400 (Bad Request)} if the role is not valid,
     * or with status {@code 500 (Internal Server Error)} if the webShopRole couldn't be updated.
     */
    @Admin
    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleForm roleForm, @Positive @PathVariable Long id)
            throws RoleException {

        RoleDto roleDto = roleService.update(roleForm, id);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    /**
     * {@code GET  /roles} : get all the roles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roles in body.
     */
    @GetMapping("/roles")
    public List<RoleDto> getAllRoles() {
        return roleService.findAll();
    }

    /**
     * {@code GET  /roles/:id} : get the "id" role.
     *
     * @param id the id of the role to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the role,
     * or with status {@code 404 (Not Found)} if the id is not valid.
     */
    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@Positive @PathVariable Long id) throws RoleException {

        roleService.findOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * {@code DELETE  /roles/:id} : delete the "id" role.
     *
     * @param id the id of the role to delete.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * or with status {@code 400 (Bad Request)} if the role is not valid.
     */
    @Admin
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@Positive @PathVariable Long id,
                                           @PositiveOrZero @RequestParam Integer version)
            throws RoleException {

        roleService.delete(id, version);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
