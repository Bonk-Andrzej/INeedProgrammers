package com.bonkAndrzej.iNeedProgrammers.chat.domain.chatThread;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatThreadRepository extends JpaRepository<ChatThread, Long> {
}
