package com.bonkAndrzej.iNeedProgrammers.jobOrder;

import com.bonkAndrzej.iNeedProgrammers.audit.config.AuditTableEntity;
import com.bonkAndrzej.iNeedProgrammers.category.Category;
import com.bonkAndrzej.iNeedProgrammers.technology.Technology;
import com.bonkAndrzej.iNeedProgrammers.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ? AND version = ?")
@Where(clause = "is_deleted = false")
@Table(name = "job_order", indexes = {
        @Index(name = "ix_job_order_created_by", columnList = "created_by", unique = false),
        @Index(name = "ix_job_order_last_modified_by", columnList = "last_modified_by", unique = false)})
@Getter @Setter
public class JobOrder extends AuditTableEntity {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private Date created;

    private Date updated;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.S")
    @Future @NotNull
    private Date end;

    @ManyToOne
    private User employer;

    @ManyToOne
    private User executor;

    @ManyToMany
    private List<Category> categories;

    @ManyToMany
    private List<Technology> technologies;

    public JobOrder() {
        initUuid();
    }

    public int getHoursTillEnd() {
        if (end != null) {
            return (int) ((end.getTime() - Calendar.getInstance().getTime().getTime()) / (1000 * 60 * 60));
        }
        return 0;
    }

    public String getShortenContent() {
        if (content.length() > 500) {
            String text = content;
            text = text.substring(0, 500) + " ...";
            return text;
        }
        return content;
    }
}
