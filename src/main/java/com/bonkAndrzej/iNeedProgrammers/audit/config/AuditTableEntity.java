package com.bonkAndrzej.iNeedProgrammers.audit.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * For implement a soft delete the entity MUST contain the following annotations:
 * <pre>
 * {@code @SQLDelete(sql = "UPDATE table_name SET is_deleted = 1 WHERE id = ? AND version = ?")}
 * {@code @Where(clause = "is_deleted = 0")}
 * </pre>
 * The entity class must have a public or protected no-argument constructor.
 * It may define additional constructors as well.
 * <p>
 * The entity class MUST call the {@link #initUuid()} in constructor during
 * instantiation.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public abstract class AuditTableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BINARY(16)",
            nullable = false, updatable = false)
    private UUID uuid;


    @Version @Column(nullable = false)
    private Integer version;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;


// -----------------------------------------------------------------------------
// Methods
// -----------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditTableEntity entity = (AuditTableEntity) o;
        return Objects.equals(uuid, entity.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }


    /**
     * According to the contract between equals and hashcode -
     * the hashcode can not change over the entire lifecycle of the object.
     * <p>
     * Moreover, basing these methods on the {@link Id} is not recommended.
     * <p>
     * The ID does not always contain a value
     * (eg.: when creating the Address object, assigning it to the Person object,
     * with the participation of {@link CascadeType} PERSIST)
     * <p>
     * For this reason, each entity bases equals and hashcode on unchanged uuid.
     */
    protected void initUuid() {
        if (uuid != null) {
            throw new IllegalStateException("The UUID cannot be changed.");
        }
        uuid = UUID.randomUUID();
    }
}
