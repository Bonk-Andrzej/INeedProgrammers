package com.bonkAndrzej.iNeedProgrammers.jobOffer;

import com.bonkAndrzej.iNeedProgrammers.audit.config.AuditTableEntity;
import com.bonkAndrzej.iNeedProgrammers.benefit.Benefit;
import com.bonkAndrzej.iNeedProgrammers.category.Category;
import com.bonkAndrzej.iNeedProgrammers.location.Location;
import com.bonkAndrzej.iNeedProgrammers.seniority.Seniority;
import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@SQLDelete(sql = "UPDATE job_offer SET is_deleted = true WHERE id = ? AND version = ?")
@Where(clause = "is_deleted = false")
@Table(name = "job_offer", indexes = {
        @Index(name = "ix_job_offer_created_by", columnList = "created_by", unique = false),
        @Index(name = "ix_job_offer_last_modified_by", columnList = "last_modified_by", unique = false)})
@Getter @Setter
public class JobOffer extends AuditTableEntity {

    @NotBlank(message = "Empty title") @Column(nullable = false)
    private String title;
    @NotBlank(message = "Empty content") @Column(nullable = false)
    private String content;
    @Email @Column(nullable = false)
    private String email;
    @Positive @Column(nullable = false)
    private Long salary;
    private String phoneNumber;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_offer_benefit",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id"))
    private Set<Benefit> benefits;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_offer_category",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_offer_location",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_offer_seniority",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "seniority_id"))
    private Set<Seniority> seniorities;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "job_offer_technology",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<Technology> technologies;


    public JobOffer() {
        initUuid();
    }

}
