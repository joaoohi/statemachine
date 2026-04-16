package com.joaoohi.statemachine.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatResponseTest {

    @Test
    void deveCriarChatResponseCorretamente() {
        ChatResponse response = new ChatResponse("Resposta do chat");

        assertEquals("Resposta do chat", response.resposta);
    }
}