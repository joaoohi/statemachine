package com.joaoohi.statemachine.repository;

import com.joaoohi.statemachine.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository
        extends JpaRepository<Conversation, Long> {
}