package com.joaoohi.statemachine.service;

import com.joaoohi.statemachine.enums.States;
import com.joaoohi.statemachine.model.ConversationState;
import com.joaoohi.statemachine.repository.ConversationStateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    private ChatService chatService;
    private ConversationStateRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(ConversationStateRepository.class);
        chatService = new ChatService(repository);
    }

    @Test
    void deveRetornarMenuQuandoEstadoInicial() {
        String clientId = "client123";
        String mensagem = "oi";

        when(repository.findById(clientId)).thenReturn(Optional.empty());

        String resposta = chatService.receberMensagem(clientId, mensagem);

        assertTrue(resposta.contains("1 - Que dia é hoje?"));

        ArgumentCaptor<ConversationState> captor = ArgumentCaptor.forClass(ConversationState.class);
        verify(repository).save(captor.capture());

        ConversationState saved = captor.getValue();
        assertEquals(clientId, saved.getClientId());
        assertEquals(States.AGUARDANDO_RESPOSTA, saved.getState());
        assertEquals(mensagem, saved.getMessage());
    }

    @Test
    void deveProcessarOpcaoQuandoEstadoAguardandoResposta() {
        String clientId = "client123";
        String mensagem = "1";
        ConversationState existingConv = new ConversationState(clientId, "oi", States.AGUARDANDO_RESPOSTA);

        when(repository.findById(clientId)).thenReturn(Optional.of(existingConv));

        String resposta = chatService.receberMensagem(clientId, mensagem);

        assertTrue(resposta.contains("Hoje é:"));

        ArgumentCaptor<ConversationState> captor = ArgumentCaptor.forClass(ConversationState.class);
        verify(repository).save(captor.capture());

        ConversationState saved = captor.getValue();
        assertEquals(States.FINAL, saved.getState());
        assertEquals(mensagem, saved.getMessage());
    }

    @Test
    void deveReiniciarConversaQuandoEstadoFinal() {
        String clientId = "client123";
        String mensagem = "oi";
        ConversationState existingConv = new ConversationState(clientId, "4", States.FINAL);

        when(repository.findById(clientId)).thenReturn(Optional.of(existingConv));

        String resposta = chatService.receberMensagem(clientId, mensagem);

        assertTrue(resposta.contains("1 - Que dia é hoje?"));

        ArgumentCaptor<ConversationState> captor = ArgumentCaptor.forClass(ConversationState.class);
        verify(repository).save(captor.capture());

        ConversationState saved = captor.getValue();
        assertEquals(States.AGUARDANDO_RESPOSTA, saved.getState());
    }
}
