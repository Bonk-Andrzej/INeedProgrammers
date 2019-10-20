package com.bonkAndrzej.iNeedProgrammers.category;

import com.bonkAndrzej.iNeedProgrammers.audit.config.AuditTableEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@SQLDelete(sql = "UPDATE category SET is_deleted = true WHERE id = ? AND version = ?")
@Where(clause = "is_deleted = false")
@Table(name = "category", indexes = {
        @Index(name = "ix_category_created_by", columnList = "created_by", unique = false),
        @Index(name = "ix_category_last_modified_by", columnList = "last_modified_by", unique = false)})
@Getter @Setter
public class Category extends AuditTableEntity {

    @NotBlank
    @Size(max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Size(max = 100)
    @Column(length = 100)
    private String description;

    public Category() {
        initUuid();
    }
}
