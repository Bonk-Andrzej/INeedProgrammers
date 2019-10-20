package com.bonkAndrzej.iNeedProgrammers.util.seeder;

import com.bonkAndrzej.iNeedProgrammers.security.RolesConstants;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import com.bonkAndrzej.iNeedProgrammers.user.UserRepository;
import com.bonkAndrzej.iNeedProgrammers.user.role.Role;
import com.bonkAndrzej.iNeedProgrammers.user.role.RoleRepository;
import com.bonkAndrzej.iNeedProgrammers.util.config.AppProfile;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component @Profile(AppProfile.DEVELOPMENT)
@Transactional
public class DevSeeder {

    private final Faker faker;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DevSeeder(UserRepository userRepository,
                     RoleRepository roleRepository,
                     PasswordEncoder passwordEncoder
    ) {
        this.faker = new Faker();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @EventListener()
    public void seed(ContextRefreshedEvent event) {
        seedRole();
        seedUser();

    }


    private void seedRole() {
        if (roleRepository.count() < 2) {
            Role roleAdmin = new Role();
            roleAdmin.setName(RolesConstants.ADMIN);
            roleAdmin.setSlug("ADMIN");
            roleRepository.save(roleAdmin);

            Role roleUser = new Role();
            roleAdmin.setName(RolesConstants.USER);
            roleAdmin.setSlug("USER");
            roleRepository.saveAndFlush(roleUser);
        }
    }

    private void seedUser() {
        if (userRepository.findOneByEmailWithEagerRelationships("admin@example.com").isEmpty()) {
            String password = passwordEncoder.encode("password");
            Role roleAdmin = roleRepository.findByName(RolesConstants.ADMIN).orElseThrow();
            Role roleUser = roleRepository.findByName(RolesConstants.USER).orElseThrow();

            User admin = new User();
            admin.setLogin("login.admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(password);
            admin.setFirstName("Richard");
            admin.setLastName("Roe");
            admin.setRole(roleAdmin);
            userRepository.save(admin);

            User user = new User();
            user.setLogin("login.user");
            user.setEmail("user@example.com");
            user.setPassword(password);
            user.setFirstName("Harry");
            user.setLastName("Potter");
            user.setRole(roleUser);
            userRepository.save(user);

            Set<String> usedNames = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                String firstName;
                String lastName;
                String login;

                do {
                    firstName = faker.name().firstName();
                    lastName = faker.name().lastName();
                    login = firstName + " " + lastName;

                } while (usedNames.contains(firstName + lastName));
                usedNames.add(firstName + lastName);
                String randomUserEmail = firstName.toLowerCase() + "."
                        + lastName.toLowerCase() + "@example.com";

                User randomUser = new User();
                randomUser.setLogin(login);
                randomUser.setEmail(randomUserEmail);
                randomUser.setPassword(passwordEncoder.encode(password));
                randomUser.setFirstName(firstName);
                randomUser.setLastName(lastName);
                randomUser.setRole(roleUser);

                userRepository.save(randomUser);
            }
        }
    }

}
