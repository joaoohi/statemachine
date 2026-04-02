package com.joaoohi.statemachine.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatRequestTest {

    @Test
    void deveDefinirMensagem() {

        ChatRequest request = new ChatRequest();
        request.mensagem = "teste";

        assertEquals("teste", request.mensagem);
    }
}