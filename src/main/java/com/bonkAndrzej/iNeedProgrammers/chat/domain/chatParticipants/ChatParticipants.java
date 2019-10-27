package com.bonkAndrzej.iNeedProgrammers.chat.domain.chatParticipants;

import com.bonkAndrzej.iNeedProgrammers.audit.config.AuditTableEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@SQLDelete(sql = "UPDATE chat_participants SET is_deleted = true WHERE id = ? AND version = ?")
@Where(clause = "is_deleted = false")
@Table(name = "chat_participants", indexes = {
        @Index(name = "ix_chat_participants_created_by", columnList = "created_by", unique = false),
        @Index(name = "ix_chat_participants_last_modified_by", columnList = "last_modified_by", unique = false)})
@Getter @Setter
public class ChatParticipants extends AuditTableEntity {
}
