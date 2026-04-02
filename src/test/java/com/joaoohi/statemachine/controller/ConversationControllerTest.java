package com.joaoohi.statemachine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoohi.statemachine.dto.ConversationRequest;
import com.joaoohi.statemachine.model.Conversation;
import com.joaoohi.statemachine.repository.ConversationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConversationController.class)
class ConversationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConversationRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void deveCriarConversa() throws Exception {

        Conversation conversation = new Conversation();
        conversation.id = 1L;
        conversation.clientInput = "Olá";

        when(repository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(conversation);

        ConversationRequest request = new ConversationRequest();
        request.clientInput = "Olá";

        mockMvc.perform(post("/conversation")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.clientInput").value("Olá"));
    }

    @Test
    void deveSalvarConversaComOutroTexto() throws Exception {

        Conversation conversation = new Conversation();
        conversation.id = 2L;
        conversation.clientInput = "Teste";

        when(repository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(conversation);

        ConversationRequest request = new ConversationRequest();
        request.clientInput = "Teste";

        mockMvc.perform(post("/conversation")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.clientInput").value("Teste"));
    }

    @Test
    void deveSalvarConversaComMensagemLonga() throws Exception {

        Conversation conversation = new Conversation();
        conversation.id = 3L;
        conversation.clientInput = "Mensagem muito longa";

        when(repository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(conversation);

        ConversationRequest request = new ConversationRequest();
        request.clientInput = "Mensagem muito longa";

        mockMvc.perform(post("/conversation")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientInput").value("Mensagem muito longa"));
    }

    @Test
    void deveSalvarConversaComNumero() throws Exception {

        Conversation conversation = new Conversation();
        conversation.id = 4L;
        conversation.clientInput = "123";

        when(repository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(conversation);

        ConversationRequest request = new ConversationRequest();
        request.clientInput = "123";

        mockMvc.perform(post("/conversation")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientInput").value("123"));
    }
}