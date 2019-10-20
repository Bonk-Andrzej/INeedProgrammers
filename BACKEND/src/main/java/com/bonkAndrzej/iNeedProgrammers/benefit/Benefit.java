package com.bonkAndrzej.iNeedProgrammers.benefits;

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

@Entity
@SQLDelete(sql = "UPDATE benefit SET is_deleted = true WHERE id = ? AND version = ?")
@Where(clause = "is_deleted = false")
@Table(name = "benefit", indexes = {
        @Index(name = "ix_benefit_created_by", columnList = "created_by", unique = false),
        @Index(name = "ix_benefit_last_modified_by", columnList = "last_modified_by", unique = false)})
@Getter @Setter
public class Benefit extends AuditTableEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    public Benefit() {
        initUuid();
    }
}
