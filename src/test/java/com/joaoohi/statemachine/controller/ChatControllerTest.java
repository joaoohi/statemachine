package com.joaoohi.statemachine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoohi.statemachine.dto.ChatRequest;
import com.joaoohi.statemachine.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChatService chatService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void deveReceberMensagem() throws Exception {
        ChatRequest request = new ChatRequest();
        request.clientId = "client123";
        request.mensagem = "oi";

        when(chatService.receberMensagem("client123", "oi")).thenReturn("Menu de opções...");

        mockMvc.perform(post("/chat")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resposta").value("Menu de opções..."));
    }
}
