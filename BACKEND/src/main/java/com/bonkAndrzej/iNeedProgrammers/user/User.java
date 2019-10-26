package com.bonkAndrzej.iNeedProgrammers.user;

import com.bonkAndrzej.iNeedProgrammers.audit.config.AuditTableEntity;
import com.bonkAndrzej.iNeedProgrammers.user.role.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ? AND version = ?")
@Where(clause = "is_deleted = false")
@Table(name = "user", indexes = {
        @Index(name = "ix_user_created_by", columnList = "created_by", unique = false),
        @Index(name = "ix_user_last_modified_by", columnList = "last_modified_by", unique = false)})
@Getter @Setter
@DynamicInsert @DynamicUpdate
public class User extends AuditTableEntity {
    private String firstName;
    private String lastName;

    @NotBlank @Size(min = 5, max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank @Size(min = 6, max = 100)
    @Column(nullable = false)
    private String password;
    private String resetPasswordKey;
    private Instant resetPasswordDate;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled = false;
    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    private String activationKey;

    @ManyToOne(fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    public User() {
        initUuid();
    }
}
