package com.bonkAndrzej.iNeedProgrammers.user;

import com.bonkAndrzej.iNeedProgrammers.user.dto.UserDto;
import com.bonkAndrzej.iNeedProgrammers.user.dto.UserForm;
import com.bonkAndrzej.iNeedProgrammers.user.exception.UserException;
import com.bonkAndrzej.iNeedProgrammers.user.role.dto.RoleForm;
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
 * REST controller for managing User.
 */
@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    /**
     * {@code POST  /users} : Create a new user.
     *
     * @param userForm the order to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new order, or with
     * status {@code 400 (Bad Request)} if the order has already an ID.
     */
    @Admin
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserForm userForm) throws UserException {

        UserDto userDto = userService.save(userForm);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> getUser(@Positive @PathVariable Long id) throws UserException {

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
            throws UserException {

        UserDto userDto = userService.update(id, userForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Admin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@Positive @PathVariable Long id,
                                           @PositiveOrZero @RequestParam Integer version)
            throws UserException {

        userService.delete(id, version);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Admin
    @PatchMapping("/users/{id}")
    public ResponseEntity<UserDto> changeUserRole(@Positive @PathVariable Long id,
                                                  @Valid @RequestBody RoleForm roleForm)
            throws UserException {

        UserDto userDto = userService.changeUserRole(id, roleForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
