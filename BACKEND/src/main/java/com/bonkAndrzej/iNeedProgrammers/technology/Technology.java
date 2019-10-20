package com.bonkAndrzej.iNeedProgrammers.technology;

import com.bonkAndrzej.iNeedProgrammers.audit.config.AuditTableEntity;

public class Technology extends AuditTableEntity {
    private String name;

    public Technology() {
        initUuid();
    }
}
