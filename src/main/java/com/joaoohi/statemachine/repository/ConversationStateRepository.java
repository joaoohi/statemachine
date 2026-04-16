package com.joaoohi.statemachine.repository;

import com.joaoohi.statemachine.model.ConversationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationStateRepository extends JpaRepository<ConversationState, String> {
}