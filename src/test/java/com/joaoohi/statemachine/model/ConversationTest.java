package com.joaoohi.statemachine.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConversationTest {

    @Test
    void deveCriarConversation() {

        Conversation conversation = new Conversation();

        conversation.id = 10L;
        conversation.clientInput = "teste";

        assertEquals(10L, conversation.id);
        assertEquals("teste", conversation.clientInput);
    }
}