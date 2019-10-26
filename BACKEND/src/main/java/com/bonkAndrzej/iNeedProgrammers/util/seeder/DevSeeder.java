package com.bonkAndrzej.iNeedProgrammers.util.seeder;

import com.bonkAndrzej.iNeedProgrammers.benefit.Benefit;
import com.bonkAndrzej.iNeedProgrammers.benefit.BenefitRepository;
import com.bonkAndrzej.iNeedProgrammers.category.Category;
import com.bonkAndrzej.iNeedProgrammers.category.CategoryRepository;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.JobOffer;
import com.bonkAndrzej.iNeedProgrammers.jobOffer.JobOfferRepository;
import com.bonkAndrzej.iNeedProgrammers.location.Location;
import com.bonkAndrzej.iNeedProgrammers.location.LocationRepository;
import com.bonkAndrzej.iNeedProgrammers.seniority.Seniority;
import com.bonkAndrzej.iNeedProgrammers.seniority.SeniorityRepository;
import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import com.bonkAndrzej.iNeedProgrammers.technology.TechnologyRepository;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import com.bonkAndrzej.iNeedProgrammers.user.UserRepository;
import com.bonkAndrzej.iNeedProgrammers.user.role.Role;
import com.bonkAndrzej.iNeedProgrammers.user.role.RoleRepository;
import com.bonkAndrzej.iNeedProgrammers.util.AppProfile;
import com.bonkAndrzej.iNeedProgrammers.util.RolesConstants;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@Component @Profile(AppProfile.DEVELOPMENT)
@Transactional
public class DevSeeder {

    private static final String PASSWORD = "password";
    private static final String ADMIN_EMAIL = "seedAdmin@email.com";
    private static final String ADMIN_LOGIN = "seedAdmin";
    private static final String USER_EMAIL = "seedUser@email.com";
    private static final String USER_LOGIN = "seedUser";

    private final Faker faker;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BenefitRepository benefitRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final SeniorityRepository seniorityRepository;
    private final TechnologyRepository technologyRepository;
    private final JobOfferRepository jobOfferRepository;

    @Autowired
    public DevSeeder(UserRepository userRepository,
                     RoleRepository roleRepository,
                     BenefitRepository benefitRepository,
                     CategoryRepository categoryRepository,
                     LocationRepository locationRepository,
                     SeniorityRepository seniorityRepository,
                     TechnologyRepository technologyRepository,
                     JobOfferRepository jobOfferRepository,
                     PasswordEncoder passwordEncoder
    ) {
        this.faker = new Faker();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.benefitRepository = benefitRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.seniorityRepository = seniorityRepository;
        this.technologyRepository = technologyRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRole();
        seedUser();
        seedBenefit();
        seedCategory();
        seedLocation();
        seedSeniority();
        seedTechnology();
        seedJobOffer();
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
        if (userRepository.findOneByEmailWithEagerRelationships(DevSeeder.ADMIN_EMAIL).isEmpty()) {

            String password = passwordEncoder.encode(DevSeeder.PASSWORD);
            Role roleAdmin = roleRepository.findByName(RolesConstants.ADMIN).orElseThrow();
            Role roleUser = roleRepository.findByName(RolesConstants.USER).orElseThrow();

            User admin = new User();
            admin.setLogin(DevSeeder.ADMIN_LOGIN);
            admin.setEmail(DevSeeder.ADMIN_EMAIL);
            admin.setPassword(password);
            admin.setFirstName("Tom");
            admin.setLastName("Doe");
            admin.setRole(roleAdmin);
            userRepository.save(admin);

            User user = new User();
            user.setLogin(DevSeeder.USER_LOGIN);
            user.setEmail(DevSeeder.USER_EMAIL);
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
                        + lastName.toLowerCase() + "@email.com";

                User randomUser = new User();
                randomUser.setLogin(login);
                randomUser.setEmail(randomUserEmail);
                randomUser.setPassword(password);
                randomUser.setFirstName(firstName);
                randomUser.setLastName(lastName);
                randomUser.setRole(roleUser);
                log.info("seedUser");
                userRepository.save(randomUser);
            }
        }
    }

    private void seedBenefit() {
        log.info("benefitRepository.count() < 10 " + benefitRepository.count());
        if (benefitRepository.count() < 10) {
            for (int i = 0; i < 10; i++) {
                Benefit benefit = new Benefit();
                benefit.setName("Benefit " + i);
                benefitRepository.save(benefit);
            }
        }
    }

    private void seedCategory() {
        if (categoryRepository.count() < 10) {
            for (int i = 0; i < 10; i++) {
                Category category = new Category();
                category.setName(faker.hacker().abbreviation());
                categoryRepository.save(category);
            }
        }
    }

    private void seedLocation() {
        if (locationRepository.count() < 10) {
            for (int i = 0; i < 10; i++) {
                Location location = new Location();
                location.setName(faker.address().cityName());
                locationRepository.save(location);
            }
        }
    }

    private void seedSeniority() {
        if (seniorityRepository.count() < 10) {
            for (int i = 0; i < 10; i++) {
                Seniority seniority = new Seniority();
                seniority.setName("Seniority " + i);
                seniorityRepository.save(seniority);
            }
        }
    }

    private void seedTechnology() {
        if (categoryRepository.count() < 10) {
            log.info("seedTechnology seedTechnology");
            for (int i = 0; i < 10; i++) {
                Technology technology = new Technology();
                technology.setName(faker.rickAndMorty().character());
                technologyRepository.save(technology);
            }
        }
    }

    private void seedJobOffer() {
        if (jobOfferRepository.count() < 10) {
            List<User> users = userRepository.findAll();
            List<Benefit> benefits = benefitRepository.findAll();
            List<Category> categories = categoryRepository.findAll();
            List<Location> locations = locationRepository.findAll();
            List<Seniority> seniorities = seniorityRepository.findAll();
            List<Technology> technologies = technologyRepository.findAll();
            log.info("seedJobOffer seedJobOffer");
            for (int i = 0; i < 10; i++) {
                Random random = new Random();

                JobOffer jobOffer = new JobOffer();
                jobOffer.setTitle(faker.commerce().productName());
                jobOffer.setContent(faker.chuckNorris().fact());
                jobOffer.setEmail(faker.company().name().concat("@email.com"));
                jobOffer.setSalary(random.nextLong());
                jobOffer.setPhoneNumber(faker.phoneNumber().phoneNumber());

                jobOffer.setEmployer(users.get(random.nextInt(users.size())));

                Set<Benefit> benefitSet = new HashSet<>();
                Set<Category> categorySet = new HashSet<>();
                Set<Location> locationSet = new HashSet<>();
                Set<Seniority> senioritySet = new HashSet<>();
                Set<Technology> technologySet = new HashSet<>();

                for (int j = 0; j < 5; j++) {
                    benefitSet.add(benefits.get(random.nextInt(benefits.size())));
                    categorySet.add(categories.get(random.nextInt(categories.size())));
                    locationSet.add(locations.get(random.nextInt(locations.size())));
                    senioritySet.add(seniorities.get(random.nextInt(seniorities.size())));
//                    technologySet.add(technologies.get(random.nextInt(technologies.size())));
                }
                jobOffer.setBenefits(benefitSet);
                jobOffer.setCategories(categorySet);
                jobOffer.setLocations(locationSet);
                jobOffer.setSenioritySet(senioritySet);
//                jobOffer.setTechnologies(technologySet);

                jobOfferRepository.save(jobOffer);
            }
        }
    }
}
