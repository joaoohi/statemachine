package com.joaoohi.statemachine.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConversationRequestTest {

    @Test
    void deveDefinirClientInput() {

        ConversationRequest request = new ConversationRequest();
        request.clientInput = "teste";

        assertEquals("teste", request.clientInput);
    }
}