package com.bonkAndrzej.iNeedProgrammers.user;

import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserForm;
import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleForm;
import com.bonkAndrzej.iNeedProgrammers.util.error.CustomValidationException;
import com.bonkAndrzej.iNeedProgrammers.util.error.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

;

/**
 * REST controller for managing User.
 * <p>
 * This class accesses the {@link User} entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    /**
     * {@code POST  /orders} : Create a new order.
     *
     * @param userForm the order to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new order, or with status {@code 400 (Bad Request)} if the order has already an ID.
     */
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserForm userForm) throws ResourceNotFoundException {

        UserDto userDto = userService.save(userForm);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> getUser(@Positive @PathVariable Long id) throws ResourceNotFoundException {

        UserDto userDto = userService.findOne(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@Positive @PathVariable("id") Long id,
                                              @Valid @RequestBody UserForm userForm)
            throws CustomValidationException, ResourceNotFoundException {

        UserDto userDto = userService.update(id, userForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@Positive @PathVariable Long id,
                                           @PositiveOrZero @RequestParam Integer version)
            throws CustomValidationException, ResourceNotFoundException {

        userService.delete(id, version);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/users/{id}", params = "status")
    public ResponseEntity<UserDto> changeUserStatus(@Positive @PathVariable Long id,
                                                    @NotBlank @RequestParam String status)
            throws CustomValidationException, ResourceNotFoundException {

        UserDto userDto = userService.changeStatus(id, status);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @PatchMapping("/users/{id}")
    public ResponseEntity<UserDto> addRoleToUser(@Positive @PathVariable Long id,
                                                 @Valid @RequestBody RoleForm roleForm)
            throws CustomValidationException, ResourceNotFoundException {

        UserDto userDto = userService.addRoleToUser(id, roleForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
