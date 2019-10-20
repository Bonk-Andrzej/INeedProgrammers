CREATE TABLE IF NOT EXISTS `role`
(
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
    `created_date`       datetime(6)           DEFAULT NULL,
    `last_modified_date` datetime(6)           DEFAULT NULL,
    `uuid`               binary(16)   NOT NULL,
    `version`            int(11)               DEFAULT NULL,
    `created_by`         varchar(255)          DEFAULT NULL,
    `last_modified_by`   varchar(255)          DEFAULT NULL,
    `is_deleted`         bit(1)       NOT NULL DEFAULT FALSE,

    `name`               varchar(255) NOT NULL,
    `slug`               varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_role_name` (`name`),
    UNIQUE KEY `UK_role_slug` (`slug`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `role` (`uuid`, created_by, last_modified_by, `name`, `slug`)
VALUES (0x2F3E0F7A83934B97956049D011586530, 'sql init', 'sql init', 'ROLE_ADMIN', 'admin'),
       (0x472B28FC87C74CADB0FBA110A2C0F04D, 'sql init', 'sql init', 'ROLE_USER', 'user');

CREATE TABLE IF NOT EXISTS `user`
(
    `id`                  bigint(20)   NOT NULL AUTO_INCREMENT,
    `created_date`        datetime(6)           DEFAULT NULL,
    `last_modified_date`  datetime(6)           DEFAULT NULL,
    `uuid`                binary(16)   NOT NULL,
    `version`             int(11)      NOT NULL DEFAULT 0,
    `created_by`          varchar(255)          DEFAULT NULL,
    `last_modified_by`    varchar(255)          DEFAULT NULL,
    `is_deleted`          bit(1)       NOT NULL DEFAULT FALSE,

    `first_name`          varchar(255)          DEFAULT NULL,
    `last_name`           varchar(255)          DEFAULT NULL,
    `login`               varchar(50)  NOT NULL,
    `email`               varchar(255) NOT NULL,
    `password`            varchar(255) NOT NULL,
    `reset_password_key`  varchar(255)          DEFAULT NULL,
    `reset_password_date` datetime(6)           DEFAULT NULL,
    `is_enabled`          bit(1)       NOT NULL DEFAULT FALSE,

    `role_id`             bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_user_login` (`login`),
    UNIQUE KEY `UK_user_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `user` (`uuid`, `version`, created_by, last_modified_by, `first_name`, `last_name`,
                    `login`, `email`, `password`, `is_enabled`, `role_id`)

# password == 'password'
VALUES (0x26EB99A27BBE4B9CA4ECB83DCB141EA0, 0, 'sql init', 'sql init', 'admin name', 'admin surname',
        'admin', 'admin@mail.com', '$2a$10$paKiMMCrBzPElvypRbzeYuc3ZbHdUEuNWieTxMYtMMspFs/2CMfYq', 1, 1),
       (0x25CB99A59CEE4B9DA4EDB83CCB346EA0, 0, 'sql init', 'sql init', 'user name', 'user surname',
        'user', 'user@mail.com', '$2a$10$paKiMMCrBzPElvypRbzeYuc3ZbHdUEuNWieTxMYtMMspFs/2CMfYq', 1, 2);



# CREATE TABLE IF NOT EXISTS `user_role`
# (
#     `user_id` bigint(20) NOT NULL,
#     `role_id` bigint(20) NOT NULL,
#     PRIMARY KEY (`user_id`, `role_id`),
#     KEY `FK_user_role_role_id` (`role_id`),
#     CONSTRAINT `FK_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
#     CONSTRAINT `FK_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8mb4
#   COLLATE = utf8mb4_unicode_ci;

# INSERT INTO `user_role` (`user_id`, `role_id`)
# VALUES (1, 1),
#        (1, 2),
#        (1, 3);

CREATE TABLE IF NOT EXISTS `category`
(
    `id`                 bigint(20)  NOT NULL AUTO_INCREMENT,
    `created_date`       datetime(6)          DEFAULT NULL,
    `last_modified_date` datetime(6)          DEFAULT NULL,
    `uuid`               binary(16)  NOT NULL,
    `version`            int(11)              DEFAULT NULL,
    `created_by`         varchar(255)         DEFAULT NULL,
    `last_modified_by`   varchar(255)         DEFAULT NULL,
    `is_deleted`         bit(1)      NOT NULL DEFAULT FALSE,

    `name`               varchar(50) NOT NULL,
    `description`        varchar(100)         DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_category_name` (`name`)
)



