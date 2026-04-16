package com.joaoohi.statemachine.service;

import com.joaoohi.statemachine.repository.ConversationStateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChatServiceProcessarOpcaoTest {

    private ChatService chatService;
    private ConversationStateRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(ConversationStateRepository.class);
        chatService = new ChatService(repository);
    }

    @Test
    void deveRetornarDataDeHoje() {
        String resposta = ReflectionTestUtils.invokeMethod(chatService, "processarOpcao", "1");
        assertNotNull(resposta);
        assertTrue(resposta.contains("Hoje é:"));
    }

    @Test
    void deveRetornarDiasParaFimDoAno() {
        String resposta = ReflectionTestUtils.invokeMethod(chatService, "processarOpcao", "2");
        assertNotNull(resposta);
        assertTrue(resposta.contains("Faltam"));
    }

    @Test
    void deveRetornarDiasPassadosNoAno() {
        String resposta = ReflectionTestUtils.invokeMethod(chatService, "processarOpcao", "3");
        assertNotNull(resposta);
        assertTrue(resposta.contains("Já se passaram"));
    }

    @Test
    void deveRetornarConversaEncerrada() {
        String resposta = ReflectionTestUtils.invokeMethod(chatService, "processarOpcao", "4");
        assertEquals("Conversa encerrada.", resposta);
    }

    @Test
    void deveRetornarOpcaoInvalida() {
        String resposta = ReflectionTestUtils.invokeMethod(chatService, "processarOpcao", "999");
        assertEquals("Opção inválida. Escolha entre 1, 2, 3 ou 4.", resposta);
    }
}
