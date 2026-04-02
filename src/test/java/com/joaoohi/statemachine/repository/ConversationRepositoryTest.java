package com.joaoohi.statemachine.repository;

import com.joaoohi.statemachine.model.Conversation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConversationRepositoryTest {

    @Autowired
    ConversationRepository repository;

    @Test
    void deveSalvarConversation() {

        Conversation c = new Conversation();
        c.clientInput = "teste";

        Conversation saved = repository.save(c);

        assertNotNull(saved.id);
        assertEquals("teste", saved.clientInput);
    }
}