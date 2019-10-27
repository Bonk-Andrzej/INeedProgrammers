CREATE TABLE IF NOT EXISTS `role`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)      NOT NULL DEFAULT 0,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `name`               VARCHAR(255) NOT NULL,
    `slug`               VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_name` (`name`),
    UNIQUE KEY `uk_role_slug` (`slug`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `role` (`uuid`, created_by, last_modified_by, `name`, `slug`)
VALUES (0x2F3E0F7A83934B97956049D011586530, 'sql init', 'sql init', 'ROLE_ADMIN', 'admin'),
       (0x472B28FC87C74CADB0FBA110A2C0F04D, 'sql init', 'sql init', 'ROLE_USER', 'user');

CREATE TABLE IF NOT EXISTS `user`
(
    `id`                  BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`        DATETIME(6)           DEFAULT NULL,
    `last_modified_date`  DATETIME(6)           DEFAULT NULL,
    `uuid`                BINARY(16)   NOT NULL,
    `version`             INT(11)      NOT NULL DEFAULT 0,
    `created_by`          VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`    VARCHAR(255)          DEFAULT NULL,
    `is_deleted`          BIT(1)       NOT NULL DEFAULT FALSE,

    `first_name`          VARCHAR(255)          DEFAULT NULL,
    `last_name`           VARCHAR(255)          DEFAULT NULL,
    `login`               VARCHAR(50)  NOT NULL,
    `email`               VARCHAR(255) NOT NULL,
    `password`            VARCHAR(100) NOT NULL,
    `reset_password_key`  VARCHAR(255)          DEFAULT NULL,
    `reset_password_date` DATETIME(6)           DEFAULT NULL,
    `is_enabled`          BIT(1)       NOT NULL DEFAULT FALSE,
    `activation_key`      VARCHAR(20)           DEFAULT NULL,

    `role_id`             BIGINT(20)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_login` (`login`),
    UNIQUE KEY `uk_user_email` (`email`),
    CONSTRAINT `fk_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `user` (`uuid`, `version`, created_by, last_modified_by, `first_name`, `last_name`,
                    `login`, `email`, `password`, `is_enabled`, `role_id`)

# password == 'password'
VALUES (0x26EB99A27BBE4B9CA4ECB83DCB141EA0, 0, 'sql init', 'sql init', 'admin name', 'admin surname',
        'admin', 'admin@email.com', '$2a$10$paKiMMCrBzPElvypRbzeYuc3ZbHdUEuNWieTxMYtMMspFs/2CMfYq', 1, 1),
       (0x25CB99A59CEE4B9DA4EDB83CCB346EA0, 0, 'sql init', 'sql init', 'user name', 'user surname',
        'user', 'user@email.com', '$2a$10$paKiMMCrBzPElvypRbzeYuc3ZbHdUEuNWieTxMYtMMspFs/2CMfYq', 1, 2);


CREATE TABLE IF NOT EXISTS `benefit`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)      NOT NULL DEFAULT 0,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `name`               VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_benefit_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `category`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)      NOT NULL DEFAULT 0,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `name`               VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_category_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `location`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)      NOT NULL DEFAULT 0,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `name`               VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_location_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `seniority`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)      NOT NULL DEFAULT 0,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `name`               VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_seniority_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `technology`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)      NOT NULL DEFAULT 0,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `name`               VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_technology_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `job_offer`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME(6)           DEFAULT NULL,
    `last_modified_date` DATETIME(6)           DEFAULT NULL,
    `uuid`               BINARY(16)   NOT NULL,
    `version`            INT(11)               DEFAULT NULL,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `is_deleted`         BIT(1)       NOT NULL DEFAULT FALSE,

    `title`              VARCHAR(255) NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `email`              VARCHAR(255) NOT NULL,
    `salary`             BIGINT(20)   NOT NULL,
    `phone_number`       VARCHAR(255)          DEFAULT NULL,
    `employer_id`        BIGINT(20)   NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_job_offer_user_id` FOREIGN KEY (`employer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `job_offer_benefit`
(
    `job_offer_id` BIGINT(20) NOT NULL,
    `benefit_id`   BIGINT(20) NOT NULL,
    PRIMARY KEY (`job_offer_id`, `benefit_id`),
    KEY `fk_job_offer_benefit_benefit_id` (`benefit_id`),
    CONSTRAINT `fk_job_offer_benefit_benefit_id` FOREIGN KEY (`benefit_id`) REFERENCES `benefit` (`id`),
    CONSTRAINT `fk_job_offer_benefit_job_offer_id` FOREIGN KEY (`job_offer_id`) REFERENCES job_offer (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `job_offer_category`
(
    `job_offer_id` BIGINT(20) NOT NULL,
    `category_id`  BIGINT(20) NOT NULL,
    PRIMARY KEY (`job_offer_id`, `category_id`),
    KEY `fk_job_offer_category_category_id` (`category_id`),
    CONSTRAINT `fk_job_offer_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
    CONSTRAINT `fk_job_offer_category_job_offer_id` FOREIGN KEY (`job_offer_id`) REFERENCES job_offer (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `job_offer_location`
(
    `job_offer_id` BIGINT(20) NOT NULL,
    `location_id`  BIGINT(20) NOT NULL,
    PRIMARY KEY (`job_offer_id`, `location_id`),
    KEY `fk_job_offer_location_location_id` (`location_id`),
    CONSTRAINT `fk_job_offer_location_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
    CONSTRAINT `fk_job_offer_location_job_offer_id` FOREIGN KEY (`job_offer_id`) REFERENCES job_offer (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `job_offer_seniority`
(
    `job_offer_id` BIGINT(20) NOT NULL,
    `seniority_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`job_offer_id`, `seniority_id`),
    KEY `fk_job_offer_seniority_seniority_id` (`seniority_id`),
    CONSTRAINT `fk_job_offer_seniority_seniority_id` FOREIGN KEY (`seniority_id`) REFERENCES `seniority` (`id`),
    CONSTRAINT `fk_job_offer_seniority_job_offer_id` FOREIGN KEY (`job_offer_id`) REFERENCES job_offer (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `job_offer_technology`
(
    `job_offer_id`  BIGINT(20) NOT NULL,
    `technology_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`job_offer_id`, `technology_id`),
    KEY `fk_job_offer_technology_technology_id` (`technology_id`),
    CONSTRAINT `fk_job_offer_technology_technology_id` FOREIGN KEY (`technology_id`) REFERENCES `technology` (`id`),
    CONSTRAINT `fk_job_offer_technology_job_offer_id` FOREIGN KEY (`job_offer_id`) REFERENCES job_offer (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
