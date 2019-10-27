package com.bonkAndrzej.iNeedProgrammers.audit;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistentAuditEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String principal;

    private Instant auditEventDate;
    private String auditEventType;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "persistent_audit_evt_data",
                     joinColumns = @JoinColumn(name = "event_id"))
    private Map<String, String> data = new HashMap<>();
}
