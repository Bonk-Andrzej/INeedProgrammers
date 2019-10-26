package com.bonkAndrzej.iNeedProgrammers.chat.domain.chatParticipants;

import com.bonkAndrzej.iNeedProgrammers.chat.domain.chatMessage.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatParticipantsRepository  extends JpaRepository<ChatParticipants, Long> {
}
