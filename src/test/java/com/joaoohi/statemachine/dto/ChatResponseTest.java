package com.joaoohi.statemachine.dto;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatResponseTest {

    @Test
    void deveCriarChatResponseCorretamente() {

        ChatResponse response = new ChatResponse(
                10L,
                List.of("opcao1", "opcao2")
        );

        assertEquals(10L, response.id);
        assertEquals(2, response.menu.size());
        assertEquals("opcao1", response.menu.get(0));
    }
}